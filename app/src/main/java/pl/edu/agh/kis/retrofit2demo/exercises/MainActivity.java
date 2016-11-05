package pl.edu.agh.kis.retrofit2demo.exercises;

import android.app.FragmentManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import pl.edu.agh.kis.retrofit2demo.R;
import pl.edu.agh.kis.retrofit2demo.model.Student;
import pl.edu.agh.kis.retrofit2demo.restservice.StudentsService;
import pl.edu.agh.kis.retrofit2demo.ui.CustomListViewAdapter;
import pl.edu.agh.kis.retrofit2demo.ui.StudentDialog;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private CustomListViewAdapter<Student> adapter;
    private List<Student> items = new ArrayList<>();
    //  TODO ćw.1.b
    //  Podejrzyj (CTRL + spacja na nazwie metody) opis i zachowanie metody create.
    private StudentsService service = StudentsService.retrofit.create(StudentsService.class);
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
        adapter = new CustomListViewAdapter<>(this, R.layout.list_item, R.id.studentListItemTextView, items, fm, service);
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
        //  TODO ćw.1.c
        //  Stwórz mapę parametrów dołączanych do bazowego URLa i umieść w niej wpis pozwalający
        //  na pobieranie listy studentów (po ich imionach) w kolejności alfabetycznej (od A do Z).
        //  Mapę tę przekaż do odpowiedniej metody z StudentsService (GET):
        //  a) w przypadku sukcesu - wyłuskaj listę studentów z odpowiedzi i przekaż ją do dalszego
        //  procesowania do metody processListOfStudents(List<Student> students)
        //  b) w przypadku błędu - wyświetl użytkownikowi stosowną informację (użyj Toast)
        Map<String, String> options = new HashMap<>();
        options.put("sort", "asc"); //asc or desc or anything (no order)
        Call<List<Student>> call = service.getStudents(options);
        call.enqueue(new Callback<List<Student>>() {
            @Override
            public void onResponse(Call<List<Student>> call, Response<List<Student>> response) {
                List<Student> students = response.body();
                processListOfStudents(students);
            }

            @Override
            public void onFailure(Call<List<Student>> call, Throwable t) {
                Toast.makeText(MainActivity.this, "ERROR! Could not get students from server", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void processListOfStudents(List<Student> students) {
        items.clear();
        items.addAll(students);
        adapter.notifyDataSetChanged();
    }

    public void showNewStudentDialog(View view) {
        StudentEditDialog sed = StudentEditDialog.newInstance(MainActivity.this, new Student(), service);
        sed.show(fm, "newStudentDialog");
    }
}
