package asterix.werewolf;

public class Howl {
    String type;
    boolean isAnswer;
    Werewolf target;

    public Howl(String type) {
        this.type = type;
        this.isAnswer = false;
    }

    public Howl(String type, boolean isAnswer, Werewolf target) {
        this.type = type;
        this.isAnswer = isAnswer;
        this.target = target;
    }

    void pack()
    {

    }

    void domination(Werewolf target)
    {

    }

    void submission()
    {

    }

    void agressiveness(Werewolf target)
    {

    }
}
