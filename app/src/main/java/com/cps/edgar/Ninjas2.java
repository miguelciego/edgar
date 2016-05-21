package com.cps.edgar;

import java.io.Serializable;

/**
 * Created by Administrator on 05/05/2016.
 */
public class Ninjas2 implements Serializable {

    public int imagen;
    public String titulo;
    public String subtitulo;

    public Ninjas2(){
        super();
    }

    public Ninjas2(int imagen, String titulo, String subtitulo){
        super();
        this.imagen = imagen;
        this.titulo = titulo;
        this.subtitulo = subtitulo;
    }

    public int getImagen() {
        return imagen;
    }

    public void setImagen(int imagen) {
        this.imagen = imagen;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String subtitulo) {
        this.subtitulo = subtitulo;
    }

    public String getSubtitulo() {
        return subtitulo;
    }

    public void setSubtitulo(String subtitulo) {
        this.subtitulo = subtitulo;
    }
}
