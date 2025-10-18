package Droids;

public class HeavyDroid extends Droid {
    public HeavyDroid(String name, double health, double maxHealth, double damage, double critDamage, double critChance, double hitChance) {
        super(name, health, maxHealth, damage, critDamage, critChance, hitChance);
    }

    public void useAbility(Droid[] team, Droid[] enemyTeam, int teamSize) {
        int targetIndex = rand.nextInt(teamSize);
        enemyTeam[targetIndex].setHitChance(0.5);
        System.out.println(this.getName() + " використовує вміння і знижує шанс влучення " + enemyTeam[targetIndex].getName() + " до 50%.");
    }
}
