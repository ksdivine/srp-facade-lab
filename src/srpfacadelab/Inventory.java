package srpfacadelab;

import java.util.List;

public class Inventory
{
    public static final int MAX_CARRYING_CAPACITY = 1000;


    private int carryingCapacity;

    private List<Item> inventory;


    public Inventory()
    {
        carryingCapacity = MAX_CARRYING_CAPACITY;
    }

    public void add(Item item)
    {
        inventory.add(item);
    }

    public List<Item> getInventory()
    {
        return inventory;
    }

    public boolean checkIfItemExistsInInventory(Item item) {
        for (Item i: inventory) {
            if (i.getId() == item.getId())
                return true;
        }
        return false;
    }

    public int getCarryingCapacity() {
        return carryingCapacity;
    }

    private void setCarryingCapacity(int carryingCapacity) {
        this.carryingCapacity = carryingCapacity;
    }

}