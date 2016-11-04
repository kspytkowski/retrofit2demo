package pl.edu.agh.kis.retrofit2demo.httpclient;

import java.util.List;

import pl.edu.agh.kis.retrofit2demo.model.Student;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;

public interface StudentsService {

    @GET("students")
    Call<List<Student>> getStudents();

    Retrofit retrofit = new Retrofit
            .Builder()
            .baseUrl("http://analyzer-asp2016.rhcloud.com/api/")
            .addConverterFactory(GsonConverterFactory.create())
            .build();
}
