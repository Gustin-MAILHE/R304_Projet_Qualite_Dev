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

        for (int i = 0 ; i < rd.nextInt(1, 8) ; ++i){
            String sexe;
            int height = rd.nextInt(30) + 150;
            if (rd.nextBoolean()){
                sexe = "Male";
            }
            else{
                sexe = "Female";
            }




            this.pack.addLimb(new Werewolf(male.getName() + "JR" + i, sexe, height, 0, rd.nextInt(20), this.pack));
        }
    }
}
