package lina.ejemplopokemon.PokeApi;


import android.util.Log;

import lina.ejemplopokemon.models.CaracteristicasRespuesta;
import lina.ejemplopokemon.models.GeneroRespuesta;
import retrofit2.Call;
import lina.ejemplopokemon.models.PokemonRespuesta;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by LINA on 05/04/2017.
 */

public interface PokeApiService {

   /* @GET("pokemon/?limit=150&offset=0")
    Call<PokemonRespuesta> obtenerListapokemon();*/

  /*  @GET("pokemon")
    Call<PokemonRespuesta> obtenerListapokemon(@Query("limit") int limit,@Query("offset") int offset);
    */

    @GET("pokemon")
    Call<PokemonRespuesta> obtenerListapokemon();

    @GET("pokemon/{id}")
    Call<CaracteristicasRespuesta> listarcaracteristicas(@Path("id") int id);


    @GET("pokemon-species/{id}")
    Call<GeneroRespuesta> listargenero(@Path("id") int id);






}
