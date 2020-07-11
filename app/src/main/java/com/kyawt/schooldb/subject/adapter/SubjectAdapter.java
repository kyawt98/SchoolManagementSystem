package com.kyawt.schooldb.subject.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.kyawt.schooldb.R;
import com.kyawt.schooldb.course.CourseDetailActivity;
import com.kyawt.schooldb.model.CourseModel;
import com.kyawt.schooldb.model.SubjectModel;
import com.kyawt.schooldb.subject.SubjectDetailActivity;

import java.util.ArrayList;

public class SubjectAdapter extends RecyclerView.Adapter<SubjectAdapter.SubjectViewHolder> {

    private ArrayList<SubjectModel> subjectModelArrayList;
    Context context;

    public SubjectAdapter(ArrayList<SubjectModel> subjectModelArrayList, Context context){
        this.context = context;
        this.subjectModelArrayList = subjectModelArrayList;
    }

    @NonNull
    @Override
    public SubjectViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_subject,parent,false);
        SubjectViewHolder subjectViewHolder = new SubjectViewHolder(view);
        return subjectViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull SubjectViewHolder holder, final int position) {
        TextView txt_sub_code = holder.txt_sub_code;
        TextView txt_sub_name = holder.txt_sub_name;
        ConstraintLayout ll_item_layout = holder.ll_item_layout;

        txt_sub_code.setText(subjectModelArrayList.get(position).sub_code+" ");
        txt_sub_name.setText(subjectModelArrayList.get(position).sub_name+" ");

        ll_item_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int subCode = subjectModelArrayList.get(position).sub_code;
                String subName = subjectModelArrayList.get(position).sub_name;

                Toast.makeText(context, subCode+"\n"+ subName, Toast.LENGTH_LONG).show();

                Intent intent= new Intent(context, SubjectDetailActivity.class);
                intent.putExtra("key_for_sub_code", subCode);
                intent.putExtra("key_for_sub_name", subName);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return subjectModelArrayList.size();
    }


    //    ................ViewHolder start..............................

    public static class SubjectViewHolder extends RecyclerView.ViewHolder{
        TextView txt_sub_code;
        TextView txt_sub_name;
        ConstraintLayout ll_item_layout;

        public SubjectViewHolder(View itemView){
            super(itemView);
            this.txt_sub_code = (TextView) itemView.findViewById(R.id.txtSubjectCodeItem);
            this.txt_sub_name = (TextView) itemView.findViewById(R.id.txtSubjectNameItem);
            this.ll_item_layout = (ConstraintLayout) itemView.findViewById(R.id.layout_item);
        }
    }

    //    ................ViewHolder end..............................
}
