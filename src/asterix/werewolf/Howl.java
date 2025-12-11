package asterix.werewolf;

import javax.crypto.spec.PSource;
import java.util.ArrayList;

public class Howl {
    String type;
    boolean isAnswer;
    ArrayList<Werewolf> targets;
    Werewolf source;

    public Howl(String type, boolean isAnswer, Werewolf source) {
        this.type = type;
        this.isAnswer = isAnswer;
        this.source = source;

        this.pack();
    }
    public Howl(String type, boolean isAnswer, ArrayList<Werewolf> targets, Werewolf source) {
        this.type = type;
        this.isAnswer = isAnswer;
        this.targets = targets;
        this.source = source;

        switchType();
    }

    private void switchType()
    {
        switch (this.type)
        {
            case "Pack":
                this.pack();
                break;
            case "Domination":
                for (Werewolf target : targets)
                {
                    this.domination(target);
                }
                break;
            case "Submission":
                this.submission(targets.getFirst());
                break;
            case "Agressiveness":
                for (Werewolf target : targets)
                {
                    this.agressiveness(target);
                }
                break;
            default:
                break;
        }
    }

    void pack()
    {
        System.out.println( "Le lycanthrope " + source.getName() + " cri sa meute : \n" + source.getPack() );
    }

    void domination(Werewolf target)
    {
        System.out.println( "Le lycanthrope " + source.getName() + " montre sa domination sur " + target.getName() );
    }

    void submission(Werewolf target)
    {
        System.out.println( "Le lycanthrope " + source.getName() + " admet sa soumission envers " + target.getName() );
    }

    void agressiveness(Werewolf target)
    {
        System.out.println( "Le lycanthrope " + source.getName() + " se montre agressif envers " + target.getName() );
    }

    public boolean isATarget(Werewolf werewolf)
    {
        for (Werewolf target : targets)
        {
            if (target == werewolf)
            {
                return true;
            }
        }
        return false;
    }
}
