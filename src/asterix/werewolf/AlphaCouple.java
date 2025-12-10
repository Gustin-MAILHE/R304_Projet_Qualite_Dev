package asterix.werewolf;

import java.util.ArrayList;
import java.util.Random;

public class AlphaCouple {
    Werewolf male;
    Werewolf female;
    Pack pack;

    public  AlphaCouple(Werewolf male, Werewolf female)
    {
        this.male = male;
        this.female = female;
        this.pack = male.getPack();
    }

    @Override
    public String toString()
    {
        String result = "Male: \n\t" + male.toString() + "\nFemale: \n\t" + female.toString();
        return result;
    }

    public void beget()
    {
        Random rd = new Random();
        // Specs definition
        for (int i = 0 ; i < rd.nextInt(1, 8) ; ++i) {
            String sexe;
            long height = 0;
            long strength = 0;
            // if this is a son
            if (rd.nextBoolean()) {
                sexe = "Male";
                height = rd.nextInt(50) + ( this.male.getHeight()-25 );
                strength = rd.nextInt(4) + ( this.male.getStrength() - 2 );
            }
            // if this is a daughter
            else {
                sexe = "Female";
                height = rd.nextInt(50) + ( this.female.getHeight()-25 );
                strength = rd.nextInt(4) + ( this.female.getStrength() - 2 );
            }


            this.pack.addLimb(new Werewolf(male.getName() + "JR" + i, sexe, height, 0, strength, this.pack));
        }
    }
}
