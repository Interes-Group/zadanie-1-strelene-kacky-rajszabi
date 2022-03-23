package sk.stuba.fei.uim.oop.sittingduck;

import sk.stuba.fei.uim.oop.cards.AimedAtCard;
import sk.stuba.fei.uim.oop.cards.Card;
import sk.stuba.fei.uim.oop.cards.DuckCard;
import sk.stuba.fei.uim.oop.cards.WaterCard;
import sk.stuba.fei.uim.oop.cards.actioncard.*;
import sk.stuba.fei.uim.oop.player.Player;
import sk.stuba.fei.uim.oop.utility.ZKlavesnice;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SittingDuck {

    private List<Card> pond;
    private List<Card> deckOfDucks;
    private List<ActionCard> actionCards;
    private List<AimedAtCard> crossHairs;
    private final Player[] players;
    private int currentPlayer;
    private int roundCounter;

    public SittingDuck() {
        System.out.println("Welcome to FEI SITTING DUCK");
        int numberPlayers = ZKlavesnice.readInt("Enter number of players: ");
        this.players = new Player[numberPlayers];
        for (int i=0; i<numberPlayers; i++) {
            this.players[i] = new Player(ZKlavesnice.readString("Enter PLAYER " + (i + 1) + "name: "));
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
            System.out.println("--- PLAYER " + activePlayer.getName() + " STARTS TURN ---");
            System.out.println("Player 1 Ducks left: " + players[0].getAllDuckCard().size());
            showBoard();
            System.out.println("Your cards: ");
            activePlayer.chooseActionCard(crossHairs, pond, deckOfDucks, actionCards);
        }
        System.out.println("--- GAME FINISHED ---");
        System.out.println("And the WINNER is " + getWinner().getName());

    }

    private void showBoard() {
        System.out.println("------------------------------------");
        for (int i=0; i<6; i++) {
            System.out.println((i+1) + ". " + crossHairs.get(i).getStatus() + " -  " + pond.get(i).getName());
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
        crossHairs = new ArrayList<>();
        for (int i=0; i<6; i++) {
            crossHairs.add(new AimedAtCard("Not aimed at"));
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
        Collections.shuffle(deckOfDucks);
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
            actionCards.add(new AimCard("Aim"));
        }
        for (int i=0; i<12; i++) {
            actionCards.add(new ShootCard("Shoot"));
        }
        for (int i=0; i<2; i++) {
            actionCards.add(new WildBillCard("Wild Bill"));
        }
        for (int i=0; i<6; i++) {
            actionCards.add(new DuckMarchCard("Duck march"));
        }
        for (int i=0; i<2; i++) {
            actionCards.add(new ScatterCard("Scatter"));
        }
        actionCards.add(new TurboduckCard("Turboduck"));
        actionCards.add(new DuckDanceCard("Duck dance"));
        Collections.shuffle(actionCards);
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
