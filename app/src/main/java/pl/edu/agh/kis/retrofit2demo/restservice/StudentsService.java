package pl.edu.agh.kis.retrofit2demo.restservice;

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

    //  TODO ćw.1.a
    //  Stwórz obiekt typu Retrofit (referencję nazwij retrofit), jako bazowy URL podaj
    //  "http://analyzer-asp2016.rhcloud.com/api/", natomiast do konwersji obiekt javowy <-> JSON
    //  zastosuj Gson'a.

    //  TODO ćw.1.c
    //  Napisz sygnaturę metody odpowiedzialnej za pobieranie listy studentów. Metoda powinna
    //  przyjmować mapę parametrów dołączanych do bazowego URLa, natomiast zwaracać obiekt
    //  typu Call parametryzowany listą studentów.
    //  GET http://analyzer-asp2016.rhcloud.com/api/students?sort=[asc/desc/anything(brak sortowania)]

    //  TODO ćw.2
    //  Napisz sygnaturę metody odpowiedzialnej za tworzenie nowego studenta. Metoda powinna
    //  przyjmować jako parametr obiekt typu Student i zwracać obiekt typu Call parametryzowany Studentem.
    //  POST http://analyzer-asp2016.rhcloud.com/api/students

    //  TODO ćw.2
    //  Napisz sygnaturę metody odpowiedzialnej za edytowanie istniejącego studenta. Metoda powinna
    //  przyjmować jako parametr id studenta oraz obiekt typu Student i zwracać obiekt typu Call
    //  parametryzowany Studentem.
    //  PUT http://analyzer-asp2016.rhcloud.com/api/students/{id}

    //  TODO ćw.3
    //  Napisz sygnaturę metody odpowiedzialnej za usuwanie istniejącego studenta. Metoda powinna
    //  przyjmować jako parametr id studenta i zwracać obiekt typu Call parametryzowany Studentem.
    //  Pamiętaj o konieczności dołączenia nagłówka Auth z wartością secret.
    //  DELETE http://analyzer-asp2016.rhcloud.com/api/students/{id} + Header "Auth: secret"

}
