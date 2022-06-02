package entity;

public class Cucumber extends Vegetable {
    public final String classID = "Cucumber";


    public Cucumber(boolean ripe, int nutrition, boolean isPickled) {
        super(ripe, nutrition, isPickled);
    }

    @Override
    public String toString() {
        StringBuilder tempString = new StringBuilder();
        tempString
                .append("Огурец: ")
                .append(this.isRipe() ? "Спелый" : "Не спелый").append(", ")
                .append("калорийность: ").append(getNutrition()).append(", ")
                .append(this.isPickled() ? "маринованный" : "свежий").append(".");
        return tempString.toString();
    }
}
