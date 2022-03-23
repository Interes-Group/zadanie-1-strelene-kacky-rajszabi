package sk.stuba.fei.uim.oop.player;

import sk.stuba.fei.uim.oop.cards.AimedAtCard;
import sk.stuba.fei.uim.oop.cards.Card;
import sk.stuba.fei.uim.oop.cards.DuckCard;
import sk.stuba.fei.uim.oop.cards.actioncard.ActionCard;
import sk.stuba.fei.uim.oop.utility.ZKlavesnice;

import javax.swing.*;
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

    public void chooseActionCard(List<AimedAtCard> crossHairs, List<Card> pond, List<Card> deckOfDucks, List<ActionCard> actionCards) {
        for (int i=0; i<3; i++) {
            System.out.println((i+1) + ". " + this.actionCards.get(i).getName());
        }
        int card = ZKlavesnice.readInt("Pick an action card(1-3): ");
        ActionCard actionCard = this.actionCards.remove(card-1);
        System.out.println("You chose card: '" + actionCard.getName() + "'");
        actionCard.activate(crossHairs, pond, deckOfDucks);
        if (duckCards.size() == 0) {
            setActive(false);
        }
        else {
            this.actionCards.add(actionCards.remove(0));
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

    public List<ActionCard> getAllActionCard() {
        return this.actionCards;
    }

    public void addDuckCard(DuckCard duckCard) {
        this.duckCards.add(duckCard);
    }

    public void removeDuckCard(DuckCard duckCard) {
        this.duckCards.remove(duckCard);
    }

    public String getName() {
        return this.name;
    }

}
