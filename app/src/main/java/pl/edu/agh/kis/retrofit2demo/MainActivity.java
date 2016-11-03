package pl.edu.agh.kis.retrofit2demo;

import android.app.AlertDialog;
import android.app.FragmentManager;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
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

    CustomListViewAdapter<Student> adapter;

    List<Student> items = new ArrayList<>();

    StudentsService studentsService = StudentsService.retrofit.create(StudentsService.class);

    FragmentManager fm = getFragmentManager();

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

        adapter = new CustomListViewAdapter<>(this, R.layout.list_item, R.id.studentListItemTextView, items, fm, studentsService);
        lv.setAdapter(adapter);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Student student = (Student) adapterView.getItemAtPosition(i);
                StudentDialog sd = StudentDialog.newInstance(student);
                sd.show(fm, "studentDialog");
            }
        });
    }

    private void getStudents() {
        Call<List<Student>> call = studentsService.getStudents();

        call.enqueue(new Callback<List<Student>>() {
            @Override
            public void onResponse(Call<List<Student>> call, Response<List<Student>> response) {
                List<Student> students = response.body();
                items.clear();
                items.addAll(students);
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<List<Student>> call, Throwable t) {
                Toast.makeText(MainActivity.this, "ERROR!", Toast.LENGTH_SHORT).show();
            }
        });

    }

}
