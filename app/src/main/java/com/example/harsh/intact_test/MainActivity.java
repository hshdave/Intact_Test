package com.example.harsh.intact_test;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Parcelable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.ContextThemeWrapper;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ArrayList<Products> listpro;
    private ArrayList<Products> wishlist;
    private ArrayList<Products> mlist;
    private ArrayList<String> colors;

    Button btn_check;
    Mywishlistadapter adapter;


    int id = 0;
    String tag = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        System.out.println("New mlist :"+mlist);

        btn_check = findViewById(R.id.btn_check);


        Intent i = getIntent();
        Bundle extrs = i.getExtras();
        if (extrs != null)
        {
            if (extrs.containsKey("rmc")) {
                tag = i.getStringExtra("rmc");
            }
        }

        id = i.getIntExtra("wishid",0);

        wishlist = new ArrayList<>();
        adapter = new Mywishlistadapter(wishlist,this);

        listpro = new ArrayList<>();
        addWish(listpro);

        new GetJSON().execute();

        btn_check.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setMessage(R.string.msg);

                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        finish();
                        startActivity(getIntent());
                    }
                });
                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });

                AlertDialog ckalert = builder.create();
                ckalert.show();

            }
        });
    }


    public void initView()
    {
        LinearLayoutManager layoutManager = new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false);
        RecyclerView recyclerView = findViewById(R.id.myCatlog);
        recyclerView.setLayoutManager(layoutManager);
        CatlogAdapter adapter = new CatlogAdapter(listpro,this);
        recyclerView.setAdapter(adapter);
        adapter.setOnItemClickListener(onItemClickListener);
    }


    public void intiWishView()
    {
        LinearLayoutManager layoutManager = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
        RecyclerView recyclerView = findViewById(R.id.mywishlist);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
        adapter.setOnItemClickListener(onItemClickListenerwish);
    }

    public class GetJSON extends AsyncTask<Void,Void,Void>
    {

        String link = getResources().getString(R.string.link);
        String color = "#ffffff";
        @Override
        protected Void doInBackground(Void... voids) {

            Log.d("Json link",link);

            Httphandler sh = new Httphandler();

            String jsondata = sh.makeServiceCall(link);

            System.out.println("JSON String :"+jsondata);

            colors = new ArrayList<>();


            if(jsondata != null)
            {
                try
                {
                    JSONObject jproroot = new JSONObject(jsondata);

                    JSONArray products = jproroot.getJSONArray("products");

                    for (int i=0;i<products.length();i++)
                    {
                        JSONObject childproduct = products.getJSONObject(i);


                            if (childproduct.has("colors"))
                            {
                                JSONArray colorarray = childproduct.getJSONArray("colors");
                                System.out.println("Color Array: "+colorarray);


                                    for(int j=0;j<colorarray.length();j++)
                                    {
                                        JSONObject code = colorarray.getJSONObject(j);
                                        colors.add(code.getString("code"));
                                    }


                            }

                         JSONObject size = childproduct.getJSONObject("size");

                        listpro.add(new Products(childproduct.getInt("id"),childproduct.getInt("quantity"),
                                childproduct.getString("title"),childproduct.getString("brand"),
                                                  childproduct.getString("description"),
                                                    childproduct.getString("image"),
                                                    size.getString("H"),
                                                    size.getString("W"),
                                                    size.getString("D"),
                                                    childproduct.getString("short_description"),
                                                    childproduct.getDouble("price"),
                                                    colors));
                    }


                    System.out.println("JSON Root :"+products);

                }catch (JSONException e)
                {
                    e.printStackTrace();
                }
            }



            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);

            initView();
            intiWishView();

            addWish(listpro);
        }
    }

    private View.OnClickListener onItemClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {

            RecyclerView.ViewHolder viewHolder = (RecyclerView.ViewHolder) view.getTag();
            int position = viewHolder.getAdapterPosition();

            Intent i = new Intent(MainActivity.this,Productpage.class);

            i.putExtra("id",listpro.get(position));
            startActivity(i);



        }
    };

    private View.OnClickListener onItemClickListenerwish = new View.OnClickListener() {
        @Override
        public void onClick(View view) {

            RecyclerView.ViewHolder viewHolder = (RecyclerView.ViewHolder) view.getTag();
            int position = viewHolder.getAdapterPosition();
            Intent i = new Intent(MainActivity.this,Productpage.class);

            i.putExtra("id",wishlist.get(position));
            i.putExtra("wis","fromwish");
            startActivity(i);



        }
    };


    public void addWish(ArrayList<Products> wish)
    {

        try
        {

            if (tag.contains("removefromlist"))
            {
                if (wish.size()>0)
                {
                    for (int i=0;i<wish.size();i++)
                    {
                        if (wish.get(i).getPid()==id)
                        {
                            wishlist.remove(i);

                            adapter.notifyDataSetChanged();
                        }
                    }
                }
            }else
            {
                if (wish.size()>0)
                {
                    for (int i=0;i<wish.size();i++)
                    {
                        if (wish.get(i).getPid()==id)
                        {
                            System.out.println("My ID Found!");

                            wishlist.add(new Products(wish.get(i).getPid(),wish.get(i).getPqty(),
                                    wish.get(i).getPtitle(),wish.get(i).getPbrand(),
                                    wish.get(i).getPdesc(),wish.get(i).getPimg(),wish.get(i).getPheight(),
                                    wish.get(i).getPwidth(),wish.get(i).getPdepth(),wish.get(i).getSdesc(),
                                    wish.get(i).getPprice(),wish.get(i).getColors()));


                            adapter.notifyDataSetChanged();
                        }
                    }
                }

            }



        }catch (NullPointerException n)
        {
            n.printStackTrace();
        }catch (IndexOutOfBoundsException i)
        {
            i.printStackTrace();
        }

    }

    @Override
    protected void onPause() {
        super.onPause();



    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        // Make sure to call the super method so that the states of our views are saved


        super.onSaveInstanceState(outState);
        // Save our own state now
    }



}
