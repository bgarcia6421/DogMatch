package Service;

import Model.DogBreed;

import java.util.*;

public class DogBreedGraph {
    private Map<DogBreed, List<DogBreed>> adjList;

    public DogBreedGraph() {
        adjList = new HashMap<>();
    }

    public void addEdge(DogBreed src, DogBreed dest) {
        adjList.putIfAbsent(src, new ArrayList<>());
        adjList.putIfAbsent(dest, new ArrayList<>());
        adjList.get(src).add(dest);
        adjList.get(dest).add(src);
    }

    public List<DogBreed> getNeighbors(DogBreed breed) {
        return adjList.getOrDefault(breed, new ArrayList<>());
    }

    public void printGraph() {
        for (Map.Entry<DogBreed, List<DogBreed>> entry : adjList.entrySet()) {
            System.out.print(entry.getKey().getBreed() + " -> ");
            for (DogBreed neighbor : entry.getValue()) {
                System.out.print(neighbor.getBreed() + " ");
            }
            System.out.println();
        }
    }
    public static void populateBreedGraph(List<DogBreed> breedList, DogBreedGraph breedGraph) {
        for (int i = 0; i < breedList.size(); i++) {
            DogBreed breed = breedList.get(i);
            for (int j = i + 1; j < breedList.size(); j++) {
                DogBreed otherBreed = breedList.get(j);
                if (breed.getSize() == otherBreed.getSize() && breed.getEnergyLevel() == otherBreed.getEnergyLevel()) {
                    breedGraph.addEdge(breed, otherBreed);  // Add bidirectional edge
                }
            }
        }
    }
}