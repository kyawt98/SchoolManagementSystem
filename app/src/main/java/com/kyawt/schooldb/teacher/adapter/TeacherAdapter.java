package com.kyawt.schooldb.teacher.adapter;

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
import com.kyawt.schooldb.model.CourseModel;
import com.kyawt.schooldb.model.TeacherModel;
import com.kyawt.schooldb.teacher.TeacherDetailActivity;

import java.util.ArrayList;
import java.util.Random;

public class TeacherAdapter extends RecyclerView.Adapter<TeacherAdapter.TeacherViewHolder> {

    private ArrayList<TeacherModel> teacherModelArrayList;
    Context context;


    public TeacherAdapter(ArrayList<TeacherModel> teacherModelArrayList, Context context){
        this.context = context;
        this.teacherModelArrayList = teacherModelArrayList;
    }


    @NonNull
    @Override
    public TeacherViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_teacher,parent,false);
        TeacherViewHolder teacherViewHolder = new TeacherViewHolder(view);
        return teacherViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull TeacherViewHolder holder, final int position) {

//        TextView txt_teacher_id =holder.txt_teacher_id;
        TextView txt_teacher_name = holder.txt_teacher_name;
        TextView txt_teacher_ph = holder.txt_teacher_ph;
        ImageView img_gender = holder.img_gender;
        ImageView img_call = holder.img_call;
        Button btn_title = holder.btn_title;
        LinearLayout ll_item_layout = holder.ll_item_layout;

//        txt_teacher_id.setText(teacherModelArrayList.get(position).teacher_id);
        txt_teacher_name.setText(teacherModelArrayList.get(position).teacher_name+" ");
        txt_teacher_ph.setText(teacherModelArrayList.get(position).teacher_ph+" ");

        if (teacherModelArrayList.get(position).teacher_gender.equalsIgnoreCase("male")){
            img_gender.setImageResource(R.drawable.ic_male);
        }else if (teacherModelArrayList.get(position).teacher_gender.equalsIgnoreCase("female")){
            img_gender.setImageResource(R.drawable.ic_female);
        }

//        ................. Logic Title Start.......................

        btn_title.setText(teacherModelArrayList.get(position).teacher_name.toUpperCase().charAt(0)+"");
//        ................. Random color......................
        Random random = new Random();
        int red = random.nextInt(255);
        final int green =  random.nextInt(255);
        int blue = random.nextInt(255);

        btn_title.setBackgroundColor(Color.rgb(red,green,blue));

        if (teacherModelArrayList.get(position).teacher_ph.length() <12) {

            img_call.setVisibility(View.VISIBLE);
            img_call.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + teacherModelArrayList.get(position).teacher_ph));
                    context.startActivity(intent);
                }
            });
        }else{
            img_call.setVisibility(View.GONE);
        }

        ll_item_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String teacher_name = teacherModelArrayList.get(position).teacher_name;
                String teacher_ph = teacherModelArrayList.get(position).teacher_ph;
                String teacher_gender = teacherModelArrayList.get(position).teacher_gender;

                Intent intent= new Intent(context, TeacherDetailActivity.class);
                intent.putExtra("key_for_teacher_name", teacher_name);
                intent.putExtra("key_for_teacher_ph", teacher_ph);
                intent.putExtra("key_for_teacher_gender", teacher_gender);
                intent.putExtra("key_for_teacher_nrc", teacherModelArrayList.get(position).teacher_nrc);
                intent.putExtra("key_for_teacher_dob", teacherModelArrayList.get(position).teacher_dob);
                intent.putExtra("key_for_teacher_email", teacherModelArrayList.get(position).teacher_email);
                intent.putExtra("key_for_teacher_address", teacherModelArrayList.get(position).teacher_address);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return teacherModelArrayList.size();
    }

    //    ................ViewHolder start..............................

    public static class TeacherViewHolder extends RecyclerView.ViewHolder{
        TextView txt_teacher_id;
        TextView txt_teacher_name;
        TextView txt_teacher_ph;
        ImageView img_gender;
        ImageView img_call;
        Button btn_title;
        LinearLayout ll_item_layout;

        public TeacherViewHolder(View itemView){
            super(itemView);
//            this.txt_teacher_id = (TextView) itemView.findViewById(R.id.txt_teacher_id_item);
            this.txt_teacher_name = (TextView) itemView.findViewById(R.id.txt_teacher_name_item);
            this.txt_teacher_ph = (TextView) itemView.findViewById(R.id.txt_teacher_ph_item);
            this.img_gender = (ImageView) itemView.findViewById(R.id.img_teacher_gender_item);
            this.img_call = (ImageView) itemView.findViewById(R.id.img_call_item);
            this.btn_title = (Button) itemView.findViewById(R.id.btn_title);
            this.ll_item_layout = (LinearLayout) itemView.findViewById(R.id.ll_item_layout);
        }
    }

    //    ................ViewHolder end..............................
}
