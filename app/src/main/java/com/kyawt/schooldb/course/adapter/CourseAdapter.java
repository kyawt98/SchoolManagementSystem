package com.kyawt.schooldb.course.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.kyawt.schooldb.R;
import com.kyawt.schooldb.course.CourseDetailActivity;
import com.kyawt.schooldb.model.CourseModel;
import com.kyawt.schooldb.registration.AddRegistrationActivity;

import java.util.ArrayList;
import java.util.Random;

public class CourseAdapter extends RecyclerView.Adapter<CourseAdapter.CourseViewHolder> {

    private ArrayList<CourseModel> courseModelArrayList;
    Context context;

    public CourseAdapter(ArrayList<CourseModel> courseModelArrayList, Context context){
        this.context = context;
        this.courseModelArrayList = courseModelArrayList;
    }

    @NonNull
    @Override
    public CourseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_course,parent,false);
        CourseViewHolder courseViewHolder = new CourseViewHolder(view);
        return courseViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull CourseViewHolder holder, final int position) {
        TextView txt_course_code = holder.txt_course_code;
        TextView txt_course_name = holder.txt_course_name;
        ConstraintLayout ll_item_layout = holder.ll_item_layout;

        txt_course_code.setText(courseModelArrayList.get(position).class_code+" ");
        txt_course_name.setText(courseModelArrayList.get(position).class_name+" ");

        ll_item_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int course_code = courseModelArrayList.get(position).class_code;
                String course_name = courseModelArrayList.get(position).class_name;

                Toast.makeText(context, course_code+"\n"+ course_name, Toast.LENGTH_LONG).show();

                Intent intent= new Intent(context, CourseDetailActivity.class);
                intent.putExtra("key_for_course_code", course_code);
                intent.putExtra("key_for_course_name", course_name);
                context.startActivity(intent);

            }
        });
    }

    @Override
    public int getItemCount() {
        return courseModelArrayList.size();
    }

    //    ................ViewHolder start..............................

    public static class CourseViewHolder extends RecyclerView.ViewHolder{
        TextView txt_course_code;
        TextView txt_course_name;
        ConstraintLayout ll_item_layout;

        public CourseViewHolder(View itemView){
            super(itemView);
            this.txt_course_code = (TextView) itemView.findViewById(R.id.txtCourseCodeItem);
            this.txt_course_name = (TextView) itemView.findViewById(R.id.txtCourseNameItem);
            this.ll_item_layout = (ConstraintLayout) itemView.findViewById(R.id.item_layout);
        }
    }

    //    ................ViewHolder end..............................
}
