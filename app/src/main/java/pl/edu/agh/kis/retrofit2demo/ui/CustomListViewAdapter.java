package pl.edu.agh.kis.retrofit2demo.ui;

import android.app.FragmentManager;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import pl.edu.agh.kis.retrofit2demo.R;
import pl.edu.agh.kis.retrofit2demo.exercises.MainActivity;
import pl.edu.agh.kis.retrofit2demo.exercises.PopupMenuButtonOnClickListener;
import pl.edu.agh.kis.retrofit2demo.model.Student;
import pl.edu.agh.kis.retrofit2demo.restservice.StudentsService;

public class CustomListViewAdapter<T> extends ArrayAdapter<T> {

    private FragmentManager fragmentManager;
    private StudentsService service;

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
