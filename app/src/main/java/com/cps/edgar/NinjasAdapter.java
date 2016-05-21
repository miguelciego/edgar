package com.cps.edgar;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by Administrator on 05/05/2016.
 */
public class NinjasAdapter extends ArrayAdapter<Ninjas2> {
    Context context;
    int layoutResourceId;
    Ninjas2 datos[] = null;


    public NinjasAdapter(Context context, int layoutResourceId, Ninjas2[] datos){

        super(context, layoutResourceId, datos);

        this.context = context;
        this.layoutResourceId = layoutResourceId;
        this.datos = datos;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        View renglon = convertView;
        NinjasHolder holder = null;

        if (renglon == null){
            LayoutInflater inflater = ((Activity)context).getLayoutInflater();
            renglon = inflater.inflate(layoutResourceId,parent,false);

            holder = new NinjasHolder();
            holder.imagen = (ImageView)renglon.findViewById(R.id.foto);
            holder.titulo = (TextView)renglon.findViewById(R.id.titulo);
            holder.subtitulo = (TextView) renglon.findViewById(R.id.subtitulo);
            renglon.setTag(holder);

        } else {
            holder = (NinjasHolder)renglon.getTag();
        }

        Ninjas2 ninjas = datos[position];
        holder.titulo.setText(ninjas.titulo);
        holder.subtitulo.setText(ninjas.subtitulo);
        holder.imagen.setImageResource(ninjas.imagen);

        return renglon;

    }

    //public List<Ninjas> getItemlist(){
     //   return itemlist;
   // }

   // public void setItemlist(List<Ninjas> itemlist){
    //    this.itemlist=itemlist;
   // }

    static class NinjasHolder{
        ImageView imagen;
        TextView titulo;
        TextView subtitulo;
    }
}
