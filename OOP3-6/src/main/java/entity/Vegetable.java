package entity;

public abstract class Vegetable extends Food {
    private boolean isPickled;

    public Vegetable(boolean ripe, int nutrition, boolean isPickled) {
        super(ripe, nutrition);
        this.isPickled = isPickled;
    }

    public boolean isPickled() {
        return isPickled;
    }

    public void setPickled(boolean pickled) {
        isPickled = pickled;
    }
}
