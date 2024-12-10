package Model;

public class User {
    private int age;
    private int hoursAway;
    private EnergyLevel dogEnergyPref;
    private Size sizePref;
    private LivingSituation livingSit;

    public User(int age, EnergyLevel dogEnergyPref, Size sizePref,
                LivingSituation livingSit) {
        this.age = age;
        this.dogEnergyPref = dogEnergyPref;
        this.sizePref = sizePref;
        this.livingSit = livingSit;
    }
    public User() {

    }



    public void setAge(int age) {
        this.age = age;
    }
    public int getAge() {
        return age;
    }
    public void setDogEnergyPref(EnergyLevel dogEnergyPref) {
        this.dogEnergyPref = dogEnergyPref;
    }
    public EnergyLevel getDogEnergyPref() {
        return dogEnergyPref;
    }
    public void setSizePref(Size sizePref) {
        this.sizePref = sizePref;
    }
    public Size getSizePref() {
        return sizePref;
    }
    public void setLivingSit(LivingSituation livingSit) {
        this.livingSit = livingSit;
    }
    public LivingSituation getLivingSit() {
        return livingSit;
    }

    @Override
    public String toString() {
        return "User Profile {age: " + age + ", hours away from home: " + hoursAway + ", dog size "
                + "preference: " + sizePref + ", dog energy preference: " + dogEnergyPref
                + ", living situation: " + livingSit + "}";
    }
}
