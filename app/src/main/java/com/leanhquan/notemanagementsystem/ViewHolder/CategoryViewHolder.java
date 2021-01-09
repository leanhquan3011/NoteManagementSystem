package com.leanhquan.notemanagementsystem.ViewHolder;

import android.view.ContextMenu;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.leanhquan.notemanagementsystem.Common.Common;
import com.leanhquan.notemanagementsystem.Inteface.ItemClickListener;
import com.leanhquan.notemanagementsystem.R;

public class CategoryViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnCreateContextMenuListener {

    public TextView             txtNameCategory, txtDateCreateCategory;
    public ItemClickListener    itemClickListener;

    public CategoryViewHolder(@NonNull View itemView) {
        super(itemView);
        txtNameCategory = itemView.findViewById(R.id.txtNameCategory);
        txtDateCreateCategory = itemView.findViewById(R.id.txtDateCreateCategory);
        itemView.setOnCreateContextMenuListener(this);
    }

    public void setItemClickListener(ItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    @Override
    public void onClick(View v) {
        itemClickListener.onClick(v, getAdapterPosition(), false);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        menu.setHeaderTitle("Select action");
        menu.add(0,0,getAdapterPosition(), Common.UPDATE);
        menu.add(0,1,getAdapterPosition(), Common.DELETE);
    }
}
