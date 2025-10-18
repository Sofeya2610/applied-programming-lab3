package Droids;
import java.util.Random;

public abstract class Droid {
    private String name;
    private double health;
    private double maxHealth;
    private double damage;
    private double critDamage;
    private double critChance;
    private double hitChance;
    protected Random rand = new Random();

    public Droid(String name, double health, double maxHealth, double damage, double critDamage, double critChance, double hitChance) {
        this.name = name;
        this.health = health;
        this.maxHealth = maxHealth;
        this.damage = damage;
        this.critDamage = critDamage;
        this.critChance = critChance;
        this.hitChance = hitChance;
    }

    public void attack(Droid enemy) {
        if (rand.nextDouble() <= hitChance) {
            double dealtDamage = rand.nextDouble() < critChance ? critDamage : damage;
            enemy.takeDamage(dealtDamage);
            System.out.println(this.name + " завдає " + dealtDamage + " шкоди " + enemy.getName());
        } else {
            System.out.println(this.name + " промахується!");
        }
    }

    public void takeDamage(double amount) {
        health -= amount;
        if (health < 0) health = 0;
    }

    public void Heal(double amount) {
        health += amount;
        if (health > maxHealth) health = maxHealth;
    }

    public boolean isAlive() {
        return health > 0;
    }

    public String getName() {
        return name;
    }

    public double getHealth() {
        return health;
    }

    public void setHitChance(double hitChance) {
        this.hitChance = hitChance;
    }

    public abstract void useAbility(Droid[] team, Droid[] enemyTeam, int teamSize);
    @Override
    public String toString() {
        return name
                + " (HP: " + health
                + "/" + maxHealth
                + ", DMG: " + damage
                + ", CritDMG: " + critDamage
                + ", Crit%: " + critChance
                + ", Hit%: " + hitChance + ")";
    }

}

