package sk.stuba.fei.uim.oop.cards.actioncard;

import sk.stuba.fei.uim.oop.cards.AimedAtCard;
import sk.stuba.fei.uim.oop.cards.Card;
import sk.stuba.fei.uim.oop.utility.ZKlavesnice;

import java.util.List;

public class ShootCard extends ActionCard{

    public ShootCard(String name) {
        super(name);
    }

    @Override
    public void activate(List<AimedAtCard> crossHairs, List<Card> pond, List<Card> deckOfDucks) {
        // TODO: 23. 3. 2022 Exception when player gave wrong tile of crosshairs.
        int tile = ZKlavesnice.readInt("Select the pond you want to shoot at..");
        crossHairs.get(tile-1).setStatus("Not aimed at");
        pond.get(tile-1).gettingShotAt(pond, deckOfDucks);
    }

    @Override
    public String getName() {
        return this.name;
    }
}
