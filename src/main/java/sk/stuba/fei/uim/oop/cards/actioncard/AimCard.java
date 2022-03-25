package sk.stuba.fei.uim.oop.cards.actioncard;

import sk.stuba.fei.uim.oop.cards.AimedAtCard;
import sk.stuba.fei.uim.oop.cards.Card;
import sk.stuba.fei.uim.oop.crosshairs.CrossHairs;
import sk.stuba.fei.uim.oop.utility.ZKlavesnice;

import java.util.List;

public class AimCard extends ActionCard {

    public AimCard(String name) {
        super(name);
    }

    @Override
    public void activate(CrossHairs crosshairs, List<Card> pond, List<Card> deckOfDucks) {
        while (true) {
            int tile = ZKlavesnice.readInt("Select the tile by its number you want to place your crosshair [1-6]..");
            if (1 <= tile && tile <= 6) {
//                AimedAtCard aimedAtCard = crosshairs.get(tile - 1);
                AimedAtCard aimedAtCard = crosshairs.getCrossHairByIndex(tile - 1);
                if (!aimedAtCard.getStatus()) {
//                    aimedAtCard.changeValues("Aimed at", true);
                    crosshairs.updateCrossHair(tile-1, "Aimed at", true);
                    break;
                } else {
                    System.out.println("The tile you have chosen already has a crosshair...");
                }
            } else {
                System.out.println("Wrong input number...");
            }
        }
    }

    @Override
    public String getName() {
        return this.name;
    }
}
