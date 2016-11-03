package pl.edu.agh.kis.retrofit2demo;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.PopupMenu;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import pl.edu.agh.kis.retrofit2demo.httpclient.StudentsService;
import pl.edu.agh.kis.retrofit2demo.model.Student;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    Button btnGetAllStudents;

    ListView lv;

    CustomListViewAdapter adapter;

    List<Student> items = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnGetAllStudents = (Button) findViewById(R.id.btnGetAllStudents);

        btnGetAllStudents.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getStudents();
            }
        });

        lv = (ListView) findViewById(R.id.studentsListView);

        adapter = new CustomListViewAdapter(this, R.layout.list_item, R.id.studentListItemTextView, items);
        lv.setAdapter(adapter);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Student item = (Student) adapterView.getItemAtPosition(i);
                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(MainActivity.this);
                alertDialogBuilder.setTitle(item.getName());
                alertDialogBuilder.setMessage(item.toStringDetailed());
                alertDialogBuilder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                    }
                });
                AlertDialog alertDialog = alertDialogBuilder.create();
                alertDialog.show();
//                alertDialog.dismiss();
            }
        });
    }

    private void getStudents() {

        StudentsService studentsService = StudentsService.retrofit.create(StudentsService.class);

        Call<List<Student>> call = studentsService.getStudents();

        call.enqueue(new Callback<List<Student>>() {
            @Override
            public void onResponse(Call<List<Student>> call, Response<List<Student>> response) {
                List<Student> students = response.body();
                items.addAll(students);
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<List<Student>> call, Throwable t) {
                Toast.makeText(MainActivity.this, "ERROR!", Toast.LENGTH_SHORT).show();
            }
        });

    }

    /*public void showPopup(View v) {
        PopupMenu popup = new PopupMenu(this, v);
        popup.inflate(R.menu.popup_menu);
//        MenuInflater inflater = popup.getMenuInflater();
//        inflater.inflate(R.menu.popup_menu, popup.getMenu());
        popup.show();
    }*/

}
