package com.example.studentrating;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

public class StudentAdapter extends ArrayAdapter<Student> {


    public StudentAdapter(@NonNull Context context, ArrayList<Student> students) {
        super(context, R.layout.adapter_item, students);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        final Student student = getItem(position);

        if(convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.adapter_item, null);
        }

        ((TextView) convertView.findViewById(R.id.tv_adapter_name)).setText(student.getName());
        ((TextView) convertView.findViewById(R.id.tv_adapter_group)).setText(student.getGroup());
        ((TextView) convertView.findViewById(R.id.tv_adapter_score)).setText(student.getScore().toString());

        return  convertView;

    }
}
