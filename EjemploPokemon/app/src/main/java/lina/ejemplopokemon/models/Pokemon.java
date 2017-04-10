package lina.ejemplopokemon.models;

/**
 * Created by LINA on 05/04/2017.
 */

public class Pokemon {
    private String name;
    private  String url;

    public Pokemon(int id, String nombre, String imagen) {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
