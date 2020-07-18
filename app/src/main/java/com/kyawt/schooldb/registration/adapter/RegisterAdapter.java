package com.kyawt.schooldb.registration.adapter;

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
import com.kyawt.schooldb.model.RegisterModel;
import com.kyawt.schooldb.registration.RegisterDetailActivity;

import java.util.ArrayList;
import java.util.Random;

public class RegisterAdapter extends RecyclerView.Adapter<RegisterAdapter.RegisterViewHolder> {


    private ArrayList<RegisterModel> registerModelArrayList;
    Context context;

    public RegisterAdapter(ArrayList<RegisterModel> registerModelArrayList, Context context){
        this.context = context;
        this.registerModelArrayList = registerModelArrayList;
    }

    @NonNull
    @Override
    public RegisterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_register,parent,false);
        RegisterViewHolder registerViewHolder = new RegisterViewHolder(view);
        return registerViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RegisterViewHolder holder, final int position) {

        Button btn_title = holder.btn_title;
        TextView txt_student_name = holder.txt_student_name;
        TextView txt_student_ph = holder.txt_student_ph;
        TextView txt_course_name = holder.txt_course_name;
        ImageView img_call = holder.img_call;
        LinearLayout ll_item_layout = holder.ll_item_layout;

        txt_student_name.setText(registerModelArrayList.get(position).student_name+" ");
        txt_course_name.setText(registerModelArrayList.get(position).course_name+" ");
        txt_student_ph.setText(registerModelArrayList.get(position).father_ph);

        //        ................. Random color......................
        btn_title.setText(registerModelArrayList.get(position).student_name.toUpperCase().charAt(0)+"");

        Random random = new Random();
        int red = random.nextInt(255);
        final int green =  random.nextInt(255);
        int blue = random.nextInt(255);

        btn_title.setBackgroundColor(Color.rgb(red,green,blue));

        if (registerModelArrayList.get(position).father_ph.length() <12) {

            img_call.setVisibility(View.VISIBLE);
            img_call.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + registerModelArrayList.get(position).father_ph));
                    context.startActivity(intent);
                }
            });
        }else{
            img_call.setVisibility(View.GONE);
        }

        ll_item_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String student_name = registerModelArrayList.get(position).student_name;
                String course_name = registerModelArrayList.get(position).course_name;
                String register_date = registerModelArrayList.get(position).register_date;
                String student_nrc = registerModelArrayList.get(position).student_nrc;
                String student_dob = registerModelArrayList.get(position).student_bd;
                String father_name = registerModelArrayList.get(position).father_name;
                String father_nrc = registerModelArrayList.get(position).father_nrc;
                String father_ph = registerModelArrayList.get(position).father_ph;
                String address = registerModelArrayList.get(position).student_address;
                String email = registerModelArrayList.get(position).student_email;
                int course_fees = registerModelArrayList.get(position).course_fees;
                int course_duration = registerModelArrayList.get(position).course_duration;

                Intent intent= new Intent(context, RegisterDetailActivity.class);
                intent.putExtra("key_for_student_name", student_name);
                intent.putExtra("key_for_course_name", course_name);
                intent.putExtra("key_for_register_date", register_date);
                intent.putExtra("key_for_student_nrc", student_nrc);
                intent.putExtra("key_for_student_dob", student_dob);
                intent.putExtra("key_for_father_name", father_name);
                intent.putExtra("key_for_father_nrc", father_nrc);
                intent.putExtra("key_for_father_ph", father_ph);
                intent.putExtra("key_for_address", address);
                intent.putExtra("key_for_email", email);
                intent.putExtra("key_for_course_fees", course_fees);
                intent.putExtra("key_for_course_duration", course_duration);
                context.startActivity(intent);

            }
        });
    }

    @Override
    public int getItemCount() {
        return registerModelArrayList.size();
    }

    public static class RegisterViewHolder extends RecyclerView.ViewHolder {
        Button btn_title;
        TextView txt_student_name, txt_student_ph, txt_course_name;
        ImageView img_call;
        LinearLayout ll_item_layout;

       public RegisterViewHolder(View itemView){
           super(itemView);
           this.txt_student_name = (TextView) itemView.findViewById(R.id.txt_student_name_item);
           this.txt_course_name = (TextView) itemView.findViewById(R.id.txt_course_name);
           this.ll_item_layout = (LinearLayout) itemView.findViewById(R.id.ll_item_layout);
           this.txt_student_ph = (TextView) itemView.findViewById(R.id.txt_student_ph_item);
           this.img_call = (ImageView) itemView.findViewById(R.id.img_call_item);
           this.btn_title = (Button) itemView.findViewById(R.id.btn_title);
       }
    }
}
