package pl.edu.agh.kis.retrofit2demo;

import android.app.FragmentManager;
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

    private CustomListViewAdapter<Student> adapter;

    private List<Student> items = new ArrayList<>();

    private StudentsService studentsService = StudentsService.retrofit.create(StudentsService.class);

    private FragmentManager fm = getFragmentManager();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnGetAllStudents = (Button) findViewById(R.id.btnGetAllStudents);

        btnGetAllStudents.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getStudents();
            }
        });

        ListView lv = (ListView) findViewById(R.id.studentsListView);

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
                processListOfStudents(students);
            }

            @Override
            public void onFailure(Call<List<Student>> call, Throwable t) {
                Toast.makeText(MainActivity.this, "ERROR!", Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void processListOfStudents(List<Student> students) {
        items.clear();
        items.addAll(students);
        adapter.notifyDataSetChanged();
    }

    public void showNewStudentDialog(View view) {
        StudentEditDialog sed = StudentEditDialog.newInstance(new Student(), studentsService);
        sed.show(fm, "newStudentDialog");
    }

}
