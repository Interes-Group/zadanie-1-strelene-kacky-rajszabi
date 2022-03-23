package sk.stuba.fei.uim.oop.cards.actioncard;

import sk.stuba.fei.uim.oop.cards.AimedAtCard;
import sk.stuba.fei.uim.oop.cards.Card;
import sk.stuba.fei.uim.oop.utility.ZKlavesnice;

import java.util.List;

public class TurboduckCard extends ActionCard {

    public TurboduckCard(String name) {
        super(name);
    }

    @Override
    public void activate(List<AimedAtCard> crossHairs, List<Card> pond, List<Card> deckOfDucks) {
        int duck = ZKlavesnice.readInt("Select the duck you want to move to the first place..");
        pond.add(0, pond.remove(duck-1));
    }

    @Override
    public String getName() {
        return this.name;
    }
}
