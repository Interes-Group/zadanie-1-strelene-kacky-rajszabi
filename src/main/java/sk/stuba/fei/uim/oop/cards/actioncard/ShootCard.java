package sk.stuba.fei.uim.oop.cards.actioncard;

import sk.stuba.fei.uim.oop.cards.AimedAtCard;
import sk.stuba.fei.uim.oop.cards.Card;
import sk.stuba.fei.uim.oop.crosshairs.CrossHairs;
import sk.stuba.fei.uim.oop.utility.ZKlavesnice;

import java.util.List;

public class ShootCard extends ActionCard{

    public ShootCard(String name) {
        super(name);
    }

    @Override
    public void activate(CrossHairs crossHairs, List<Card> pond, List<Card> deckOfDucks) {
        // TODO: 24. 3. 2022 Implement: cant activate ShootCard if there are not crosshairs.
        while (true) {
            int tile = ZKlavesnice.readInt("Select the pond you want to shoot at [1-6]..");
            if (1 <= tile && tile <= 6) {
                AimedAtCard aimedAtCard = crossHairs.getCrossHairByIndex(tile-1);
                if (aimedAtCard.getStatus()) {
                    crossHairs.updateCrossHair(tile-1, "Not aimed at", false);
                    pond.get(tile - 1).gettingShotAt(pond, deckOfDucks);
                    break;
                } else {
                    System.out.println("The pond you chose does not have a crosshair...");
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
