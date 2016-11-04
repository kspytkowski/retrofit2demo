package pl.edu.agh.kis.retrofit2demo;

import android.app.FragmentManager;
import android.content.Context;
import android.view.MenuItem;
import android.view.View;
import android.widget.PopupMenu;
import android.widget.Toast;

import pl.edu.agh.kis.retrofit2demo.httpclient.StudentsService;
import pl.edu.agh.kis.retrofit2demo.model.Student;

public class PopupMenuButtonOnClickListener implements View.OnClickListener {
    private final Student student;
    private final Context context;
    private final FragmentManager fragmentManager;
    private final StudentsService service;

    private static final String AUTH_KEY = "tajnehaslo";

    public PopupMenuButtonOnClickListener(Context context, Student student, FragmentManager fragmentManager, StudentsService service) {
        this.context = context;
        this.student = student;
        this.fragmentManager = fragmentManager;
        this.service = service;
    }

    @Override
    public void onClick(View view) {
        PopupMenu popupMenu = new PopupMenu(context, view);

        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.editPopupMenuItem:
                        StudentEditDialog sde = StudentEditDialog.newInstance(student, service);
                        sde.show(fragmentManager, "editDialog");
                        return true;
                    case R.id.removePopupMenuItem:
                        deleteStudent(student, AUTH_KEY);
                        return true;
                    default:
                        return true;
                }
            }
        });

        popupMenu.inflate(R.menu.popup_menu);

        popupMenu.show();
    }

    private void deleteStudent(Student student, String authKey) {
        //TODO use service to remove student
        //remember that remove has to have our authKey inside header
        Toast.makeText(context, "Remove student: " + student.toString(), Toast.LENGTH_SHORT).show();
    }
}
