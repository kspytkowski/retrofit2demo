package pl.edu.agh.kis.retrofit2demo.httpclient;

import java.util.List;

import pl.edu.agh.kis.retrofit2demo.model.Student;
import retrofit2.Call; //TODO Add to presentation
import retrofit2.http.GET; //TODO Add to presentation

public interface Gateway {

    @GET("students")
    Call<List<Student>> getStudents();

}
