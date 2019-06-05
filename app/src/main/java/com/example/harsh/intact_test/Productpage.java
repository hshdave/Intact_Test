package com.example.harsh.intact_test;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;


import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class Productpage extends AppCompatActivity {

    int id = 0;

    ImageView itemImage;
    TextView text_price,text_desc,text_h,text_w,text_d,txt_title;
    String img,height,width,depth,desc;
    double price;
    ImageView imgv1,imgv2,imgv3;
    ArrayList<String> colors;
    Button btn_addwish;
    ArrayList<Products> wishl;
    String tag = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_productpage);

        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.titile_lay);
        getSupportActionBar().setHomeButtonEnabled(true);

        wishl = new ArrayList<>();
        Intent i = getIntent();

        Products products = i.getParcelableExtra("id");
        Bundle extrs = i.getExtras();
        if (extrs != null)
        {
            if (extrs.containsKey("wis")) {
                tag = i.getStringExtra("wis");
            }
        }

        id = products.getPid();
        colors = products.getColors();
        img = products.getPimg();
        price = products.getPprice();
        height = products.getPheight();
        width = products.getPwidth();
        depth = products.getPdepth();
        desc = products.getPdesc();

        btn_addwish = findViewById(R.id.btn_addwish);

        itemImage = findViewById(R.id.pimage);
        text_price = findViewById(R.id.price);
        text_desc = findViewById(R.id.pdesc);
        text_h = findViewById(R.id.ph);
        text_w = findViewById(R.id.pw);
        text_d = findViewById(R.id.pd);
        imgv1 = findViewById(R.id.pcol1);
        imgv2 = findViewById(R.id.pcol2);
        imgv3 = findViewById(R.id.pcol3);
        txt_title = findViewById(R.id.tvTitle);

        Picasso.get().load(img).into(itemImage);
        text_price.setText("$ "+price);
        text_desc.setText(desc);
        text_h.setText(height);
        text_w.setText(width);
        text_d.setText(depth);


       txt_title.setText(products.getPtitle());


        System.out.println("Color Size "+colors.size());

        if (colors.size()>0)
        {

            if (colors.size()<2)
            {
                imgv1.setBackgroundColor(Color.parseColor(colors.get(2)));
                imgv2.setVisibility(View.GONE);
                imgv3.setVisibility(View.GONE);

            }else if(colors.size()<3)
            {
                imgv1.setBackgroundColor(Color.parseColor(colors.get(0)));
                imgv2.setBackgroundColor(Color.parseColor(colors.get(1)));
                imgv3.setVisibility(View.GONE);
            }else {
                imgv1.setBackgroundColor(Color.parseColor(colors.get(0)));
                imgv2.setBackgroundColor(Color.parseColor(colors.get(1)));
                imgv3.setBackgroundColor(Color.parseColor(colors.get(2)));
            }

        }


        if (tag.contains("fromwish"))
        {
            btn_addwish.setBackground(getDrawable(R.drawable.mybuttonblack));

        }

        btn_addwish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                Intent i = new Intent(Productpage.this,MainActivity.class);

                if (tag.contains("fromwish"))
                {
                    i.putExtra("rmc","removefromlist");
                }
                i.putExtra("wishid",id);
                startActivity(i);
            }
        });

    }

}
