package asterix.werewolf;

import asterix.Character;

import java.util.ArrayList;

public class Werewolf extends Character {
    private String ageCategorie; // "jeune", "adulte", "vieux"
    private float dominationFactor;
    private String rank;
    private long level;
    private long thinkingStrengh;
    private Pack pack;

    public Werewolf(String name, String sexe, long height, long age, long strength, Pack pack)
    {
        super(name, sexe, height, age, strength);
        this.pack = pack;
    }

    @Override
    public String toString()
    {
        String result =
                "Werewolf : " + this.getName()
                + "\t - sexe : " + this.getSexe()
                + "\t - height : " + this.getHeight()
                + "\t - age : " + this.getAge()
                + "\t - strength : " + this.getStrength();
        return result;
    }

    public void Howl(String type, boolean isAnswer, ArrayList<Werewolf> targets)
    {
        new Howl(type, isAnswer, targets, this);
        if (!isAnswer) {
            System.out.println( "Werewolf " + this.getName() + " has started a " + type + "howl" );
        }
    }

    public void hearHowl( Howl howl )
    {
        switch (howl.type)
        {
            case "Domination" :
                if (howl.isATarget(this))
                {

                }
                break;
            case "Pack":
                new Howl("Pack", true, this);
        }
    }

    public void breakUp()
    {

    }

    public void toHuman()
    {

    }


    public Pack getPack()
    {
        return pack;
    }
    public void setPack(Pack pack)
    {
        this.pack = pack;
    }
}
