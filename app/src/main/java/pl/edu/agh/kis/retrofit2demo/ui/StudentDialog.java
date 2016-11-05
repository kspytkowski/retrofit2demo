package pl.edu.agh.kis.retrofit2demo.ui;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;

import pl.edu.agh.kis.retrofit2demo.model.Student;

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
        alertDialogBuilder.setPositiveButton("OK", null);
        return alertDialogBuilder.create();
    }
}
