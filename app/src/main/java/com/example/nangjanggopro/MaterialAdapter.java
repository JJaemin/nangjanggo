package com.example.nangjanggopro;

import android.content.Context;
import android.util.Log;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MaterialAdapter extends RecyclerView.Adapter<MaterialAdapter.Holder> {
    private Context context;
    private List<WordItemData> list;


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
        holder.textMaterial.setText(list.get(itemposition).Material_title);
        holder.textNow.setText(list.get(itemposition).Material_now);
        holder.textAfter.setText(list.get(itemposition).Material_after);
        holder.btnalerm.setText(list.get(itemposition).Material_alerm);
        Log.d("holder",list.get(itemposition).Material_title+" "+list.get(itemposition).Material_now+" "+list.get(itemposition).Material_after );

    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    public class Holder extends RecyclerView.ViewHolder implements View.OnCreateContextMenuListener {

        TextView textMaterial, textNow, textAfter, btnalerm;

        public Holder(@NonNull final View itemView) {
            super(itemView);

            textMaterial = itemView.findViewById(R.id.textMaterial);
            textAfter = itemView.findViewById(R.id.textAfter);
            textNow = itemView.findViewById(R.id.textNow);
            btnalerm = itemView.findViewById(R.id.btnalerm);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    Toast.makeText(context, "재료를 삭제합니다.", Toast.LENGTH_SHORT).show();

                    list.remove(getAdapterPosition());
                    notifyItemRemoved(getAdapterPosition());
                    notifyItemRangeChanged(getAdapterPosition(), list.size());
                }
            });


        }


        @Override
        public void onCreateContextMenu(ContextMenu contextMenu, View view, ContextMenu.ContextMenuInfo contextMenuInfo) {

        }
    }
}
