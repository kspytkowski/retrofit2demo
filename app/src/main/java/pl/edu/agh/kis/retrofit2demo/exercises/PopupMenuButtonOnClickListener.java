package pl.edu.agh.kis.retrofit2demo.exercises;

import android.app.FragmentManager;
import android.content.Context;
import android.view.MenuItem;
import android.view.View;
import android.widget.PopupMenu;

import pl.edu.agh.kis.retrofit2demo.R;
import pl.edu.agh.kis.retrofit2demo.model.Student;
import pl.edu.agh.kis.retrofit2demo.restservice.StudentsService;

public class PopupMenuButtonOnClickListener implements View.OnClickListener {
    private final Student student;
    private final Context context;
    private final FragmentManager fragmentManager;
    private final StudentsService service;

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
                        StudentEditDialog sde = StudentEditDialog.newInstance(context, student, service);
                        sde.show(fragmentManager, "editDialog");
                        return true;
                    case R.id.removePopupMenuItem:
                        deleteStudent(student);
                        return true;
                    default:
                        return true;
                }
            }
        });
        popupMenu.inflate(R.menu.popup_menu);
        popupMenu.show();
    }

    private void deleteStudent(final Student student) {
        //  TODO ćw.3
        //  Wywołaj odpowiednią metodę z StudentsService (DELETE) i wyświetl użytkownikowi informację
        //  o sukcesie lub zaistniałym błędzie (użyj Toast)

    }

}
