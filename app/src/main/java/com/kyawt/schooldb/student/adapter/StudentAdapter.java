package com.kyawt.schooldb.student.adapter;

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

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.kyawt.schooldb.R;
import com.kyawt.schooldb.model.StudentModel;
import com.kyawt.schooldb.model.TeacherModel;
import com.kyawt.schooldb.student.StudentDetailActivity;
import com.kyawt.schooldb.teacher.TeacherDetailActivity;

import java.util.ArrayList;
import java.util.Random;

public class StudentAdapter extends RecyclerView.Adapter<StudentAdapter.StudentViewHolder> {

    private ArrayList<StudentModel> studentModelArrayList;
    Context context;

    public StudentAdapter(ArrayList<StudentModel> studentModelArrayList, Context context) {
        this.context = context;
        this.studentModelArrayList = studentModelArrayList;
    }

    @NonNull
    @Override
    public StudentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_student, parent, false);
        StudentViewHolder studentViewHolder = new StudentViewHolder(view);
        return studentViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull StudentViewHolder holder, final int position) {
        TextView txt_student_name = holder.txt_student_name;
        TextView txt_student_ph = holder.txt_student_ph;
        ImageView img_gender = holder.img_gender;
        ImageView img_call = holder.img_call;
        Button btn_title = holder.btn_title;
        LinearLayout ll_item_layout = holder.ll_item_layout;

//        txt_teacher_id.setText(teacherModelArrayList.get(position).teacher_id);
        txt_student_name.setText(studentModelArrayList.get(position).student_name + " ");
        txt_student_ph.setText(studentModelArrayList.get(position).student_phone + " ");

        if (studentModelArrayList.get(position).student_gender.equalsIgnoreCase("male")) {
            img_gender.setImageResource(R.drawable.ic_male);
        } else if (studentModelArrayList.get(position).student_gender.equalsIgnoreCase("female")) {
            img_gender.setImageResource(R.drawable.ic_female);
        }

//        ................. Logic Title Start.......................

        btn_title.setText(studentModelArrayList.get(position).student_name.toUpperCase().charAt(0) + "");
//        ................. Random color......................
        Random random = new Random();
        int red = random.nextInt(255);
        final int green = random.nextInt(255);
        int blue = random.nextInt(255);

        btn_title.setBackgroundColor(Color.rgb(red, green, blue));

        if (studentModelArrayList.get(position).student_phone.length() < 12) {

            img_call.setVisibility(View.VISIBLE);
            img_call.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + studentModelArrayList.get(position).student_phone));
                    context.startActivity(intent);
                }
            });
        } else {
            img_call.setVisibility(View.GONE);
        }

        ll_item_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int student_id = studentModelArrayList.get(position).student_id;
                String student_name = studentModelArrayList.get(position).student_name;
                String student_ph = studentModelArrayList.get(position).student_phone;
                String student_gender = studentModelArrayList.get(position).student_gender;

                Intent intent = new Intent(context, StudentDetailActivity.class);

                intent.putExtra("key_for_student_id", student_id);
                intent.putExtra("key_for_student_name", student_name);
                intent.putExtra("key_for_student_ph", student_ph);
                intent.putExtra("key_for_student_gender", student_gender);
                intent.putExtra("key_for_student_nrc", studentModelArrayList.get(position).student_nrc);
                intent.putExtra("key_for_student_dob", studentModelArrayList.get(position).student_dob);
                intent.putExtra("key_for_student_email", studentModelArrayList.get(position).student_email);
                intent.putExtra("key_for_student_address", studentModelArrayList.get(position).student_address);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return studentModelArrayList.size();
    }

    //    ................ViewHolder start..............................

    public static class StudentViewHolder extends RecyclerView.ViewHolder {
        TextView txt_student_name;
        TextView txt_student_ph;
        ImageView img_gender;
        ImageView img_call;
        Button btn_title;
        LinearLayout ll_item_layout;

        public StudentViewHolder(View itemView) {
            super(itemView);
            this.txt_student_name = (TextView) itemView.findViewById(R.id.txt_stu_name_item);
            this.txt_student_ph = (TextView) itemView.findViewById(R.id.txt_stu_ph_item);
            this.img_gender = (ImageView) itemView.findViewById(R.id.img_stu_gender_item);
            this.img_call = (ImageView) itemView.findViewById(R.id.img_call_item);
            this.btn_title = (Button) itemView.findViewById(R.id.btn_title);
            this.ll_item_layout = (LinearLayout) itemView.findViewById(R.id.ll_item_layout);
        }
    }

    //    ................ViewHolder end..............................
}

