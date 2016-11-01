package pl.edu.agh.kis.retrofit2demo.httpclient;

import java.util.List;

import pl.edu.agh.kis.retrofit2demo.model.Student;
import retrofit2.Call; //TODO Add to presentation
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET; //TODO Add to presentation
import retrofit2.http.Path;

public interface StudentsService {

    @GET("students")
    Call<List<Student>> getStudents();

    @GET("students/{studentName}")
    Call<Student> getStudent(@Path("studentName") String studentName);

    Retrofit retrofit = new Retrofit
            .Builder()
            .baseUrl("http://analyzer-asp2016.rhcloud.com/api/")
            .addConverterFactory(GsonConverterFactory.create())
            .build();
}
