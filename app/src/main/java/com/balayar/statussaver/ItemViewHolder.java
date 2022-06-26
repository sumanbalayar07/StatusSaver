package com.balayar.statussaver;

import android.view.View;
import android.widget.ImageButton;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ItemViewHolder extends RecyclerView.ViewHolder{
    public ImageButton save, share;
    public View imageView;
    public ItemViewHolder(@NonNull View itemView) {
        super(itemView);
        imageView = itemView.findViewById(R.id.ivThumbnail);
        save = itemView.findViewById(R.id.delete);
        share = itemView.findViewById(R.id.share);
    }
}