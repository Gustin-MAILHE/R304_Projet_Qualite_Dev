package asterix.werewolf;

import java.util.ArrayList;

public class Pack {

    private AlphaCouple alphaCouple;
    private ArrayList<Werewolf> limbs;

    public AlphaCouple getAlphaCouple()
    {
        return alphaCouple;
    }
    public void setAlphaCouple(AlphaCouple alphaCouple)
    {
        this.alphaCouple = alphaCouple;
    }

    @Override
    public String toString()
    {
        StringBuilder res = new StringBuilder("===== Pack =====\n\n");
        res.append(alphaCouple.toString()).append("\n\n");

        res.append("=== Pack's Limbs ===\n");
        for (Werewolf w : limbs)
        {
            res.append("\t - ").append(w.getName()).append("\n");
        }
        return res.toString();
    }

    public String showLimbs()
    {
        StringBuilder res = new StringBuilder();
        for (Werewolf w : this.limbs)
        {
            res.append(w.toString()).append("\n");
        }
        return res.toString();
    }

    public void addLimb( Werewolf l )
    {
        this.limbs.add( l );
        System.out.println( "New limb "+ l.getName() + " added" );
    }
    public void dellLimb( Werewolf l )
    {
        this.limbs.remove( l );
    }



    public void initAlphaCouple()
    {
        // Strongest Male & Female choice
        Werewolf aMale = null;
        long maleStrength = 0;
        Werewolf aFemale = null;
        long femaleStrength = 0;

        for (Werewolf w : this.limbs)
        {
            if (w.getSexe().equals("Female"))
            {
                if (w.getStrength() > femaleStrength)
                {
                    aFemale = w;
                    femaleStrength = w.getStrength();
                }
            }
            else
            {
                if (w.getStrength() > maleStrength)
                {
                    aMale = w;
                    maleStrength = w.getStrength();
                }
            }
        }

        // Check if there is an alpha male and an alpha female to create the alphaCouple( aMale, aFemale )
        if ( aMale != null && aFemale != null )
        {
            AlphaCouple aC = new AlphaCouple( aMale, aFemale );
            this.setAlphaCouple(aC);
        }
    }

    private boolean isThereMaleAndFemale(ArrayList<Werewolf> limbs)
    {
        boolean male = false;
        boolean female = false;

        for (Werewolf w : limbs)
        {
            if (w.getSexe().equals("Female")) female = true;
            else male = true;
        }

        return male && female;
    }
    public Pack newPack( ArrayList<Werewolf> limbs )
    {

        Pack newPack = new Pack();

        for ( Werewolf w : limbs )
        {
            newPack.addLimb( w );
            this.dellLimb( w );
        }

        newPack.initAlphaCouple();
        return newPack;
    }
}
