
public abstract class PC {
    protected String processor;
    protected int ram; // σε GB
    protected int storage; // σε GB

    public PC(String processor, int ram, int storage) {
        this.processor = processor;
        this.ram = ram;
        this.storage = storage;
    }

    public abstract boolean isGamingSuitable();

    public int getStorage() {
        return storage;
    }

    @Override
    public String toString() {
        return "Processor: " + processor + ", RAM: " + ram + "GB, Storage: " + storage + "GB";
    }
}
