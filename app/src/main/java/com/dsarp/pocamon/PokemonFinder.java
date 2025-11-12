package com.dsarp.pocamon;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class PokemonFinder extends AppCompatActivity {
    private EditText PokemonName;
    private Button btnSearch;
    private ImageView PokemonImage;
    private TextView PokemonDetails;

    private static final String BASE_URL = "https://pokeapi.co/api/v2/";
    private PokemonserviceAPI apiService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_pokemon_finder);

        PokemonName = findViewById(R.id.pokemonInput);
        btnSearch = findViewById(R.id.searchButton);
        PokemonImage = findViewById(R.id.pokeimage);
        PokemonDetails = findViewById(R.id.pokemonditel);

        // Initialize Retrofit once
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        apiService = retrofit.create(PokemonserviceAPI.class);

        btnSearch.setOnClickListener(v -> fetchPokemonData());
    }

    private void fetchPokemonData() {
        String pokemonName = PokemonName.getText().toString().trim().toLowerCase();
        if (pokemonName.isEmpty()) {
            Toast.makeText(this, "Enter a Pokémon name!", Toast.LENGTH_SHORT).show();
            return;
        }

        Call<PokemonResponse> call = apiService.getPokemonInfo(pokemonName);
        call.enqueue(new Callback<PokemonResponse>() {
            @Override
            public void onResponse(Call<PokemonResponse> call, Response<PokemonResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    PokemonResponse pokemon = response.body();
                    Log.d("PokemonResponse", "Received data: " + pokemon.getName());

                    // Fetch the correct Pokémon image
                    String imageUrl = null;
                    if (pokemon.getSprites() != null) {
                        if (pokemon.getSprites().getOther() != null &&
                                pokemon.getSprites().getOther().getOfficialArtwork() != null) {
                            imageUrl = pokemon.getSprites().getOther().getOfficialArtwork().getFrontDefault();
                        }
                        if (imageUrl == null || imageUrl.isEmpty()) {
                            imageUrl = pokemon.getSprites().getFrontDefault();
                        }
                    }

                    String details = "Name: " + pokemon.getName().toUpperCase();
                    if (pokemon.getTypes() != null && !pokemon.getTypes().isEmpty()) {
                        details += "\nType: " + pokemon.getTypes().get(0).getType().getName().toUpperCase();
                    }
                    PokemonDetails.setText(details);

                    Glide.with(PokemonFinder.this)
                            .load(imageUrl)
                            .placeholder(R.drawable.jigglypuff) // Show while loading
                            .error(R.drawable.alert) // Show if loading fails
                            .into(PokemonImage);
                } else {
                    Toast.makeText(PokemonFinder.this, "Pokémon not found!", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<PokemonResponse> call, Throwable t) {
                Toast.makeText(PokemonFinder.this, "API Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
