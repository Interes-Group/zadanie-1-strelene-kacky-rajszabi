package sk.stuba.fei.uim.oop.cards;

import sk.stuba.fei.uim.oop.player.Player;

import java.util.List;

public class DuckCard extends Card {
    private Player player;

    public DuckCard(String name, Player player) {
        super(name);
        this.player = player;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public void gettingShotAt(List<Card> pond, List<Card> deckOfDucks) {
        pond.remove(this);
        this.player.removeDuckCard(this);
        pond.add(deckOfDucks.remove(0));
    }
}
