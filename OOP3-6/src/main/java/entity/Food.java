package entity;

import java.io.Serializable;

public abstract class Food implements Serializable {
    public final String classID = "Food";
    private boolean ripe;
    private int nutrition;

    public Food(boolean ripe, int nutrition) {
        this.ripe = ripe;
        this.nutrition = nutrition;
    }

    public boolean isRipe() {
        return ripe;
    }

    public void setRipe(boolean ripe) {
        this.ripe = ripe;
    }

    public int getNutrition() {
        return nutrition;
    }

    public void setNutrition(int nutrition) {
        this.nutrition = nutrition;
    }
}