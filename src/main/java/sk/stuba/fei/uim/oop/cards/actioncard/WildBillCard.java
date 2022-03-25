package sk.stuba.fei.uim.oop.cards.actioncard;

import sk.stuba.fei.uim.oop.cards.Card;
import sk.stuba.fei.uim.oop.crosshairs.CrossHairs;
import sk.stuba.fei.uim.oop.utility.ZKlavesnice;

import java.util.List;

public class WildBillCard extends ActionCard {

    public WildBillCard(String name) {
        super(name);
    }

    @Override
    public void activate(CrossHairs crossHairs, List<Card> pond, List<Card> deckOfDucks) {
        while (true) {
            int tile = ZKlavesnice.readInt("Select the duck you want to shoot at [1-6]..");
            if (1 <= tile && tile <= 6) {
                crossHairs.updateCrossHair(tile - 1, "Not aimed at", false);
                pond.get(tile - 1).gettingShotAt(pond, deckOfDucks);
                break;
            }
            else {
                System.out.println("Wrong input number...");
            }
        }
    }

    @Override
    public String getName() {
        return this.name;
    }
}
