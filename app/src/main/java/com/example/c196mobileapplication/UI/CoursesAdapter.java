package com.example.c196mobileapplication.UI;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.c196mobileapplication.Entity.Courses;
import com.example.c196mobileapplication.R;

import java.util.List;

public class CoursesAdapter extends RecyclerView.Adapter<CoursesAdapter.CoursesViewHolder>{

    class CoursesViewHolder extends RecyclerView.ViewHolder{
        private final TextView coursesItemView;

        private CoursesViewHolder(View itemView){
            super (itemView);
            coursesItemView=itemView.findViewById(R.id.courseList);
            itemView.setOnClickListener(new View.OnClickListener(){

                @Override
                public void onClick(View view) {
                    int position=getAdapterPosition();
                    final Courses current=mCourses.get(position);
                    Intent intent= new Intent(context, ManagerCourse.class);
                    intent.putExtra("courseID", current.getCourseID());
                    intent.putExtra("courseTitle", current.getCourseTitle());
                    intent.putExtra("startDate", current.getStartDate());
                    intent.putExtra("endDate", current.getEndDate());
                    intent.putExtra("status", current.getStatus());
                    intent.putExtra("instructorName", current.getInstructorName());
                    intent.putExtra("instructorPhone", current.getInstructorPhone());
                    intent.putExtra("instructorEmail", current.getInstructorEmail());
                    intent.putExtra("associatedTerm", current.getAssociatedTerm());
                    context.startActivity(intent);
                }
            });
        }
    }

    private List<Courses> mCourses;
    private final Context context;
    private final LayoutInflater mInflater;

    public CoursesAdapter(Context context){
        mInflater=LayoutInflater.from(context);
        this.context=context;
    }

    @NonNull
    @Override
    public CoursesAdapter.CoursesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView=mInflater.inflate(R.layout.course_list,parent,false);

        return new CoursesAdapter.CoursesViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull CoursesAdapter.CoursesViewHolder holder, int position) {
        if(mCourses!=null){
            Courses current=mCourses.get(position);
            String coursesName = current.getCourseTitle();
            holder.coursesItemView.setText(current.getCourseTitle());
        }
        else{
            holder.coursesItemView.setText("No Thing Name");
        }
    }
    public void setCourses(List<Courses> courses){
        mCourses=courses;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        if(mCourses != null) {
            return mCourses.size();
        }
        else return 0;
    }
}
