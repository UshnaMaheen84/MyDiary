package com.example.admin.mydiary.DiaryRecycleView;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.admin.mydiary.DatabaseHelper;
import com.example.admin.mydiary.R;
import com.example.admin.mydiary.ViewDiaryEntry;
import com.example.admin.mydiary.WriteDiary;
import com.example.admin.mydiary.models.DiaryModel;

import java.util.ArrayList;

/**
 * Created by Admin on 13-Jul-18.
 */

public class DiaryAdapter extends RecyclerView.Adapter<DiaryAdapter.MyViewHolder> {


    ArrayList<DiaryModel> arrayList = new ArrayList<>();
    Context context;
    DatabaseHelper db;

    public DiaryAdapter(ArrayList<DiaryModel> arrayList, Context context) {
        this.arrayList = arrayList;
        this.context = context;
        db = new DatabaseHelper(context);

    }


    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_diary_item_row, parent, false);
        return new MyViewHolder(itemView);    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {

        final DiaryModel diaryModel = arrayList.get(position);
        holder.date.setText(diaryModel.getDate());
        holder.subject.setText(diaryModel.getSubject());

        holder.holderView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, ViewDiaryEntry.class);
                intent.putExtra("Date",diaryModel.getDate());
                intent.putExtra("Subject",diaryModel.getSubject());
                intent.putExtra("Entry",diaryModel.getEntry());
                context.startActivity(intent);

            }
        });

    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {


        TextView date, subject, entry;
        View holderView;

        public MyViewHolder(View view) {
            super(view);
            holderView = view;
            date = (TextView) view.findViewById(R.id.date);
            subject = (TextView) view.findViewById(R.id.subject);
        }
    }


}
