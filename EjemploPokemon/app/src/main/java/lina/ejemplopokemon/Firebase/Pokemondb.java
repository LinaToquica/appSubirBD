package lina.ejemplopokemon.Firebase;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by LINA on 06/04/2017.
 */

public class Pokemondb {
    private int id;
    private String nombre;
    private  String imagen;
    private List tipo;
    private int gender_rate;


    public Pokemondb() {
    }

    public Pokemondb(int id, String nombre, String imagen,List tipo,int gender_rate) {
        this.id = id;
        this.nombre = nombre;
        this.imagen = imagen;
        this.tipo = tipo;
        this.gender_rate = gender_rate;

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public List getTipo() {
        return tipo;
    }

    public void setTipo(List tipo) {
        this.tipo = tipo;
    }


    public int getGender_rate() {
        return gender_rate;
    }

    public void setGender_rate(int gender_rate) {
        this.gender_rate = gender_rate;
    }
}
