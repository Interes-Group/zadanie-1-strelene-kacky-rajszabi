package sk.stuba.fei.uim.oop.sittingduck;

import sk.stuba.fei.uim.oop.cards.AimedAtCard;
import sk.stuba.fei.uim.oop.cards.Card;
import sk.stuba.fei.uim.oop.cards.DuckCard;
import sk.stuba.fei.uim.oop.cards.WaterCard;
import sk.stuba.fei.uim.oop.cards.actioncard.*;
import sk.stuba.fei.uim.oop.crosshairs.CrossHairs;
import sk.stuba.fei.uim.oop.player.Player;
import sk.stuba.fei.uim.oop.utility.ZKlavesnice;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SittingDuck {

    private List<Card> pond;
    private List<Card> deckOfDucks;
    private List<ActionCard> actionCards;
    private CrossHairs crossHairs;
    private final Player[] players;
    private int currentPlayer;
    private int roundCounter;

    public SittingDuck() {
        System.out.println("Welcome to FEI SITTING DUCK");
        while (true) {
            int numberPlayers = ZKlavesnice.readInt("Enter number of players [2-6]: ");
            if (2 <= numberPlayers && numberPlayers <= 6) {
                this.players = new Player[numberPlayers];
                break;
            } else {
                System.out.println("Wrong input. Try again...");
            }
        }
        for (int i=0; i<players.length; i++) {
            this.players[i] = new Player(ZKlavesnice.readString("Enter PLAYER " + (i + 1) + " name: "));
        }
        initializeBoard();
        startGame();
    }

    private void startGame() {
        System.out.println("--- GAME STARTED ---");
        for (this.currentPlayer = 0;getNumberActivePlayers() > 1;this.incrementCounter()) {
            if (this.currentPlayer == 0) {
                System.out.println("--- ROUND " + (this.roundCounter / this.players.length + 1) + " STARTS ---");
            }
            Player activePlayer = this.players[this.currentPlayer];
            if (!activePlayer.isActive()) {
                continue;
            }
            System.out.println("--- PLAYER " + (this.currentPlayer+1) + " STARTS TURN ---");
            System.out.println("Player " + activePlayer.getName() + "'s Ducks left: " + activePlayer.getAllDuckCard().size());
            showBoard();
            System.out.println("Your cards: ");
            activePlayer.checkActionCardsPlayability(crossHairs, actionCards);
            activePlayer.chooseActionCard(crossHairs, pond, deckOfDucks, actionCards);
        }
        System.out.println("--- GAME FINISHED ---");
        System.out.println("And the WINNER is " + getWinner().getName());

    }

    private void showBoard() {
        System.out.println("------------------------------------");
        for (int i=0; i<6; i++) {
            System.out.println((i+1) + ". " + crossHairs.getCrossHairByIndex(i).getName() + " -  " + pond.get(i).getName());
        }
        System.out.println("------------------------------------\n");
    }

    private void initializeBoard() {
        initializePlayerCards();
        initializeDeckOfDucks();
        initializeActionCards();
        initializeCrossHairs();
        initializePlayersActionCards();
        initializePond();
    }

    private void initializeCrossHairs() {
        crossHairs = new CrossHairs();
        for (int i=0; i<6; i++) {
            crossHairs.addCrossHair(new AimedAtCard("Not aimed at", false));
        }
    }

    private void initializePlayersActionCards() {
        for (Player player: players) {
            for (int i=0; i<3; i++) {
                player.addActionCard(actionCards.remove(0));
            }
        }
    }

    private void initializePond() {
        this.pond = new ArrayList<>();
        for (int i=0; i<6; i++) {
            pond.add(deckOfDucks.remove(0));
        }
    }

    private void initializeDeckOfDucks() {
        this.deckOfDucks = new ArrayList<>();
        for (Player player : players) {
            deckOfDucks.addAll(player.getAllDuckCard());
        }
        for (int i=0; i<6; i++) {
            deckOfDucks.add(new WaterCard("Water"));
        }
        for (int i=0; i<5; i++) {
            Collections.shuffle(deckOfDucks);
        }
    }

    private void initializePlayerCards() {
        for (int i=0; i<players.length; i++) {
            for (int j = 0; j < 5; j++) {
                players[i].addDuckCard(new DuckCard("Duck of player " + (i+1), players[i]));
            }
        }
    }

    private void initializeActionCards() {
        this.actionCards = new ArrayList<>();
        for (int i=0; i<10; i++) {
            this.actionCards.add(new AimCard("Aim"));
        }
        for (int i=0; i<12; i++) {
            this.actionCards.add(new ShootCard("Shoot"));
        }
        for (int i=0; i<2; i++) {
            this.actionCards.add(new WildBillCard("Wild Bill"));
        }
        for (int i=0; i<6; i++) {
            this.actionCards.add(new DuckMarchCard("Duck march"));
        }
        for (int i=0; i<2; i++) {
            this.actionCards.add(new ScatterCard("Scatter"));
        }
        this.actionCards.add(new TurboduckCard("Turboduck"));
        this.actionCards.add(new DuckDanceCard("Duck dance"));
        for (int i=0; i<5; i++) {
            Collections.shuffle(this.actionCards);
        }
    }

    private void incrementCounter() {
        this.currentPlayer++;
        this.currentPlayer %= this.players.length;
        this.roundCounter++;
    }

    private int getNumberActivePlayers() {
        int count = 0;
        for (Player player : this.players) {
            if (player.isActive()) {
                count++;
            }
        }
        return count;
    }

    private Player getWinner() {
        for (Player player : this.players) {
            if (player.isActive()) {
                return player;
            }
        }
        return null;
    }
}
