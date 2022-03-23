package sk.stuba.fei.uim.oop.cards.actioncard;

import sk.stuba.fei.uim.oop.cards.AimedAtCard;
import sk.stuba.fei.uim.oop.cards.Card;

import java.util.List;

public class DuckMarchCard extends ActionCard{

    public DuckMarchCard(String name) {
        super(name);
    }

    @Override
    public void activate(List<AimedAtCard> crossHairs, List<Card> pond, List<Card> deckOfDucks) {
        deckOfDucks.add(pond.remove(0));
        pond.add(deckOfDucks.remove(0));
    }

    @Override
    public String getName() {
        return this.name;
    }
}
