package sk.stuba.fei.uim.oop.cards.actioncard;

import sk.stuba.fei.uim.oop.cards.Card;
import sk.stuba.fei.uim.oop.crosshairs.CrossHairs;

import java.util.Collections;
import java.util.List;

public class ScatterCard extends ActionCard{

    public ScatterCard(String name) {
        super(name);
    }

    @Override
    public void activate(CrossHairs crossHairs, List<Card> pond, List<Card> deckOfDucks) {
        Collections.shuffle(pond);
    }

    @Override
    public String getName() {
        return this.name;
    }
}
