package pl.edu.agh.kis.retrofit2demo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.List;

import pl.edu.agh.kis.retrofit2demo.httpclient.Gateway;
import pl.edu.agh.kis.retrofit2demo.model.Student;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    Button btnGetAllStudents;

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
    }

    private void getStudents() {
        Retrofit retrofit = new Retrofit
                .Builder()
                .baseUrl("http://analyzer-asp2016.rhcloud.com/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        Gateway gateway = retrofit.create(Gateway.class);

        Call<List<Student>> call = gateway.getStudents();

        call.enqueue(new Callback<List<Student>>() {
            @Override
            public void onResponse(Call<List<Student>> call, Response<List<Student>> response) {
                List<Student> students = response.body();
                String result = "";
                for (int i = 0; i < students.size(); i++) {
                    String id = students.get(i).getId();
                    String name = students.get(i).getName();
                    String language = students.get(i).getLanguage();
                    result += "Id: " + id + "\n" +
                            "Name: " + name + "\n" +
                            "Language: " + language + "\n\n";
                }
                Toast.makeText(MainActivity.this, result, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<List<Student>> call, Throwable t) {
                Toast.makeText(MainActivity.this, "ERROR!", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
