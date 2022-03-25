package sk.stuba.fei.uim.oop.player;

import sk.stuba.fei.uim.oop.cards.Card;
import sk.stuba.fei.uim.oop.cards.DuckCard;
import sk.stuba.fei.uim.oop.cards.actioncard.ActionCard;
import sk.stuba.fei.uim.oop.cards.actioncard.AimCard;
import sk.stuba.fei.uim.oop.cards.actioncard.ShootCard;
import sk.stuba.fei.uim.oop.crosshairs.CrossHairs;
import sk.stuba.fei.uim.oop.utility.ZKlavesnice;

import java.util.ArrayList;
import java.util.List;

public class Player {

    private final String name;
    private boolean active;
    private List<DuckCard> duckCards;
    private List<ActionCard> actionCards;

    public Player(String name) {
        this.name = name;
        this.active = true;
        this.duckCards = new ArrayList<>();
        this.actionCards = new ArrayList<>();
    }

    public void chooseActionCard(CrossHairs crossHairs, List<Card> pond, List<Card> deckOfDucks, List<ActionCard> actionCards) {
        for (int i=0; i<3; i++) {
            System.out.println((i+1) + ". " + this.actionCards.get(i).getName());
        }
        while (true) {
            int card = ZKlavesnice.readInt("Pick an action card [1-3]: ");
            if (1 <= card && card <= 3) {
                ActionCard actionCard = this.actionCards.get(card - 1);
                System.out.println("You chose card: '" + actionCard.getName() + "'");
                if (actionCard instanceof AimCard && crossHairs.getSize() == 6) {
                    System.out.println("You cannot pick AimCard. All crosshairs are reserved. Pick another action card...");
                } else if (actionCard instanceof ShootCard && crossHairs.getSize() == 0) {
                    System.out.println("You cannot pick ShootCard. No crosshair have been placed yet. Pick another action card...");
                } else {
                    actionCard.activate(crossHairs, pond, deckOfDucks);
                    actionCards.add(this.actionCards.remove(card-1));
                    break;
                }
            }
            else {
                System.out.println("Wrong input number...");
            }
        }
        if (duckCards.size() == 0) {
            setActive(false);
            actionCards.addAll(this.actionCards);
        }
        else {
            this.actionCards.add(actionCards.remove(0));
        }
    }

    public void checkActionCardsPlayability(CrossHairs crossHairs, List<ActionCard> actionCards) {
        int counter;
        boolean playable = true;
        while (playable) {
            playable = false;
            System.out.println("Crosshairs size: " + crossHairs.getSize());
            if (crossHairs.getSize() == 6) {
                counter = 0;
                for (ActionCard actionCard : actionCards) {
                    if (actionCard instanceof AimCard) {
                        counter++;
                    }
                }
                if (counter == 3) {
                    actionCards.add(this.actionCards.remove(0));
                    this.actionCards.add(actionCards.remove(0));
                    playable = true;
                }
            }
            if (crossHairs.getSize() == 0) {
                counter = 0;
                for (ActionCard actionCard : actionCards) {
                    if (actionCard instanceof ShootCard) {
                        counter++;
                    }
                }
                if (counter == 3) {
                    actionCards.add(this.actionCards.remove(0));
                    this.actionCards.add(actionCards.remove(0));
                    playable = true;
                }
            }
        }
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public boolean isActive() {
        return active;
    }

    public List<DuckCard> getAllDuckCard() {
        return this.duckCards;
    }

    public void addActionCard(ActionCard actionCard) {
        this.actionCards.add(actionCard);
    }

    public void addDuckCard(DuckCard duckCard) {
        this.duckCards.add(duckCard);
    }

    public void removeDuckCard(DuckCard duckCard) {
        this.duckCards.remove(duckCard);
        if (this.duckCards.size() == 0) {
            this.active = false;
        }
    }

    public String getName() {
        return this.name;
    }

}
