
public class Desktop extends PC {
    private boolean hasDedicatedGPU;
    private int powerSupply; // σε W

    public Desktop(String processor, int ram, int storage, boolean hasDedicatedGPU, int powerSupply) {
        super(processor, ram, storage);
        this.hasDedicatedGPU = hasDedicatedGPU;
        this.powerSupply = powerSupply;
    }

    @Override
    public boolean isGamingSuitable() {
        return hasDedicatedGPU;
    }

    @Override
    public String toString() {
        return super.toString() + ", Dedicated GPU: " + hasDedicatedGPU + ", Power Supply: " + powerSupply + "W";
    }
}