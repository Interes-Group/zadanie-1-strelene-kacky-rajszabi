package sk.stuba.fei.uim.oop.cards;

public class AimedAtCard {
    private String name;
    private boolean status;

    public AimedAtCard(String name, boolean status) {
        this.name = name;
        this.status = status;
    }

    public void changeValues(String name, boolean status) {
        this.name = name;
        this.status = status;
    }

    public boolean getStatus() {
        return this.status;
    }

    public String getName() {
        return this.name;
    }
}
