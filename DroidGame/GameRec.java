package DroidGame;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class GameRec {
    private String gameName;
    public List<String> teamBlueNames;
    public List<String> teamRedNames;
    public List<String> actions;

    public GameRec(String gameName) {
        this.gameName = gameName;
        this.actions = new ArrayList<>();
        this.teamBlueNames = new ArrayList<>();
        this.teamRedNames = new ArrayList<>();
    }

    public void writeAction(String action) {
        actions.add(action);
    }

    public void saveGame() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("saves.txt"))) {
            writer.write(gameName + "\n");
            writer.write(String.join(",", teamBlueNames) + "\n");
            writer.write(String.join(",", teamRedNames) + "\n");
            writer.write(String.join(",", actions) + "\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void readGame(String gameName) {
        try (BufferedReader reader = new BufferedReader(new FileReader("saves.txt"))) {
            String name = reader.readLine();
            String blueLine = reader.readLine();
            String redLine = reader.readLine();
            String actionsLine = reader.readLine();

            if (Objects.equals(name, gameName)) {
                this.teamBlueNames = List.of(blueLine.split(","));
                this.teamRedNames = List.of(redLine.split(","));
                this.actions = List.of(actionsLine.split(","));
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public List<String> getActions() {
        return actions;
    }
}
