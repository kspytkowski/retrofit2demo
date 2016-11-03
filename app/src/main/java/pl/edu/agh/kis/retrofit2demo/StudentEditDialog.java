package pl.edu.agh.kis.retrofit2demo;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import pl.edu.agh.kis.retrofit2demo.httpclient.StudentsService;
import pl.edu.agh.kis.retrofit2demo.model.Student;

/**
 * Created by Jakub Fortunka on 03.11.2016.
 */

public class StudentEditDialog extends DialogFragment {

    private Student student;

    private StudentsService service;

    public void setStudent(Student student) {
        this.student = student;
    }

    public void setService(StudentsService service) {
        this.service = service;
    }

    public StudentEditDialog() {

    }

    public static StudentEditDialog newInstance(Student student, StudentsService service) {
        StudentEditDialog sed = new StudentEditDialog();
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
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        EditText studentNameEdit = (EditText) dialogView.findViewById(R.id.studentNameEditText);
                        EditText studentLanguageEdit = (EditText) dialogView.findViewById(R.id.studentLanguageEditText);
                        student.setName(studentNameEdit.getText().toString());
                        student.setLanguage(studentLanguageEdit.getText().toString());
                        //TODO user service to save changes
                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        StudentEditDialog.this.getDialog().cancel();
                    }
                });
        return builder.create();
    }
}
