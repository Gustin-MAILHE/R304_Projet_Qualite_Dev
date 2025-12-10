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
    }
    public Howl(String type, boolean isAnswer, ArrayList<Werewolf> targets, Werewolf source) {
        this.type = type;
        this.isAnswer = isAnswer;
        this.targets = targets;
        this.source = source;

        switch (type)
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
                this.submission(source);
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
        System.out.println( "Werewolf " + source.getName() + " howl his pack : \n" + source.getPack() );
    }

    void domination(Werewolf target)
    {

    }

    void submission(Werewolf source)
    {

    }

    void agressiveness(Werewolf target)
    {

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
