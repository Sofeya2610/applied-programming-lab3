package Droids;

public class SupportDroid extends Droid {
    private double abilityHeal;

    public SupportDroid(String name, double health, double maxHealth, double damage, double critDamage, double critChance, double hitChance, double abilityHeal) {
        super(name, health, maxHealth, damage, critDamage, critChance, hitChance);
        this.abilityHeal = abilityHeal;
    }

    public void useAbility(Droid[] team, Droid[] enemyTeam, int teamSize) {
        for (int i = 0; i < teamSize; i++) {
            team[i].Heal(this.abilityHeal);
        }
        System.out.println(this.getName() + " використовує вміння і лікує кожного напарника на " + abilityHeal);
    }
}
