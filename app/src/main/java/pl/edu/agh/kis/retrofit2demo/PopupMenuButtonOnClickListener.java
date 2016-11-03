package pl.edu.agh.kis.retrofit2demo;

import android.app.FragmentManager;
import android.content.Context;
import android.view.MenuItem;
import android.view.View;
import android.widget.PopupMenu;

import pl.edu.agh.kis.retrofit2demo.httpclient.StudentsService;
import pl.edu.agh.kis.retrofit2demo.model.Student;

/**
 * Created by Jakub Fortunka on 03.11.2016.
 */
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
                        StudentEditDialog sde = StudentEditDialog.newInstance(student, service);
                        sde.show(fragmentManager, "editDialog");
                        return true;
                    case R.id.removePopupMenuItem:
                        //TODO use service to remove student
                        return true;
                    default:
                        return true;
                }
            }
        });

        popupMenu.inflate(R.menu.popup_menu);

        popupMenu.show();
    }
}
