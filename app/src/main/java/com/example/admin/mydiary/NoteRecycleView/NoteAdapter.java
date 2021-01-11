package com.example.admin.mydiary.NoteRecycleView;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.admin.mydiary.DatabaseHelper;
import com.example.admin.mydiary.R;
import com.example.admin.mydiary.models.mNotes;

import java.util.ArrayList;

/**
 * Created by Admin on 16-Jul-18.
 */

public class NoteAdapter extends RecyclerView.Adapter<NoteAdapter.MyViewHolder> {

    ArrayList<mNotes> arrayList = new ArrayList<>();
    Context context;
    DatabaseHelper db;

    public NoteAdapter(ArrayList<mNotes> arrayList, Context context) {
        this.arrayList = arrayList;
        this.context = context;
        db = new DatabaseHelper(context);

    }


    @Override
    public NoteAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_note_item_row, parent, false);
        return new NoteAdapter.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final NoteAdapter.MyViewHolder holder, int position) {

        final mNotes mNote = arrayList.get(position);
        holder.note.setText(mNote.getNote());
        holder.time.setText(DateFormat.format("dd-MM-yy (HH:mm) ", mNote.getMessageTime()));

        holder.holderView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                db.removeNote(mNote.getId());
                updateArrayList(arrayList.get(holder.getAdapterPosition()), holder.getAdapterPosition(), mNote.getId());
                return true;
            }
        });
    }

    private void updateArrayList(mNotes model, int position, String id) {

        arrayList.remove(model);
        try {
            notifyItemRemoved(position);
            //  notifyDataSetChanged();
        } catch (Exception e) {

            notifyItemRemoved(position);
            //notifyDataSetChanged();
        }

        Toast.makeText(context, id, Toast.LENGTH_SHORT).show();
    }


    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {


        TextView note, time;
        View holderView;

        public MyViewHolder(View view) {
            super(view);
            holderView = view;
            note = (TextView) view.findViewById(R.id.note);
            time = (TextView) view.findViewById(R.id.message_time);

        }
    }


}
