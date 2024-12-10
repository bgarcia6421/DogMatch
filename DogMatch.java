import java.util.*;

import Model.DogBreed;
import Model.EnergyLevel;
import Model.Size;
import Service.DogBreedGraph;
import com.google.gson.*;
import java.io.*;
import java.net.*;

public class DogMatch {
    private static final String API_KEY = "live_nETOrXttTpqANbIMq5wGJ7zrn12iftjbHJdgkdSnBLzroKsTq52U2Af7eNlJKzGD";  // Replace with your actual Dog API key
    private static final String BASE_URL = "https://api.thedogapi.com/v1/breeds";

    public static void main(String[] args) {
        // Create a Scanner to get user input
        Scanner scanner = new Scanner(System.in);

        // Prompt user for preferences
        System.out.println("Enter your living situation (apartment/house):");
        String livingSituation = scanner.nextLine().toLowerCase();

        System.out.println("Enter your size preference (small/medium/large):");
        String sizePref = scanner.nextLine().toLowerCase();

        System.out.println("Enter your energy level preference (low/medium/high):");
        String energyPref = scanner.nextLine().toLowerCase();

        // Fetch dog breeds from the API
        JsonArray breeds = fetchBreedsFromAPI();

        Stack<String> searchHistory = new Stack<>();

        ArrayList<DogBreed> breedList = new ArrayList<>();

        // Convert fetched JSON into DogBreed objects and populate the ArrayList
        populateBreedList(breeds, breedList);

        // Create a graph to represent relationships between breeds
        DogBreedGraph breedGraph = new DogBreedGraph();
        DogBreedGraph.populateBreedGraph(breedList, breedGraph);

        // Use a binary search tree to organize breeds by size (or energy)
        TreeSet<DogBreed> breedTree = new TreeSet<>(Comparator.comparing(DogBreed::getSize));

        // Add breeds to the BST for fast size-based searching
        breedTree.addAll(breedList);

        // Filter breeds based on user preferences
        System.out.println("\nRecommended dog breeds based on your preferences:");
        filterBreeds(breedList, breedGraph, breedTree, searchHistory, sizePref, energyPref, livingSituation);

        scanner.close();  // Close the scanner
    }

    // Fetch the list of dog breeds from the API using HttpURLConnection
    private static JsonArray fetchBreedsFromAPI() {
        try {
            URL url = new URL(BASE_URL + "?api_key=" + API_KEY);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            StringBuilder response = new StringBuilder();
            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();

            Gson gson = new Gson();
            return gson.fromJson(response.toString(), JsonArray.class);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    // Method to populate the breed list from the fetched JSON data
    private static void populateBreedList(JsonArray breeds, ArrayList<DogBreed> breedList) {
        if (breeds != null) {
            for (int i = 0; i < breeds.size(); i++) {
                JsonObject breed = breeds.get(i).getAsJsonObject();
                String breedName = breed.get("name").getAsString();

                // Use default size and energy for now since the API doesn't provide this info
                Size size = Size.Medium;  // Default size (you can map these better later)
                EnergyLevel energy = EnergyLevel.Low;  // Default energy

                // Create DogBreed object
                DogBreed dogBreed = new DogBreed(breedName, size, energy);
                breedList.add(dogBreed);
            }
        }
    }

    // Method to filter breeds based on user preferences
    private static void filterBreeds(ArrayList<DogBreed> breedList, DogBreedGraph breedGraph, TreeSet<DogBreed> breedTree,
                                     Stack<String> searchHistory, String sizePref, String energyPref, String livingSituation) {

        // Parse the user preferences into enums
        Size userSize = parseSize(sizePref);
        EnergyLevel userEnergy = parseEnergyLevel(energyPref);

        // Iterate through the breed list and find matches
        for (DogBreed breed : breedList) {
            // Check if breed matches size and energy preferences
            if (breed.getSize() == userSize && breed.getEnergyLevel() == userEnergy) {
                System.out.println("Breed: " + breed.getBreed());
                searchHistory.push(breed.getBreed());  // Add breed to search history stack
            }
        }
    }

    // Helper method to parse size from string to enum
    private static Size parseSize(String sizeStr) {
        try {
            return Size.valueOf(sizeStr.toUpperCase());
        } catch (IllegalArgumentException e) {
            // Default to Medium if invalid size
            return Size.Medium;
        }
    }

    // Helper method to parse energy from string to enum
    private static EnergyLevel parseEnergyLevel(String energyStr) {
        try {
            return EnergyLevel.valueOf(energyStr.toUpperCase());
        } catch (IllegalArgumentException e) {
            // Default to Low if invalid energy level
            return EnergyLevel.Low;
        }
    }
}