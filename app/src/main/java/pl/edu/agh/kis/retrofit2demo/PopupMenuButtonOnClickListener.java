package pl.edu.agh.kis.retrofit2demo;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.view.menu.MenuBuilder;
import android.view.MenuItem;
import android.view.View;
import android.widget.PopupMenu;

import pl.edu.agh.kis.retrofit2demo.model.Student;

/**
 * Created by Jakub Fortunka on 03.11.2016.
 */
public class PopupMenuButtonOnClickListener implements View.OnClickListener {
    private final Student student;
    private final Context context;

    public PopupMenuButtonOnClickListener(Context context, Student student) {
        this.context = context;
        this.student = student;
    }

    @Override
    public void onClick(View view) {
        PopupMenu popupMenu = new PopupMenu(context, view);

        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.editPopupMenuItem:
                        //TODO create edit dialog
                        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);
                        alertDialogBuilder.setTitle(student.getName());
                        alertDialogBuilder.setMessage(student.toStringDetailed());
                        alertDialogBuilder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                            }
                        });
                        AlertDialog alertDialog = alertDialogBuilder.create();
                        alertDialog.show();
                        return true;
                    case R.id.removePopupMenuItem:
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
