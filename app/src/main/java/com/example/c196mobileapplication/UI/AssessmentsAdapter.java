package com.example.c196mobileapplication.UI;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.c196mobileapplication.Entity.Assessments;
import com.example.c196mobileapplication.R;

import java.util.List;

public class AssessmentsAdapter extends RecyclerView.Adapter<AssessmentsAdapter.AssessmentsViewHolder>{

        class AssessmentsViewHolder extends RecyclerView.ViewHolder{
            private final TextView assessmentsItemView;

            private AssessmentsViewHolder(View itemView){
                super (itemView);
                assessmentsItemView=itemView.findViewById(R.id.assessmentsList);
                itemView.setOnClickListener(new View.OnClickListener(){

                    @Override
                    public void onClick(View view) {
                        int position=getAdapterPosition();
                        final Assessments current=mAssessments.get(position);
                        Intent intent= new Intent(context, ManagerAssessment.class);
                        intent.putExtra("assessmentID", current.getAssessmentID());
                        intent.putExtra("assessment", current.getAssessment());
                        intent.putExtra("startDate", current.getStartDate());
                        intent.putExtra("endDate", current.getEndDate());
                        intent.putExtra("type", current.getType());

                        context.startActivity(intent);
                    }
                });
            }
        }

        private List<Assessments> mAssessments;
        private final Context context;
        private final LayoutInflater mInflater;

        public AssessmentsAdapter(Context context){
            mInflater=LayoutInflater.from(context);
            this.context=context;
        }

        @NonNull
        @Override
        public AssessmentsAdapter.AssessmentsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View itemView=mInflater.inflate(R.layout.assessments_list,parent,false);

            return new AssessmentsAdapter.AssessmentsViewHolder(itemView);
        }

        @Override
        public void onBindViewHolder(@NonNull AssessmentsAdapter.AssessmentsViewHolder holder, int position) {
            if(mAssessments!=null){
                Assessments current=mAssessments.get(position);
                String assessmentsName = current.getAssessment();
                holder.assessmentsItemView.setText(current.getAssessment());
            }
            else{
                holder.assessmentsItemView.setText("No Thing Name");
            }
        }
        public void setAssessments(List<Assessments> assessments){
            mAssessments=assessments;
            notifyDataSetChanged();
        }

        @Override
        public int getItemCount() {
            if(mAssessments != null) {
                return mAssessments.size();
            }
            else return 0;
        }
    }



