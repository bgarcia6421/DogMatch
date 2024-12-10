package Service;

import Model.DogBreed;

public class DogBreedTree {
    class Node {
        DogBreed breed;
        Node left;
        Node right;

        public Node(DogBreed breed) {
            this.breed = breed;
            left = null;
            right = null;
        }
    }
    private Node root;

    public DogBreedTree() {
        root = null;
    }
    private Node addBreed(Node root, DogBreed breed) {
        if(root == null) {
            root = new Node(breed);
            return root;
        }
        if(breed.getBreed().compareTo(root.breed.getBreed()) < 0) {
            root.left = addBreed(root.left, breed);
        }
        else if(breed.getBreed().compareTo(root.breed.getBreed()) > 0) {
            root.right = addBreed(root.right, breed);
        }
        else {
            return root;
        }
        return root;
    }
    public void add(DogBreed breed) {
        root = addBreed(root, breed);
    }
    private void traverseInOrderBreeds(Node root) {
        if(root != null) {
            traverseInOrderBreeds(root.left);
            System.out.println(root.breed);
            traverseInOrderBreeds(root.right);
        }
    }
}
