package Model;

public class DogBreed {
    private String breed;
    private Size size;
    private EnergyLevel energyLevel;



    public DogBreed(String breed, Size size, EnergyLevel energyLevel) {
        this.breed = breed;
        this.size = size;
        this.energyLevel = energyLevel;
    }

    public void setBreed(String breed) {
        this.breed = breed;
    }
    public String getBreed() {
        return breed;
    }
    public void setEnergyLevel(EnergyLevel energyLevel) {
        this.energyLevel = energyLevel;
    }
    public EnergyLevel getEnergyLevel() {
        return energyLevel;
    }
    public void setSize(Size size) {
        this.size = size;
    }
    public Size getSize() {
        return size;
    }
    @Override
    public String toString() {
        return "Dog Breed{breed: " + breed + ", size: " + size + ", energy level: "
                + energyLevel + "}";
    }
}
