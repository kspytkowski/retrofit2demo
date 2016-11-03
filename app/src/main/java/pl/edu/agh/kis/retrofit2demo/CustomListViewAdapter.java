package pl.edu.agh.kis.retrofit2demo;

import android.app.AlertDialog;
import android.app.FragmentManager;
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

import pl.edu.agh.kis.retrofit2demo.httpclient.StudentsService;
import pl.edu.agh.kis.retrofit2demo.model.Student;

/**
 * Created by Jakub Fortunka on 03.11.2016.
 */

public class CustomListViewAdapter<T> extends ArrayAdapter<T> {

    private FragmentManager fragmentManager;
    private StudentsService service;

    public CustomListViewAdapter(Context context, int resource) {
        super(context, resource);
    }

    public CustomListViewAdapter(Context context, int resource, List<T> items) {
        super(context, resource, items);
    }

    public CustomListViewAdapter(MainActivity mainActivity, int list_item, int student_list_item, List<T> items, FragmentManager fragmentManager, StudentsService service) {
        super(mainActivity, list_item, student_list_item, items);
        this.fragmentManager = fragmentManager;
        this.service = service;
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

        T item = getItem(position);

        if (item != null) {
            TextView tt1 = (TextView) v.findViewById(R.id.studentListItemTextView);

            if (tt1 != null) {
                tt1.setText(item.toString());
            }
        }

        View overflowButton = v.findViewById(R.id.popupMenuButton);
        overflowButton.setOnClickListener(new PopupMenuButtonOnClickListener(v.getContext(), (Student) item, fragmentManager, service));
        return v;
    }
}
