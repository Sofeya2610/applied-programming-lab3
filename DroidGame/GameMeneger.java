package DroidGame;

import Droids.Droid;

public class GameMeneger {
    private Droid[] currentDroids = null;
    private GameRec lastGameRec = null;

    public void startMatch(Droid[] teamBlue, Droid[] teamRed, int teamSize, String matchName) {
        GameRec gameRec = new GameRec(matchName);
        for (Droid d : teamBlue) gameRec.teamBlueNames.add(d.getName());
        for (Droid d : teamRed) gameRec.teamRedNames.add(d.getName());
        this.lastGameRec = gameRec;

        Game game = new Game(teamBlue, teamRed, teamSize, gameRec);
        game.Start();
        gameRec.saveGame();
        this.currentDroids = combineTeams(teamBlue, teamRed);
    }

    private Droid[] combineTeams(Droid[] teamBlue, Droid[] teamRed) {
        Droid[] all = new Droid[teamBlue.length + teamRed.length];
        System.arraycopy(teamBlue, 0, all, 0, teamBlue.length);
        System.arraycopy(teamRed, 0, all, teamBlue.length, teamRed.length);
        return all;
    }

    public void saveLastGame() {
        if (lastGameRec != null) {
            lastGameRec.saveGame();
            System.out.println("Гру збережено у файл.");
        } else {
            System.out.println("Немає гри для збереження.");
        }
    }

    public void replayMatch(String name, Droid[] droids) {
        GameRec gameRec = new GameRec(name);
        gameRec.readGame(name);
        System.out.println("Відтворення дій бою '" + name + "':");
        for (String action : gameRec.getActions()) {
            System.out.println(action);
        }
    }

    public Droid[] getDroids() {
        return currentDroids != null ? currentDroids : new Droid[0];
    }
}
