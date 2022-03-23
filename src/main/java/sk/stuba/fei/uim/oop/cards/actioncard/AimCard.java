package sk.stuba.fei.uim.oop.cards.actioncard;

import sk.stuba.fei.uim.oop.cards.AimedAtCard;
import sk.stuba.fei.uim.oop.cards.Card;
import sk.stuba.fei.uim.oop.player.Player;
import sk.stuba.fei.uim.oop.utility.ZKlavesnice;

import java.util.List;

public class AimCard extends ActionCard {

    public AimCard(String name) {
        super(name);
    }

    @Override
    public void activate(List<AimedAtCard> crosshairs, List<Card> pond, List<Card> deckOfDucks) {
        // TODO: 23. 3. 2022 Exception: you can only put on a tile where there is no crosshair
        int tile = ZKlavesnice.readInt("Select the tile by its number you want to place your crosshair..");
        crosshairs.get(tile-1).setStatus("Aimed at");
    }

    @Override
    public String getName() {
        return this.name;
    }
}
