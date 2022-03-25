package sk.stuba.fei.uim.oop.cards.actioncard;

import sk.stuba.fei.uim.oop.cards.Card;
import sk.stuba.fei.uim.oop.cards.DuckCard;
import sk.stuba.fei.uim.oop.crosshairs.CrossHairs;
import sk.stuba.fei.uim.oop.utility.ZKlavesnice;

import java.util.List;

public class TurboduckCard extends ActionCard {

    public TurboduckCard(String name) {
        super(name);
    }

    @Override
    public void activate(CrossHairs crossHairs, List<Card> pond, List<Card> deckOfDucks) {
        // TODO: 25. 3. 2022 Check if there are only water tiles
        while (true) {
            int duck = ZKlavesnice.readInt("Select the duck you want to move to the first place [1-6]..");
            if (1 <= duck && duck <= 6) {
                Card card = pond.get(duck-1);
                if (card instanceof DuckCard) {
                    pond.add(0, pond.remove(duck - 1));
                    break;
                } else {
                    System.out.println("You cannot pick water...");
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
