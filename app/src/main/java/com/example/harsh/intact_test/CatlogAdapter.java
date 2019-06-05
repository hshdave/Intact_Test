package com.example.harsh.intact_test;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class CatlogAdapter extends RecyclerView.Adapter<CatlogAdapter.ViewHolder> {



    private ArrayList<Products> arraypro;
    private Context mcontext;
    private View.OnClickListener catlogitemListener;

    public CatlogAdapter(ArrayList<Products> arraypro, Context mcontext) {
        this.arraypro = arraypro;
        this.mcontext = mcontext;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {

        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.recyclerviewitem,viewGroup,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int i) {
        Glide.with(mcontext).asBitmap().load(arraypro.get(i).getPimg())
                .into(viewHolder.catlogImage);

        viewHolder.catlogText.setText(arraypro.get(i).getPbrand());

    }

    public void setOnItemClickListener(View.OnClickListener itemClickListener) {
        catlogitemListener = itemClickListener;
    }


    @Override
    public int getItemCount() {
        return arraypro .size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        ImageView catlogImage;
        TextView catlogText;

        public ViewHolder(View itemView) {
            super(itemView);

            catlogImage =itemView.findViewById(R.id.recycleimageView);
            catlogText = itemView.findViewById(R.id.recyclePname);

            itemView.setTag(this);
            itemView.setOnClickListener(catlogitemListener);
        }
    }

}
