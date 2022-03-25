package sk.stuba.fei.uim.oop.crosshairs;

import sk.stuba.fei.uim.oop.cards.AimedAtCard;

import java.util.ArrayList;
import java.util.List;

public class CrossHairs {
    private int size;
    private List<AimedAtCard> aimedAtCards;

    public CrossHairs() {
        this.size = 0;
        this.aimedAtCards = new ArrayList<>();
    }

    public void addCrossHair(AimedAtCard aimedAtCard) {
        this.aimedAtCards.add(aimedAtCard);
    }

    public AimedAtCard getCrossHairByIndex(int index) {
        return this.aimedAtCards.get(index);
    }

    public int getSize() {
        return size;
    }

    public void updateCrossHair(int index, String name, boolean status) {
        boolean aimedAtCardStatus = aimedAtCards.get(index).getStatus();
        if (aimedAtCardStatus && !status) {
            size--;
        } else if (!aimedAtCardStatus && status) {
            size++;
        }
        this.aimedAtCards.get(index).changeValues(name, status);
    }
}
