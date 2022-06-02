package entity;

public class Carrot extends Vegetable{
    public final String classID = "Carrot";

    public Carrot(boolean ripe, int nutrition, boolean isPickled) {
        super(ripe, nutrition, isPickled);
    }

    @Override
    public String toString() {
        StringBuilder tempString = new StringBuilder();
        tempString
                .append("Морковь: ")
                .append(this.isRipe() ? "Спелая" : "Не спелая").append(", ")
                .append("калорийность: ").append(getNutrition()).append(", ")
                .append(this.isPickled() ? "маринованная" : "свежая").append(".");
        return tempString.toString();
    }
}

