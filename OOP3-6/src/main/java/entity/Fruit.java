package entity;

public abstract class Fruit extends Food {
    private boolean isDried;

    public Fruit(boolean ripe, int nutrition, boolean isDried) {
        super(ripe, nutrition);
        this.isDried = isDried;
    }

    public boolean isDried() {
        return isDried;
    }

    public void setDried(boolean dried) {
        isDried = dried;
    }
}
