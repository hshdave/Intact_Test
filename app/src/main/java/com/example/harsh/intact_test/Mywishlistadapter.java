package com.example.harsh.intact_test;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class Mywishlistadapter extends RecyclerView.Adapter<Mywishlistadapter.ViewHolder>{


    private ArrayList<Products> arraypro;
    private Context mcontext;
    public static String currency = "$";
    private View.OnClickListener mylister;


    public Mywishlistadapter(ArrayList<Products> arraypro, Context mcontext) {
        this.arraypro = arraypro;
        this.mcontext = mcontext;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.mywishlistview,viewGroup,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {

        viewHolder.outof.setVisibility(View.GONE);

        Glide.with(mcontext).asBitmap().load(arraypro.get(i).getPimg()).into(viewHolder.wishImage);

        viewHolder.wishTxtprice.setText(currency+" "+String.valueOf(arraypro.get(i).pprice));
        viewHolder.wishTxtname.setText(arraypro.get(i).getPtitle());
        viewHolder.wishTxtdesc.setText(arraypro.get(i).getSdesc());


        if(arraypro.get(i).getPqty()<=0)
        {
            viewHolder.wishimgcol1.setVisibility(View.GONE);
            viewHolder.wishimgcol2.setVisibility(View.GONE);
            viewHolder.wishimgcol3.setVisibility(View.GONE);

            viewHolder.outof.setVisibility(View.VISIBLE);

            viewHolder.outof.setText("Out of Stock");
        }
        else {
            for (int j=0;j<arraypro.get(i).colors.size();j++)
            {
                viewHolder.wishimgcol1.setBackgroundColor(Color.parseColor(arraypro.get(i).colors.get(j)));
                viewHolder.wishimgcol2.setBackgroundColor(Color.parseColor(arraypro.get(i).colors.get(j)));
            }
        }


    }

    public void setOnItemClickListener(View.OnClickListener itemClickListener) {
        mylister = itemClickListener;
    }

    @Override
    public int getItemCount() {
        return arraypro.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        ImageView wishImage,wishimgcol1,wishimgcol2,wishimgcol3;
        TextView wishTxtprice,wishTxtname,wishTxtdesc,outof;

        public ViewHolder(View itemView) {
            super(itemView);

            wishImage = itemView.findViewById(R.id.wishlistImg);
            wishimgcol1 = itemView.findViewById(R.id.productcol1);
            wishimgcol2 = itemView.findViewById(R.id.productcol2);
            wishimgcol3 = itemView.findViewById(R.id.productcol3);

            wishTxtprice = itemView.findViewById(R.id.prodctPrice);
            wishTxtname = itemView.findViewById(R.id.productName);
            wishTxtdesc = itemView.findViewById(R.id.productDesc);

            outof = itemView.findViewById(R.id.outof);

            itemView.setTag(this);
            itemView.setOnClickListener(mylister);
        }
    }
}
