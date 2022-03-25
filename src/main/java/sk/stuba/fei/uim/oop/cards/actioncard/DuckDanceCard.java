package sk.stuba.fei.uim.oop.cards.actioncard;

import sk.stuba.fei.uim.oop.cards.AimedAtCard;
import sk.stuba.fei.uim.oop.cards.Card;
import sk.stuba.fei.uim.oop.crosshairs.CrossHairs;

import java.util.List;

public class DuckDanceCard extends ActionCard{

    public DuckDanceCard(String name) {
        super(name);
    }

    @Override
    public void activate(CrossHairs crossHairs, List<Card> pond, List<Card> deckOfDucks) {
        deckOfDucks.addAll(pond);
        pond.clear();
        for (int i=0; i<6; i++) {
            pond.add(deckOfDucks.remove(0));
        }
    }

    @Override
    public String getName() {
        return this.name;
    }
}
