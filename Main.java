import DroidGame.GameMeneger;
import DroidGame.GameRec;
import Droids.Droid;
import Droids.HeavyDroid;
import Droids.LightDroid;
import Droids.SupportDroid;

import java.util.*;

public class Main {
    private static List<Droid> allDroids = new ArrayList<>();

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        allDroids.addAll(createDroids()); // стартовий набір

        int menu;
        GameMeneger gameMeneger = new GameMeneger();

        do {
            System.out.println("\nМеню:");
            System.out.println(" 1. Створити дроїда");
            System.out.println(" 2. Переглянути дроїдів");
            System.out.println(" 3. Запустити бій 1 на 1");
            System.out.println(" 4. Запустити бій команда на команду");
            System.out.println(" 5. Записати проведений бій у файл");
            System.out.println(" 6. Відтворити проведений бій зі збереженого файлу");
            System.out.println(" 0. Вийти\n Ваш вибір:");
            menu = sc.nextInt();
            sc.nextLine();

            switch (menu) {
                case 1:
                    createNewDroid(sc);
                    break;
                case 2:
                    printDroids();
                    break;
                case 3:
                    startFight(sc, gameMeneger, 1);
                    break;
                case 4:
                    startFight(sc, gameMeneger, 4);
                    break;
                case 5:
                    gameMeneger.saveLastGame();
                    break;
                case 6:
                    replayFight(sc, gameMeneger);
                    break;
                default:
                    if (menu != 0)
                        System.out.println("Некоректний ввід.");
            }
        } while (menu != 0);

        System.out.println("Дякуємо за гру!");
    }

    public static List<Droid> createDroids() {
        List<Droid> droids = new ArrayList<>();
        droids.add(new HeavyDroid("Titanium Hulk", 250.0, 250.0, 35.0, 70.0, 0.15, 0.85));
        droids.add(new HeavyDroid("Iron Guardian", 230.0, 230.0, 30.0, 65.0, 0.20, 0.80));
        droids.add(new HeavyDroid("Steel Behemoth", 260.0, 260.0, 40.0, 75.0, 0.10, 0.90));
        droids.add(new LightDroid("Speedster", 150.0, 150.0, 25.0, 50.0, 0.30, 0.95));
        droids.add(new LightDroid("Phantom Blade", 140.0, 140.0, 28.0, 55.0, 0.35, 0.90));
        droids.add(new LightDroid("Shadow Striker", 145.0, 145.0, 26.0, 53.0, 0.40, 0.92));
        droids.add(new SupportDroid("Medi-Bot", 180.0, 180.0, 15.0, 30.0, 0.25, 0.88, 4.0));
        droids.add(new SupportDroid("Nano-Healer", 170.0, 170.0, 18.0, 35.0, 0.22, 0.90, 7.0));
        droids.add(new SupportDroid("RepairMaster", 160.0, 160.0, 20.0, 40.0, 0.28, 0.87, 5.0));
        droids.add(new HeavyDroid("Rock Crusher", 240.0, 240.0, 37.0, 68.0, 0.18, 0.83));
        droids.add(new LightDroid("Blaze Runner", 135.0, 135.0, 27.0, 52.0, 0.33, 0.94));
        droids.add(new SupportDroid("Shield Drone", 165.0, 165.0, 22.0, 38.0, 0.24, 0.89, 9.0));
        droids.add(new HeavyDroid("Titanium Wall", 255.0, 255.0, 32.0, 60.0, 0.12, 0.88));
        droids.add(new LightDroid("Lightning", 142.0, 142.0, 29.0, 54.0, 0.36, 0.91));
        droids.add(new SupportDroid("Overclock", 175.0, 175.0, 19.0, 36.0, 0.29, 0.85, 10.0));
        return droids;
    }

    public static void createNewDroid(Scanner sc) {
        System.out.println("Виберіть тип дроїда: 1 - Heavy, 2 - Light, 3 - Support");
        int type = sc.nextInt();
        sc.nextLine();
        System.out.print("Введіть ім'я дроїда: ");
        String name = sc.nextLine();
        System.out.print("Введіть здоров'я: ");
        double health = sc.nextDouble();
        System.out.print("Введіть максимальне здоров'я: ");
        double maxHealth = sc.nextDouble();
        System.out.print("Введіть шкоду: ");
        double damage = sc.nextDouble();
        System.out.print("Введіть критичну шкоду: ");
        double critDamage = sc.nextDouble();
        System.out.print("Введіть шанс криту (0-1): ");
        double critChance = sc.nextDouble();
        System.out.print("Введіть шанс попадання (0-1): ");
        double hitChance = sc.nextDouble();

        if (type == 1) {
            allDroids.add(new HeavyDroid(name, health, maxHealth, damage, critDamage, critChance, hitChance));
        } else if (type == 2) {
            allDroids.add(new LightDroid(name, health, maxHealth, damage, critDamage, critChance, hitChance));
        } else if (type == 3) {
            System.out.print("Введіть силу лікування: ");
            double abilityHeal = sc.nextDouble();
            allDroids.add(new SupportDroid(name, health, maxHealth, damage, critDamage, critChance, hitChance, abilityHeal));
        }
        System.out.println("Створено дроїда!");
    }


    public static void printDroids() {
        if (allDroids.isEmpty()) {
            System.out.println("Немає дроїдів!");
            return;
        }
        for (int i = 0; i < allDroids.size(); i++) {
            System.out.println(i + ": " + allDroids.get(i));
        }
    }

    public static void startFight(Scanner sc, GameMeneger gameMeneger, int teamSize) {
        if (allDroids.size() < teamSize * 2) {
            System.out.println("Недостатньо дроїдів для бою.");
            return;
        }
        Droid[] teamBlue = new Droid[teamSize];
        Droid[] teamRed = new Droid[teamSize];
        System.out.println("Виберіть індекси для команди Синіх:");
        for (int i = 0; i < teamSize; i++) {
            printDroids();
            System.out.print("Індекс дроїда для Синіх [" + i + "]: ");
            int idx = sc.nextInt();
            teamBlue[i] = allDroids.get(idx);
        }
        System.out.println("Виберіть індекси для команди Червоних:");
        for (int i = 0; i < teamSize; i++) {
            printDroids();
            System.out.print("Індекс дроїда для Червоних [" + i + "]: ");
            int idx = sc.nextInt();
            teamRed[i] = allDroids.get(idx);
        }
        sc.nextLine();
        System.out.print("Введіть назву бою: ");
        String matchName = sc.nextLine();

        gameMeneger.startMatch(teamBlue, teamRed, teamSize, matchName);
    }

    public static void replayFight(Scanner sc, GameMeneger gameMeneger) {
        System.out.print("Введіть назву бою для відтворення: ");
        String name = sc.nextLine();
        gameMeneger.replayMatch(name, allDroids.toArray(new Droid[0]));
    }
}
