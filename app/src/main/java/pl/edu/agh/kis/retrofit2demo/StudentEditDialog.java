package pl.edu.agh.kis.retrofit2demo;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import pl.edu.agh.kis.retrofit2demo.httpclient.StudentsService;
import pl.edu.agh.kis.retrofit2demo.model.Student;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class StudentEditDialog extends DialogFragment {

    private Student student;
    private StudentsService service;
    private Context context;

    public void setStudent(Student student) {
        this.student = student;
    }

    public void setService(StudentsService service) {
        this.service = service;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public StudentEditDialog() {
    }

    public static StudentEditDialog newInstance(Context context, Student student, StudentsService service) {
        StudentEditDialog sed = new StudentEditDialog();
        sed.setContext(context);
        sed.setStudent(student);
        sed.setService(service);
        return sed;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        builder.setTitle("Edit " + student.getName());
        final View dialogView = inflater.inflate(R.layout.student_dialog, null);
        ((EditText) dialogView.findViewById(R.id.studentNameEditText)).append(student.getName());
        ((EditText) dialogView.findViewById(R.id.studentLanguageEditText)).append(student.getLanguage());
        builder.setView(dialogView)
                .setPositiveButton("OK", null)
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        StudentEditDialog.this.getDialog().cancel();
                    }
                });
        final AlertDialog ad = builder.create();
        ad.setOnShowListener(new DialogInterface.OnShowListener() {
            @Override
            public void onShow(DialogInterface dialogInterface) {
                Button b = ((AlertDialog) dialogInterface).getButton(AlertDialog.BUTTON_POSITIVE);
                b.setOnClickListener(new View.OnClickListener() {

                    @Override
                    public void onClick(View view) {
                        EditText studentNameEdit = (EditText) dialogView.findViewById(R.id.studentNameEditText);
                        EditText studentLanguageEdit = (EditText) dialogView.findViewById(R.id.studentLanguageEditText);
                        if (isFieldNotEmpty(studentNameEdit) && isFieldNotEmpty(studentLanguageEdit)) {
                            student.setName(studentNameEdit.getText().toString());
                            student.setLanguage(studentLanguageEdit.getText().toString());
                            saveStudent(student);
                            ad.dismiss();
                        } else {
                            Toast.makeText(getContext(), "Fields cannot be empty", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });
        return ad;
    }

    private void saveStudent(final Student student) {
//        TODO ćw.2
//        Wywołaj odpowiednie metody z StudentsService (POST, PUT) i wyświetl użytkownikowi informację
//        o sukcesie lub zaistniałym błędzie (użyj Toast). Musisz tutaj zdecydować czy będziesz
//        tworzył nowego Studenta, czy może edytował już istniejącego (pomocne może okazać się pole
//        id klasy Student)
        if (student.getId() == null) {
            Call<Student> call = service.createStudent(student);
            call.enqueue(new Callback<Student>() {
                @Override
                public void onResponse(Call<Student> call, Response<Student> response) {
                    Student createdStudent = response.body();
                    Toast.makeText(context, "Created student: " + createdStudent, Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onFailure(Call<Student> call, Throwable t) {
                    Toast.makeText(context, "ERROR! Could not create student: " + student, Toast.LENGTH_SHORT).show();
                }
            });
        } else {
            Call<Student> call = service.updateStudent(student.getId(), student);
            call.enqueue(new Callback<Student>() {
                @Override
                public void onResponse(Call<Student> call, Response<Student> response) {
                    Toast.makeText(context, "Updated student: " + student, Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onFailure(Call<Student> call, Throwable t) {
                    Toast.makeText(context, "ERROR! Could not update student: " + student, Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

    private boolean isFieldNotEmpty(EditText field) {
        return !field.getText().toString().trim().isEmpty();
    }
}
