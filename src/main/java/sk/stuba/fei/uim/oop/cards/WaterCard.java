package sk.stuba.fei.uim.oop.cards;

import java.util.List;

public class WaterCard extends Card{

    public WaterCard(String name) {
        super(name);
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public void gettingShotAt( List<Card> pond, List<Card> deckOfDucks) {

    };
}
