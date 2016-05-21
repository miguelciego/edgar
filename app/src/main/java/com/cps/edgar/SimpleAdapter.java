package com.cps.edgar;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Iterator;
import java.util.List;

/**
 * Created by Administrator on 18/05/2016.
 */
public class SimpleAdapter extends ArrayAdapter<Ninjas> {

    private List<Ninjas> itemlist;
    private Context context;

    public SimpleAdapter(Context context, List<Ninjas> itemlist) {
        super(context, android.R.layout.simple_expandable_list_item_1, itemlist);
        this.itemlist = itemlist;
        this.context=context;
    }

    @Override
    public int getCount() {
        if (itemlist != null)
        return itemlist.size();
        return 0;
    }

    @Override
    public Ninjas getItem(int position) {
        if (itemlist != null)
            return itemlist.get(position);
        return null;
    }

    @Override
    public long getItemId(int position) {
        if (itemlist != null)
            return itemlist.get(position).hashCode();
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View v = convertView;
        if (v == null) {
            v = inflater.inflate(R.layout.renglon,parent,false);
        }

        ImageView log = (ImageView) v.findViewById(R.id.foto);
        TextView nomb = (TextView) v.findViewById((R.id.nombre));

        Ninjas c = getItemList().get(position);
        //int  pos = getCount();
        nomb.setText(c.getNombre());
        log.setImageResource(c.getImagen());
        return v;
    }

    public List<Ninjas> getItemList() {
        return itemlist;
    }

    public void setItemList(List<Ninjas> itemlist){
        this.itemlist = itemlist;
    }
}
