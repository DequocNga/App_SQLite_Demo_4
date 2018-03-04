package com.russia.app_sqlite_demo_4.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.russia.app_sqlite_demo_4.R;
import com.russia.app_sqlite_demo_4.model.Student;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by VLADIMIR PUTIN on 3/4/2018.
 */

public class Custom_Adapter extends ArrayAdapter<Student> {

    Context context;
    int resource;
    ArrayList<Student> studentArrayList = new ArrayList<>();

    public Custom_Adapter(@NonNull Context context, int resource, @NonNull ArrayList<Student> objects) {
        super(context, resource, objects);
        this.context = context;
        this.resource = resource;
        this.studentArrayList = objects;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        ViewHolder viewHolder = new ViewHolder();
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.custom_listview, parent, false);
            viewHolder.textViewID = convertView.findViewById(R.id.tv_idSV);
            viewHolder.textViewName = convertView.findViewById(R.id.tv_nameSV);
            viewHolder.textViewPhone = convertView.findViewById(R.id.tv_phoneSV);
            viewHolder.textViewEmail = convertView.findViewById(R.id.tv_emailSV);
            viewHolder.textViewAddress = convertView.findViewById(R.id.tv_addressSV);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        Student student = studentArrayList.get(position);
        viewHolder.textViewID.setText(String.valueOf(student.getIdSV()));
        viewHolder.textViewName.setText(student.getNameSV());
        viewHolder.textViewPhone.setText(student.getPhoneSV());
        viewHolder.textViewEmail.setText(student.getEmailSV());
        viewHolder.textViewAddress.setText(student.getAddressSV());

        return convertView;
    }

    public class ViewHolder {
        TextView textViewID;
        TextView textViewName;
        TextView textViewPhone;
        TextView textViewEmail;
        TextView textViewAddress;
    }
}
