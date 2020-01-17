package srpfacadelab;

public class Calculations
{
    private Inventory inventory;

    private RpgPlayer rpgPlayer;


    private Calculations()
    {
    }

    public void getStats()
    {
        calculateStats();
    }

    public int getCalculatedWeights()
    {
        return calculateInventoryWeight();
    }

    private void calculateStats() {
        for (Item i: inventory.getInventory()) {
            rpgPlayer.setArmour(rpgPlayer.getArmour() + i.getArmour());
        }
    }

    private int calculateInventoryWeight() {
        int sum=0;
        for (Item i: inventory.getInventory()) {
            sum += i.getWeight();
        }
        return sum;
    }

}