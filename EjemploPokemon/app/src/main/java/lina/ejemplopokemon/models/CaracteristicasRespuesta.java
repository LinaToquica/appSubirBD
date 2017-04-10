package lina.ejemplopokemon.models;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by LINA on 05/04/2017.
 */

public class CaracteristicasRespuesta {
    private int id;
    private ArrayList<Caracteristicas> forms;
    private List<Tipos> types;
    private Sprites sprites;






    public ArrayList<Caracteristicas> getForms() {
        return forms;
    }

    public void setForms(ArrayList<Caracteristicas> forms) {
        this.forms = forms;
    }



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<Tipos> getTypes() {
        return types;
    }

    public void setTypes(List<Tipos> types) {
        this.types = types;
    }

    public Sprites getSprites() {
        return sprites;
    }

    public void setSprites(Sprites sprites) {
        this.sprites = sprites;
    }
}
