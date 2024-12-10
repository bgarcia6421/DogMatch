package Service;

import Model.DogBreed;

import java.util.HashMap;
import java.util.Map;

public class DogBreedHashTable {
    private Map<String, DogBreed> breedTable;

    public DogBreedHashTable() {
        breedTable = new HashMap<>();
    }
    public void addBreed(String key, DogBreed breed) {
        breedTable.put(key, breed);
    }
    public DogBreed getBreed(String key) {
        return breedTable.get(key);
    }
}
