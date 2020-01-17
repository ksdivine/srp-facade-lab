package srpfacadelab;

import java.util.List;


public class RpgPlayer {

    private final IGameEngine gameEngine;

    private final Inventory inventory;

    private final Calculations calculations;

    private int health;

    private int maxHealth;

    private int armour;

    // How much the player can carry in pounds
    private int carryingCapacity;

    public RpgPlayer(IGameEngine gameEngine, Inventory inventory, Calculations calculations) {
        this.gameEngine = gameEngine;
        this.inventory = inventory;
        this.calculations = calculations;
    }

    public void useItem(Item item) {
        if (item.getName().equals("Stink Bomb"))
        {
            List<IEnemy> enemies = gameEngine.getEnemiesNear(this);

            for (IEnemy enemy: enemies){
                enemy.takeDamage(100);
            }
        }
    }

    public boolean pickUpItem(Item item) {
        int weight = calculations.getCalculatedWeights();
        

        if(item.isRare() && item.isUnique())
        {
            gameEngine.playSpecialEffect("blue_swirly");
        }

        if (weight + item.getWeight() > carryingCapacity)
            return false;

        if (item.isUnique() && inventory.checkIfItemExistsInInventory(item))
            return false;

        // Don't pick up items that give health, just consume them.
        if (item.getHeal() > 0) {
            health += item.getHeal();

            if (health > maxHealth)
                health = maxHealth;

            if (item.getHeal() > 500) {
                gameEngine.playSpecialEffect("green_swirly");
            }

            return true;
        }

        if (item.isRare())
            gameEngine.playSpecialEffect("cool_swirly_particles");

        inventory.add(item);

        calculations.getStats();

        return true;
    }


    public void takeDamage(int damage) {

        int curWeight = calculations.getCalculatedWeights();

        if(curWeight/carryingCapacity <= (calculations.getCalculatedWeights()/2))
        {
            damage = (int)(damage * .75);
        }

        if (damage < armour) {
            gameEngine.playSpecialEffect("parry");
        }

        int damageToDeal = damage - armour;
        health -= damageToDeal;

        gameEngine.playSpecialEffect("lots_of_gore");
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getMaxHealth() {
        return maxHealth;
    }

    public void setMaxHealth(int maxHealth) {
        this.maxHealth = maxHealth;
    }

    public int getArmour() {
        return armour;
    }

    public void setArmour(int armour) {
        this.armour = armour;
    }

}
