package com.example.projectnotes;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class
Adapter extends RecyclerView.Adapter<Adapter.Programviewholder> implements Filterable {
    ArrayList<Modelclass> data;
    ArrayList<Modelclass> backup;
    Context context;
    Activity activity;

    public Adapter(Context applicationContext, ArrayList<Modelclass> data) {
        this.data = data;
        this.context = context;
        backup = new ArrayList<>(data);
    }

    @Override
    public Programviewholder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.singlerow, parent, false);
        return new Programviewholder(view);
    }


    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onBindViewHolder(Adapter.Programviewholder holder, int position) {
      int colorcode=getRandomColor();
holder.tv.setBackgroundColor(holder.itemView.getResources().getColor(colorcode,null));
        holder.td.setBackgroundColor(holder.itemView.getResources().getColor(colorcode,null));
        String  title    = data.get(position).getTitle();
        String  description    = data.get(position).getTitle();

        holder.tv.setText(data.get(position).getTitle());
        holder.td.setText(data.get(position).getDesc());


    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public android.widget.Filter getFilter() {
        return filter;
    }

    android.widget.Filter filter = new Filter() {


        @Override
        // background thread
        protected FilterResults performFiltering(CharSequence keyword) {
            ArrayList<Modelclass> filtereddata = new ArrayList<>();

            if (keyword.toString().isEmpty())
                filtereddata.addAll(backup);
            else {
                for (Modelclass obj : backup) {
                    if (obj.getTitle().toString().toLowerCase().contains(keyword.toString().toLowerCase()))
                        filtereddata.add(obj);
                }
            }

            FilterResults results = new FilterResults();
            results.values = filtereddata;
            return results;
        }

        @Override  // main UI thread
        protected void publishResults(CharSequence constraint, FilterResults results) {
            data.clear();
            data.addAll((ArrayList<Modelclass>) results.values);
            notifyDataSetChanged();
        }
    };


    protected class Programviewholder extends RecyclerView.ViewHolder {
        TextView tv;
        TextView td;
        //ImageView img;
        TextView id;
        RelativeLayout layout;

        public Programviewholder(@NonNull View itemView) {
            super(itemView);
        //    img = (ImageView) itemView.findViewById(R.id.addnotes);
            tv = (TextView) itemView.findViewById(R.id.textView2);
            td = (TextView) itemView.findViewById(R.id.textView);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i = new Intent(v.getContext(),Updateactivity.class);
                    i.putExtra("ID",data.get(getAdapterPosition()).getId());
                    v.getContext().startActivity(i);


                }
            });

        }
    }
    private int getRandomColor(){
        List<Integer> colorcode=new ArrayList<>();
        colorcode.add(R.color.blue);
        colorcode.add(R.color.orange);
        colorcode.add(R.color.purple);
        colorcode.add(R.color.skyblue);
        colorcode.add(R.color.violet);
        colorcode.add(R.color.yellow);
        colorcode.add(R.color.darkyellow);
        colorcode.add(R.color.green);
        colorcode.add(R.color.lightgreen);
        colorcode.add(R.color.pink);

        Random random=new Random();
        int number=random.nextInt(colorcode.size());
        return colorcode.get(number);
    }

}
