package pl.edu.agh.kis.retrofit2demo;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;

import pl.edu.agh.kis.retrofit2demo.model.Student;

/**
 * Created by Jakub Fortunka on 03.11.2016.
 */

public class StudentDialog extends DialogFragment {

    private Student student;

    public void setStudent(Student student) {
        this.student = student;
    }

    public static StudentDialog newInstance(Student student) {
        StudentDialog sd = new StudentDialog();
        sd.setStudent(student);
        return sd;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(getActivity());
        alertDialogBuilder.setTitle(student.getName());
        alertDialogBuilder.setMessage(student.toStringDetailed());
        alertDialogBuilder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
            }
        });
        return alertDialogBuilder.create();
    }
}
