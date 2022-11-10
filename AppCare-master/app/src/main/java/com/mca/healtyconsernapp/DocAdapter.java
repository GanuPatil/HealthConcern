package com.mca.healtyconsernapp;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.load.model.Model;

import java.util.ArrayList;

public class DocAdapter extends RecyclerView.Adapter<DocAdapter.DocViewHolder> {

    Context context;

    ArrayList<DoctorData> doclist;

    public DocAdapter(Context context, ArrayList<DoctorData> doclist) {
        this.context = context;
        this.doclist = doclist;
    }

    @NonNull
    @Override
    public DocViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.doc_cardview,parent,false);
        return new DocViewHolder(v);

    }

    @Override
    public void onBindViewHolder(@NonNull DocViewHolder holder, int position) {

        final DoctorData temp = doclist.get(position);

        DoctorData doctorData = doclist.get(position);
        holder.docname.setText(doctorData.docname);
        holder.docprofession.setText(doctorData.docprofession);
        holder.docexp.setText(doctorData.docexp);
        holder.docname.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context,ActivityAllDocDetail.class);
                intent.putExtra("docname",temp.getDocname());

               intent.putExtra("docexp",temp.getDocexp());
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
            }
        });


    }

    @Override
    public int getItemCount() {
        return doclist.size();
    }

    public static class DocViewHolder extends RecyclerView.ViewHolder {

        TextView docname, docprofession, docexp;

        public DocViewHolder(@NonNull View itemView){
            super(itemView);

            docname = itemView.findViewById(R.id.docname);
            docprofession = itemView.findViewById(R.id.docprofession);
            docexp = itemView.findViewById(R.id.docexp);
        }

        }

        }







