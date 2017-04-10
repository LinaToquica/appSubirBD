package lina.ejemplopokemon;

import android.app.ProgressDialog;
import android.content.Intent;
import android.nfc.Tag;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.gson.JsonArray;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

import lina.ejemplopokemon.Firebase.FireBaseReference;
import lina.ejemplopokemon.Firebase.Pokemondb;
import lina.ejemplopokemon.PokeApi.PokeApiService;
import lina.ejemplopokemon.models.Caracteristicas;
import lina.ejemplopokemon.models.CaracteristicasRespuesta;
import lina.ejemplopokemon.models.GeneroRespuesta;
import lina.ejemplopokemon.models.Pokemon;
import lina.ejemplopokemon.models.PokemonRespuesta;
import lina.ejemplopokemon.models.Sprites;
import lina.ejemplopokemon.models.Tipos;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class MainActivity extends AppCompatActivity {
    private Retrofit retrofit;
    private static  final String TAG="POKEDEX";

    int id=0,genero=0;
    String nombre="",imagen="";
    List tipo=new ArrayList();

    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("Pokemones");


        retrofit = new Retrofit.Builder()
                .baseUrl("http://pokeapi.co/api/v2/ ")
                .addConverterFactory(GsonConverterFactory.create())
                .build();





        //obtenerdatos();

        try {
            obtenercaracteristicaspokemon();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }



    private class GetDataFromFirebase extends AsyncTask<Void,Void,Boolean>{

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected Boolean doInBackground(Void... voids) {
            return false;
        }

        @Override
        protected void onPostExecute(Boolean aBoolean) {
            super.onPostExecute(aBoolean);
        }
    }


    private void obtenerdatos(){
        PokeApiService service = retrofit.create(PokeApiService.class);
        Call<PokemonRespuesta> pokemonRespuestaCall = service.obtenerListapokemon();

        pokemonRespuestaCall.enqueue(new Callback<PokemonRespuesta>() {
            @Override
            public void onResponse(Call<PokemonRespuesta> call, Response<PokemonRespuesta> response) {
                if (response.isSuccessful()){
                    PokemonRespuesta pokemonRespuesta = response.body();

                    ArrayList<Pokemon> listapokemon = pokemonRespuesta.getResults();
                    for (int i = 0;i<listapokemon.size();i++){
                        Pokemon p = listapokemon.get(i);

                        Log.i(TAG,"pokemon: " + p.getUrl());

                    }
                }
                else{
                    Log.e(TAG,"onResponse: "+response.errorBody());
                }

            }

            @Override
            public void onFailure(Call<PokemonRespuesta> call, Throwable t) {
                Log.e(TAG,"onFailure: "+t.getMessage());

            }
        });

    }

    private  void obtenercaracteristicaspokemon() throws InterruptedException {

        final JSONObject pokemon = new JSONObject();
        final JsonArray arregloPokemon = new JsonArray();
        FirebaseDatabase db =  FirebaseDatabase.getInstance();
        final DatabaseReference tutorialRef = db.getReference("Pokemones");


        for (int i = 1;i<152;i++) {


            PokeApiService service2 = retrofit.create(PokeApiService.class);
            Call<GeneroRespuesta> generoRespuestaCall = service2.listargenero(i);

            final int finalI = i;
            generoRespuestaCall.enqueue(new Callback<GeneroRespuesta>() {
                @Override
                public void onResponse(Call<GeneroRespuesta> call, Response<GeneroRespuesta> response) {
                    if(response.isSuccessful()){

                        GeneroRespuesta respuesta = response.body();
                     //   Log.i(TAG, "gender: "+respuesta.getGender_rate());
                        genero = respuesta.getGender_rate();
                        lis(genero);
                    }
                }

                private void lis(final int genero){

                    PokeApiService service = retrofit.create(PokeApiService.class);
                    Call<CaracteristicasRespuesta> caracteristicasRespuestaCall = service.listarcaracteristicas(finalI);

                    caracteristicasRespuestaCall.enqueue(new Callback<CaracteristicasRespuesta>() {
                        @Override
                        public void onResponse(Call<CaracteristicasRespuesta> call, Response<CaracteristicasRespuesta> response) {
                            if (response.isSuccessful()) {

                                CaracteristicasRespuesta respuesta = response.body();
                                // Log.i(TAG, "id: " + String.valueOf(respuesta.getId()));
                                id = Integer.parseInt(String.valueOf(respuesta.getId()));


                                ArrayList<Caracteristicas> listatipos = respuesta.getForms();
                                for (int i = 0; i < listatipos.size(); i++) {
                                    Caracteristicas c = listatipos.get(i);

                                    nombre = c.getName();

                                    // Log.i(TAG, "nombre: " + c.getName());
                                }

                                List<Tipos> lista_tipos = respuesta.getTypes();
                                JsonArray tipos = new JsonArray();

                                for (int i = 0; i < lista_tipos.size(); i++) {
                                    Tipos c = lista_tipos.get(i);

                                    tipos.add(c.getType().getName().toString());
                                    tipo.add(c.getType().getName().toString());

                                    // Log.i("TI", String.valueOf(c.getSlot()));
                                    // Log.i("tipos: ", c.getType().getName() + "");

                                }


                                Sprites sprites = respuesta.getSprites();
                                imagen = sprites.getFront_default().toString();
                                //Log.i("imagen", sprites.getFront_default().toString());





                            }
                            arregloPokemon.add(String.valueOf(pokemon));
                            Log.i("json",id+" "+nombre+" "+tipo+" "+imagen+" "+genero);


                            // tipo.clear();

                            Pokemondb pokemonobj = new Pokemondb(id,nombre,imagen,tipo,genero);

                            tutorialRef.push().setValue(pokemonobj);
                            tipo.clear();

                        }





                        @Override
                        public void onFailure(Call<CaracteristicasRespuesta> call, Throwable t) {
                            Log.e(TAG, "onFailure: " + t.getMessage());
                        }
                    });



                }


                @Override
                public void onFailure(Call<GeneroRespuesta> call, Throwable t) {
                    Log.e(TAG, "onFailure: " + t.getMessage());
                }

            });


































        }

    }


}
