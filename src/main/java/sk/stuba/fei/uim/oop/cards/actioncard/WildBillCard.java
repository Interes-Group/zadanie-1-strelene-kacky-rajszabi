package sk.stuba.fei.uim.oop.cards.actioncard;

import sk.stuba.fei.uim.oop.cards.AimedAtCard;
import sk.stuba.fei.uim.oop.cards.Card;
import sk.stuba.fei.uim.oop.player.Player;
import sk.stuba.fei.uim.oop.utility.ZKlavesnice;

import java.util.List;

public class WildBillCard extends ActionCard {

    public WildBillCard(String name) {
        super(name);
    }

    @Override
    public void activate(List<AimedAtCard> crossHairs, List<Card> pond, List<Card> deckOfDucks) {
        // TODO: 23. 3. 2022 Exception: you can only shoot at a duck
        int tile = ZKlavesnice.readInt("Select the duck you want to shoot at..");
        crossHairs.get(tile-1).setStatus("Not aimed at");
        pond.get(tile-1).gettingShotAt(pond, deckOfDucks);
    }

    @Override
    public String getName() {
        return this.name;
    }
}
