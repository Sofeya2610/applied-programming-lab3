package DroidGame;

import Droids.Droid;

import java.awt.*;
import java.util.Random;
import static java.lang.Thread.sleep;

public class Game {
    private Droid[] teamBlue, teamRed;
    private int teamSize;
    private GameRec gameRec;

    public Game(Droid[] teamBlue, Droid[] teamRed, int teamSize, GameRec gameRec) {
        this.teamBlue = teamBlue;
        this.teamRed = teamRed;
        this.teamSize = teamSize;
        this.gameRec = gameRec; // Зберігаємо посилання на GameRec
    }

    public void Start() {
        Random rand = new Random();

        for (int i = 0; ; i++) {
            int index1 = getAlive(teamBlue);
            int index2 = getAlive(teamRed);

            if (index1 == -1) {
                System.out.print("\u001B[43m" + "Команда Червоних перемогла \u001B[0m");
                gameRec.writeAction("\u001B[43m" + "Команда Червоних перемогла \u001B[0m");
                break;
            } else if (index2 == -1) {
                System.out.print("\u001B[43m " + "Команда Синіх перемогла \u001B[0m");
                gameRec.writeAction("\u001B[43m" + "Команда Синіх перемогла \u001B[0m");
                break;
            }

            System.out.print("\u001B[34m");
            teamBlue[index1].attack(teamRed[index2]);
            gameRec.writeAction(teamBlue[index1].getName() + " атакує " + teamRed[index2].getName());
            ActionDelay(2000);

            System.out.print("\u001B[31m");
            teamRed[index2].attack(teamBlue[index1]);
            gameRec.writeAction(teamRed[index2].getName() + " атакує " + teamBlue[index1].getName());
            ActionDelay(2000);

            if (rand.nextInt(4) < 2) {
                System.out.print("\u001B[32m");
                teamRed[index2].useAbility(teamRed, teamBlue, teamSize);
                gameRec.writeAction(teamRed[index2].getName() + " використовує вміння.");
            } else {
                System.out.print("\u001B[32m");
                teamBlue[index1].useAbility(teamBlue, teamRed, teamSize);
                gameRec.writeAction(teamBlue[index1].getName() + " використовує вміння.");
            }
            System.out.print("\u001B[0m");
        }
    }


    private void ActionDelay(int delay) {
        try {
            Thread.sleep(delay);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    private int getAlive(Droid[] team) {
        for (int i = 0; i < teamSize; i++) {
            if (team[i].isAlive()) {
                return i;
            }
        }
        return -1;
    }
}
