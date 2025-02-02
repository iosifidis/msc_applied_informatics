
public class Laptop extends PC {
    private int batteryLife; // σε ώρες

    public Laptop(String processor, int ram, int storage, int batteryLife) {
        super(processor, ram, storage);
        this.batteryLife = batteryLife;
    }

    @Override
    public boolean isGamingSuitable() {
        return false; // Τα laptop σε αυτή την περίπτωση δεν θεωρούνται gaming PCs
    }

    @Override
    public String toString() {
        return super.toString() + ", Battery Life: " + batteryLife + " hours";
    }
}