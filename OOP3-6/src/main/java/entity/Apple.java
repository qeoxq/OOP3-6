package entity;

public class Apple extends Fruit{
    public final String classID = "Apple";

    public Apple(boolean ripe, int nutrition, boolean isDried) {
        super(ripe, nutrition, isDried);
    }

    @Override
    public String toString() {
        StringBuilder tempString = new StringBuilder();
        tempString
                .append("Яблоко: ")
                .append(this.isRipe() ? "Спелое" : "Не спелое").append(", ")
                .append("калорийность: ").append(getNutrition()).append(", ")
                .append(this.isDried() ? "сушеное" : "свежее").append(".");
        return tempString.toString();
    }

    public static AppleBuilder builder() {
        return new AppleBuilder();
    }

    public static class AppleBuilder {
        private boolean ripe;
        private int nutrition;
        private boolean isDried;

        public AppleBuilder setRipe(boolean ripe) {
            this.ripe = ripe;
            return this;
        }

        public AppleBuilder setNutrition(int nutrition) {
            this.nutrition = nutrition;
            return this;
        }

        public AppleBuilder setDried(boolean isDried) {
            this.isDried = isDried;
            return this;
        }

        public Apple build() {
            return new Apple(ripe, nutrition, isDried);
        }
    }
}
