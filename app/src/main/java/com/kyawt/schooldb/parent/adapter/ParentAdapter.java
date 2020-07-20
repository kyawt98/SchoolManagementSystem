package com.kyawt.schooldb.parent.adapter;

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
import android.widget.RadioButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.kyawt.schooldb.R;
import com.kyawt.schooldb.model.ParentModel;
import com.kyawt.schooldb.model.RegisterModel;
import com.kyawt.schooldb.model.TeacherModel;
import com.kyawt.schooldb.teacher.TeacherDetailActivity;

import java.util.ArrayList;
import java.util.Random;

public class ParentAdapter extends RecyclerView.Adapter<ParentAdapter.ParentViewHolder> {

    private ArrayList<ParentModel> parentModelArrayList;
    Context context;

    public ParentAdapter(ArrayList<ParentModel> parentModelArrayList, Context context) {
        this.context = context;
        this.parentModelArrayList = parentModelArrayList;
    }

    @NonNull
    @Override
    public ParentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_parent, parent, false);
        ParentViewHolder parentViewHolder = new ParentViewHolder(view);
        return parentViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ParentViewHolder holder, final int position) {

        TextView txt_parent_name = holder.txt_parent_name;
        TextView txt_parent_ph = holder.txt_parent_ph;
        ImageView img_gender = holder.img_gender;
        ImageView img_call = holder.img_call;
        Button btn_title = holder.btn_title;
        LinearLayout ll_item_layout = holder.ll_item_layout;

        txt_parent_name.setText(parentModelArrayList.get(position).parent_name + " ");
        txt_parent_ph.setText(parentModelArrayList.get(position).parent_ph + " ");

        if (parentModelArrayList.get(position).parent_gender.equalsIgnoreCase("male")) {
            img_gender.setImageResource(R.drawable.ic_male);
        } else if (parentModelArrayList.get(position).parent_gender.equalsIgnoreCase("female")) {
            img_gender.setImageResource(R.drawable.ic_female);
        }

//        ................. Logic Title Start.......................

        btn_title.setText(parentModelArrayList.get(position).parent_gender.toUpperCase().charAt(0) + "");
//        ................. Random color......................
        Random random = new Random();
        int red = random.nextInt(255);
        final int green = random.nextInt(255);
        int blue = random.nextInt(255);

        btn_title.setBackgroundColor(Color.rgb(red, green, blue));

        if (parentModelArrayList.get(position).parent_ph.length() < 12) {

            img_call.setVisibility(View.VISIBLE);
            img_call.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + parentModelArrayList.get(position).parent_ph));
                    context.startActivity(intent);
                }
            });
        } else {
            img_call.setVisibility(View.GONE);
        }

        ll_item_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int parent_id = parentModelArrayList.get(position).parent_id;
                String parent_name = parentModelArrayList.get(position).parent_name;
                String parent_ph = parentModelArrayList.get(position).parent_ph;
                String parent_gender = parentModelArrayList.get(position).parent_gender;
                String parent_nrc = parentModelArrayList.get(position).parent_nrc;
                String parent_dob = parentModelArrayList.get(position).parent_birthday;
                String parent_address = parentModelArrayList.get(position).parent_address;
                String parent_email = parentModelArrayList.get(position).parent_email;

                Intent intent = new Intent(context, TeacherDetailActivity.class);

                intent.putExtra("key_for_parent_id", parent_id);
                intent.putExtra("key_for_parent_name", parent_name);
                intent.putExtra("key_for_parent_ph", parent_ph);
                intent.putExtra("key_for_parent_gender", parent_gender);
                intent.putExtra("key_for_parent_nrc", parent_nrc);
                intent.putExtra("key_for_parent_dob", parent_dob);
                intent.putExtra("key_for_parent_email", parent_email);
                intent.putExtra("key_for_parent_address",parent_address);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return parentModelArrayList.size();
    }

    //    ................ViewHolder start..............................

    public static class ParentViewHolder extends RecyclerView.ViewHolder{
        TextView txt_parent_name,txt_parent_ph;
        ImageView img_gender;
        ImageView img_call;
        Button btn_title;
        LinearLayout ll_item_layout;

        public ParentViewHolder(View itemView){
            super(itemView);
            this.txt_parent_name = (TextView) itemView.findViewById(R.id.txt_parent_name_item);
            this.txt_parent_ph = (TextView) itemView.findViewById(R.id.txt_parent_ph_item);
            this.img_gender = (ImageView) itemView.findViewById(R.id.img_parent_gender_item);
            this.img_call = (ImageView) itemView.findViewById(R.id.img_call_item);
            this.btn_title = (Button) itemView.findViewById(R.id.btn_title);
            this.ll_item_layout = (LinearLayout) itemView.findViewById(R.id.ll_item_layout);
        }
    }

    //    ................ViewHolder end..............................
}
