package com.example.exam01.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.example.exam01.MainActivity;
import com.example.exam01.Model.TienChi;
import com.example.exam01.R;

import java.util.ArrayList;

public class adapterChi extends RecyclerView.Adapter<adapterChi.ViewHolder> {

    ArrayList<TienChi> myList;

    public adapterChi(ArrayList<TienChi> myList) {
        this.myList = myList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.itemview, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        TienChi t = myList.get(position);
        holder.txtLKC.setText(t.getLoaiKhoanChi());
        holder.txtNC.setText(t.getNgayChi());
        holder.txtSTC.setText(t.getSoTienChi()+"");
        holder.txtTKC.setText(t.getTenKhoanChi());
        holder.LL01.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.instance.txtNgay.setText(t.getNgayChi());

            }
        });
        holder.LL01.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                myList.remove(position);
                notifyDataSetChanged();
                return true;
            }
        });
    }

    @Override
    public int getItemCount() {
        return myList.size();
    }

    public class ViewHolder extends  RecyclerView.ViewHolder {
        TextView txtLKC, txtNC, txtTKC, txtSTC;
        LinearLayout LL01;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtLKC = itemView.findViewById(R.id.txtLKC);
            txtNC = itemView.findViewById(R.id.txtNC);
            txtTKC = itemView.findViewById(R.id.txtTKC);
            txtSTC = itemView.findViewById(R.id.txtSTC);
            LL01 = itemView.findViewById(R.id.LL01);
        }
    }
}
