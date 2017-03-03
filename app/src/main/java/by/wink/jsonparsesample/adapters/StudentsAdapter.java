package by.wink.jsonparsesample.adapters;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import by.wink.jsonparsesample.services.ImageDownloaderTask;
import by.wink.jsonparsesample.R;
import by.wink.jsonparsesample.models.Student;

/**
 * Created by amine on 27/02/17.
 */

public class StudentsAdapter extends RecyclerView.Adapter<StudentsAdapter.StudentViewHolder> {

    private ArrayList<Student> dataSet = new ArrayList<>();

    @Override
    public StudentsAdapter.StudentViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_student, parent, false);
        StudentViewHolder holder = new StudentViewHolder(v);

        return holder;
    }

    @Override
    public void onBindViewHolder(StudentsAdapter.StudentViewHolder holder, int position) {
        Student currentStudent = dataSet.get(position);
        holder.studentNameTv.setText(currentStudent.getName());
        holder.studentEmail.setText(currentStudent.getEmail());
        new ImageDownloaderTask(holder.studentImage).execute(currentStudent.getImageUrl());


    }


    public void setDataSet(ArrayList<Student> students) {
        dataSet = students;
        notifyDataSetChanged();

    }

    @Override
    public int getItemCount() {
        return dataSet.size();
    }

    class StudentViewHolder extends RecyclerView.ViewHolder {
        public TextView studentNameTv;
        public TextView studentEmail;
        public ImageButton studenGithub;
        public ImageView studentImage;

        public StudentViewHolder(final View v) {
            super(v);
            studentNameTv = (TextView) v.findViewById(R.id.student_name);
            studentEmail = (TextView) v.findViewById(R.id.student_email);
            studenGithub = (ImageButton) v.findViewById(R.id.student_github);
            studentImage = (ImageView)v.findViewById(R.id.student_image);

            studenGithub.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    Intent i = new Intent();
                    i.setAction(Intent.ACTION_VIEW);
                    i.setData(Uri.parse(dataSet.get(getAdapterPosition()).getGithub()));
                    itemView.getContext().startActivity(i);


                }
            });
        }


    }
}
