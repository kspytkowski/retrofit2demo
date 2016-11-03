package pl.edu.agh.kis.retrofit2demo;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import pl.edu.agh.kis.retrofit2demo.model.Student;

/**
 * Created by Jakub Fortunka on 03.11.2016.
 */

public class CustomListViewAdapter extends ArrayAdapter {

    public CustomListViewAdapter(Context context, int resource) {
        super(context, resource);
    }

    public CustomListViewAdapter(Context context, int resource, List<Student> items) {
        super(context, resource, items);
    }

    public CustomListViewAdapter(MainActivity mainActivity, int list_item, int student_list_item, List<Student> items) {
        super(mainActivity, list_item, student_list_item, items);
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = convertView;
        if (v == null) {
            LayoutInflater vi;
            vi = LayoutInflater.from(getContext());
            v = vi.inflate(R.layout.list_item, null);
        }

        Student student = (Student) getItem(position);

        if (student != null) {
            TextView tt1 = (TextView) v.findViewById(R.id.studentListItemTextView);

            if (tt1 != null) {
                tt1.setText(student.toString());
            }
        }
        View overflowButton = v.findViewById(R.id.popupMenuButton);
        overflowButton.setOnClickListener(new PopupMenuButtonOnClickListener(v.getContext(),student));
        return v;
    }
}
