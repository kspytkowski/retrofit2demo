package pl.edu.agh.kis.retrofit2demo.model;

public class Student {

    private String id;
    private String name = "";
    private String language = "";

    public Student() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

//    @Override
//    public String toString() {
//        return "Student{" +
//                "id='" + id + '\'' +
//                ", name='" + name + '\'' +
//                ", language='" + language + '\'' +
//                '}';
//    }


    @Override
    public String toString() {
        return name;
    }

    public String toStringDetailed() {
        return "Name: " + name + "\n" + "Language: " + language;
    }
}

