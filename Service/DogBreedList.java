package Service;

import Model.DogBreed;

import java.util.ArrayList;
import java.util.List;
import com.google.gson.reflect.TypeToken;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import com.google.gson.Gson;
import Model.*;

import java.io.IOException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.stream.Collectors;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;


public class DogBreedList {
    private List<DogBreed> breedList;

    public DogBreedList() {
        breedList = new ArrayList<>();
    }
    public void addBreed(DogBreed breed) {
        breedList.add(breed);
    }
    public List<DogBreed> getBreedList() {
        return breedList;
    }
    public DogBreed searchBreedName(List<DogBreed> dogBreeds, String name) {
        for(DogBreed breed : dogBreeds) {
            if(breed.getBreed().equalsIgnoreCase(name)) {
                return breed;
            }

        }
        return null;
    }
    private static final String API_URL = "https//api.thedogapi.com/v1/breeds";
    private static final String API_KEY = "live_nETOrXttTpqANbIMq5wGJ7zrn12iftjbHJdgkdSnBLzroKsTq52U2Af7eNlJKzGD";

    Gson gson = new Gson();

    public static List<DogBreed> fetchDogBreeds() throws IOException, InterruptedException {

        HttpClient client = HttpClient.newHttpClient();

        // Create the request
        HttpRequest request = HttpRequest.newBuilder().uri(URI.create("https://api.thedogapi.com/v1/breeds"))
                .header("x-api-key", "live_nETOrXttTpqANbIMq5wGJ7zrn12iftjbHJdgkdSnBLzroKsTq52U2Af7eNlJKzGD")
                .build();


        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        //String responseBody = response.body();

        List<DogBreed> dogBreedList = new ArrayList<>();

        Gson gson1 = new Gson();
        JsonArray dogBreeds = gson1.fromJson(response.body(), JsonArray.class);

        for(JsonElement dog: dogBreeds) {
            JsonObject breed = dog.getAsJsonObject();

            String breedName = breed.get("name").getAsString();
            Size breedSize = parseSize(breed.get("size").getAsString());
            EnergyLevel breedEnergy = parseEnergyLevel(breed.get("energy").getAsString());


            DogBreed dogBreed = new DogBreed(breedName,breedSize, breedEnergy);
            dogBreedList.add(dogBreed);
        }
        return dogBreedList;

    }

    private static Size parseSize(String sizeString) {
        try {
            return Size.valueOf(sizeString.toUpperCase());
        } catch (IllegalArgumentException e) {
            return Size.Small;
        }
    }

    // Parse energy level from string to enum
    public static EnergyLevel parseEnergyLevel(String energyString) {
        try {
            return EnergyLevel.valueOf(energyString.toUpperCase());
        } catch (IllegalArgumentException e) {
            return EnergyLevel.Low;
        }
    }

}
