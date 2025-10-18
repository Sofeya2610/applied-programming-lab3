package Droids;

public class LightDroid extends Droid {
    public LightDroid(String name, double health, double maxHealth, double damage, double critDamage, double critChance, double hitChance) {
        super(name, health, maxHealth, damage, critDamage, critChance, hitChance);
    }

    public void useAbility(Droid[] team, Droid[] enemyTeam, int teamSize) {
        this.setHitChance(1);
        System.out.println(this.getName() + " використовує вміння і отримує шанс влучань 100%.");
    }
}
