package com.example.lab4;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class CustomAdapter extends BaseAdapter {
    private Context context;
    private int layout;
    private List<obj> objList;

    public CustomAdapter(Context context, int layout, List<obj> objList) {
        this.context = context;
        this.layout = layout;
        this.objList = objList;
    }

    @Override
    public int getCount() {
        return objList.size();
    }

    @Override
    public Object getItem(int position) {
        return objList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }



    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;

        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(layout, null);

            holder = new ViewHolder();
            holder.name = convertView.findViewById(R.id.nameFruit);
            holder.description = convertView.findViewById(R.id.desFruit);
            holder.image = convertView.findViewById(R.id.imageFruit);

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        obj currentObj = objList.get(position);

        holder.name.setText(currentObj.getName());
        holder.description.setText(currentObj.getDescription());
//        holder.image.setImageResource(currentObj.getImage());
        Picasso.get()
                .load(currentObj.getImage())
                .centerCrop()
                .fit()
                .into(holder.image);


        return convertView;
    }

    private static class ViewHolder {
        TextView name;
        TextView description;
        ImageView image;
    }
}
