package entity;

public class Banana extends Fruit{
    public final String classID = "Banana";

    public Banana(boolean ripe, int nutrition, boolean isDried) {
        super(ripe, nutrition, isDried);
    }

    @Override
    public String toString() {
        StringBuilder tempString = new StringBuilder();
        tempString
                .append("Банан: ")
                .append(this.isRipe() ? "Спелый" : "Не спелый").append(", ")
                .append("калорийность: ").append(getNutrition()).append(", ")
                .append(this.isDried() ? "сушеный" : "свежий").append(".");
        return tempString.toString();
    }
}
