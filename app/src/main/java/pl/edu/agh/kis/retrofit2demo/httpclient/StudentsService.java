package pl.edu.agh.kis.retrofit2demo.httpclient;

import java.util.List;
import java.util.Map;

import pl.edu.agh.kis.retrofit2demo.model.Student;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.QueryMap;

public interface StudentsService {

    Retrofit retrofit = new Retrofit
            .Builder()
            .baseUrl("http://analyzer-asp2016.rhcloud.com/api/")
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    @GET("students")
    Call<List<Student>> getStudents(@QueryMap Map<String, String> options);

    @POST("students")
    Call<Student> createStudent(@Body Student student);

    @PUT("students/{id}")
    Call<Student> updateStudent(@Path("id") String id, @Body Student student);

    @DELETE("students/{id}")
    @Headers("Auth: secret")
    Call<Student> deleteStudent(@Path("id") String id);
}
