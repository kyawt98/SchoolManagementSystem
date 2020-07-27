package com.kyawt.schooldb.timetable.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.kyawt.schooldb.R;
import com.kyawt.schooldb.course.CourseDetailActivity;
import com.kyawt.schooldb.model.CourseModel;
import com.kyawt.schooldb.model.TimetableModel;
import com.kyawt.schooldb.timetable.DetailTimetableActivity;

import java.util.ArrayList;

public class TimetableAdapter extends RecyclerView.Adapter<TimetableAdapter.TimetableViewHolder>{

    private ArrayList<TimetableModel> timetableModelArrayList;
    Context context;

    public TimetableAdapter(ArrayList<TimetableModel> timetableModelArrayList, Context context) {
        this.timetableModelArrayList = timetableModelArrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public TimetableViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_timetable,parent,false);
        TimetableViewHolder timetableViewHolder = new TimetableViewHolder(view);
        return timetableViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull TimetableViewHolder holder, final int position) {
        TextView course_label = holder.course_label;
        TextView txt_mon = holder.txt_mon;
        TextView txt_sub = holder.txt_sub;
        TextView txt_start_time = holder.txt_start_time;
        TextView txt_finish_time = holder.txt_finish_time;
        TextView txt_teacher_name = holder.txt_teacher_name;
        CardView card_timetable = holder.card_timetable;

        course_label.setText(timetableModelArrayList.get(position).day+" ");
        txt_mon.setText(timetableModelArrayList.get(position).course_name.toUpperCase().subSequence(0,3)+" ");
        txt_sub.setText(timetableModelArrayList.get(position).subject_name+" ");
        txt_start_time.setText(timetableModelArrayList.get(position).start_time+"");
        txt_finish_time.setText(timetableModelArrayList.get(position).finish_time+" ");
        txt_teacher_name.setText(timetableModelArrayList.get(position).teacher_name+" ");


        card_timetable.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int timetable_id = timetableModelArrayList.get(position).timetable_id;
                String course_name = timetableModelArrayList.get(position).course_name;
                String day = timetableModelArrayList.get(position).day;
                String subject_name = timetableModelArrayList.get(position).subject_name;
                String start_time = timetableModelArrayList.get(position).start_time;
                String finish_time = timetableModelArrayList.get(position).finish_time;
                String teacher_name = timetableModelArrayList.get(position).teacher_name;

                Intent intent= new Intent(context, DetailTimetableActivity.class);
                intent.putExtra("key_for_timetable_id", timetable_id);
                intent.putExtra("key_for_day", day);
                intent.putExtra("key_for_course_name", course_name);
                intent.putExtra("key_for_subject_name", subject_name);
                intent.putExtra("key_for_start_time", start_time);
                intent.putExtra("key_for_finish_time", finish_time);
                intent.putExtra("key_for_teacher_name", teacher_name);
                context.startActivity(intent);

            }
        });
    }

    @Override
    public int getItemCount() {
        return timetableModelArrayList.size();
    }

    //    ................ViewHolder start..............................

    public static class TimetableViewHolder extends RecyclerView.ViewHolder{
        TextView course_label;
        TextView txt_mon;
        TextView txt_sub;
        TextView txt_start_time;
        TextView txt_finish_time;
        TextView txt_teacher_name;
        CardView card_timetable;

        public TimetableViewHolder(View itemView){
            super(itemView);
            this.course_label = (TextView) itemView.findViewById(R.id.course_label);
            this.txt_mon = (TextView) itemView.findViewById(R.id.txt_mon);
            this.txt_sub = (TextView) itemView.findViewById(R.id.txt_sub);
            this.txt_start_time = (TextView) itemView.findViewById(R.id.txt_start_time);
            this.txt_finish_time = (TextView) itemView.findViewById(R.id.txt_finish_time);
            this.txt_teacher_name = (TextView) itemView.findViewById(R.id.txt_teacher_name);
            this.card_timetable = (CardView) itemView.findViewById(R.id.card_timetable);
        }
    }

    //    ................ViewHolder end..............................
}
