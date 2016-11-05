package pl.edu.agh.kis.retrofit2demo.restservice;

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
