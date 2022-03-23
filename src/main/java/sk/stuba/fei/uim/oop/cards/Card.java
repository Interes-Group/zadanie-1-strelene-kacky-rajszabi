package sk.stuba.fei.uim.oop.cards;

import java.util.List;

public abstract class Card {
    protected String name;

    public Card(String name) {
        this.name = name;
    }

    public abstract String getName();
    public abstract void gettingShotAt(List<Card> pond, List<Card> deckOfDucks);
}
