package com.example.nangjanggopro;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MaterialAdapter extends RecyclerView.Adapter<MaterialAdapter.Holder> {
    private Context context;
    private List<WordItemData> list = new ArrayList<>();


    public MaterialAdapter(Context context, List<WordItemData>list) {
        this.context=context;
        this.list= list;
    }

    @NonNull
    @Override
    public MaterialAdapter.Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_material,parent,false);
        Holder holder = new Holder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull MaterialAdapter.Holder holder, int position) {
        int itemposition = position;

    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    public class Holder extends RecyclerView.ViewHolder{


        public Holder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
