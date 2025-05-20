package dk.sdu.cbse.common.components;

public class AttackComponent {
    private int attackChance;
    private int coolDown;

    private int timeSinceLastAttacked;

    public AttackComponent(int attackChance, int coolDown, int timeSinceLastAttacked) {
        this.attackChance = attackChance;
        this.coolDown = coolDown;
        this.timeSinceLastAttacked = timeSinceLastAttacked;
    }

    public int getAttackChance() {
        return attackChance;
    }

    public void setAttackChance(int attackChance) {
        this.attackChance = attackChance;
    }

    public int getCoolDown() {
        return coolDown;
    }

    public void setCoolDown(int coolDown) {
        this.coolDown = coolDown;
    }

    public int getTimeSinceLastAttacked() {
        return timeSinceLastAttacked;
    }

    public void setTimeSinceLastAttacked(int timeSinceLastAttacked) {
        this.timeSinceLastAttacked = timeSinceLastAttacked;
    }
}
