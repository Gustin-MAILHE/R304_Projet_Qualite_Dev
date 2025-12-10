package asterix.werewolf;

import asterix.Character;

public class Werewolf extends Character {
    private String ageCategorie;
    private float dominationFactor;
    private String rank;
    private long level;
    private long thinkingStrngh;
    private Pack pack;

    public Werewolf(String name, String sexe, long height, long age, long strength, Pack pack)
    {
        super(name, sexe, height, age, strength);
        this.pack = pack;
    }

    @Override
    public String toString() {
        String result =
                "Werewolf : " + this.getName()
                + "\t - sexe : " + this.getSexe()
                + "\t - height : " + this.getHeight()
                + "\t - age : " + this.getAge()
                + "\t - strength : " + this.getStrength();
        return result;
    }

    public void Howl(String type, boolean isAnswer, Werewolf target)
    {

    }

    void hearHowl()
    {

    }

    void breakUp()
    {

    }

    void toHuman()
    {

    }


    public Pack getPack() {
        return pack;
    }
    public void setPack(Pack pack) {
        this.pack = pack;
    }
}
