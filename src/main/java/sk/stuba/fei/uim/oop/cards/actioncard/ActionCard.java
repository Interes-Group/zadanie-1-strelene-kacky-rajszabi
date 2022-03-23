package sk.stuba.fei.uim.oop.cards.actioncard;

import sk.stuba.fei.uim.oop.cards.AimedAtCard;
import sk.stuba.fei.uim.oop.cards.Card;
import sk.stuba.fei.uim.oop.player.Player;

import java.util.List;

public abstract class ActionCard {
    protected String name;

    public ActionCard(String name) {
        this.name = name;
    }

    public abstract void activate(List<AimedAtCard> crossHairs, List<Card> pond, List<Card> deckOfDucks);
    public abstract String getName();
}
