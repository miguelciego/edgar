package com.cps.edgar;

import java.io.Serializable;

/**
 * Created by Administrator on 05/05/2016.
 */
public class Ninjas implements Serializable{

    public int imagen;
    public String nombre;

    public Ninjas(){
        super();
    }

    public Ninjas(int imagen, String nombre){
        super();
        this.imagen = imagen;
        this.nombre = nombre;
    }

    public int getImagen() {
        return imagen;
    }

    public void setImagen(int imagen) {
        this.imagen = imagen;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
