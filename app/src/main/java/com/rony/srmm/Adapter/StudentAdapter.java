package com.rony.srmm.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.rony.srmm.R;
import com.rony.srmm.Response.StudentResponse;

public class StudentAdapter extends RecyclerView.Adapter<StudentAdapter.StudentViewHolder> {

    Context context;
    StudentResponse studentResponse;

    public StudentAdapter(Context context, StudentResponse studentResponse) {
        this.context = context;
        this.studentResponse = studentResponse;
    }

    @NonNull
    @Override
    public StudentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.student_sample_layout, parent, false);
        return new StudentViewHolder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull StudentViewHolder holder, int position) {
        if (studentResponse.student.size() > 0){
            holder.studentNameTextView.setText(studentResponse.student.get(position).name);
            holder.studentRollTextView.setText(String.valueOf(studentResponse.student.get(position).roll));
            holder.studentClassTextView.setText("Class "+studentResponse.student.get(position).student_class);
            holder.studentGenderTextView.setText(studentResponse.student.get(position).gender);
        }

    }

    @Override
    public int getItemCount() {
        return studentResponse.student.size();
    }

    public class StudentViewHolder extends RecyclerView.ViewHolder {

        TextView studentNameTextView, studentRollTextView, studentClassTextView, studentGenderTextView;

        public StudentViewHolder(@NonNull View itemView) {
            super(itemView);

            studentNameTextView = itemView.findViewById(R.id.studentNameTextView);
            studentRollTextView = itemView.findViewById(R.id.studentRollTextView);
            studentClassTextView = itemView.findViewById(R.id.studentClassTextView);
            studentGenderTextView = itemView.findViewById(R.id.studentGenderTextView);

        }
    }

}
