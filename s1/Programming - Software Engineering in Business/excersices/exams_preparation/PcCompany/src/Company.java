import java.util.ArrayList;

public class Company {
    private String name;
    private ArrayList<PC> fleet;

    public Company(String name) {
        this.name = name;
        this.fleet = new ArrayList<>();
    }

    public void addPC(PC pc) {
        fleet.add(pc);
    }

    public int getTotalStorage() {
        int totalStorage = 0;
        for (PC pc : fleet) {
            totalStorage += pc.getStorage();
        }
        return totalStorage;
    }

    @Override
    public String toString() {
        return "Company: " + name + ", Total PCs: " + fleet.size();
    }
}