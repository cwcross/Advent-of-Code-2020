import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class Day12RainRisk {
    public static void main(String[] args) {
        String[] directions = readFile("12 Days/Files/Day12.txt");
        System.out.println(processDirections(directions));
        System.out.println(processDirections2(directions));
    }

    public static String[] readFile(String fileName) {
        try {
            return Files.readAllLines(Path.of(fileName)).toArray(new String[0]);
        } catch (IOException e) {
            e.printStackTrace();
            return new String[0];
        }
    }

    public static int processDirections(String[] directions) {
        int northSouth = 0;
        int eastWest = 0;
        int facing = 1; // 0 is north, 1 is east, 2 is south, 3 is west
        for (String action : directions) {
            if (action.contains("N")) northSouth += Integer.parseInt(action.substring(1));
            else if (action.contains("E")) eastWest += Integer.parseInt(action.substring(1));
            else if (action.contains("S")) northSouth -= Integer.parseInt(action.substring(1));
            else if (action.contains("W")) eastWest -= Integer.parseInt(action.substring(1));
            else if (action.contains("L")) facing = (facing - (Integer.parseInt(action.substring(1)) / 90) + 4) % 4;
            else if (action.contains("R")) facing = (facing + (Integer.parseInt(action.substring(1)) / 90)) % 4;
            else if (action.contains("F")) switch (facing) {
                case 0 -> northSouth += Integer.parseInt(action.substring(1));
                case 1 -> eastWest += Integer.parseInt(action.substring(1));
                case 2 -> northSouth -= Integer.parseInt(action.substring(1));
                case 3 -> eastWest -= Integer.parseInt(action.substring(1));
            }
        }
        return Math.abs(northSouth) + Math.abs(eastWest);
    }

    public static int processDirections2(String[] directions) {
        int shipNorthSouth = 0;
        int shipEastWest = 0;
        int waypointNorthSouth = 1;
        int waypointEastWest = 10;
        for (String action : directions) {
            if (action.contains("N")) waypointNorthSouth += Integer.parseInt(action.substring(1));
            else if (action.contains("E")) waypointEastWest += Integer.parseInt(action.substring(1));
            else if (action.contains("S")) waypointNorthSouth -= Integer.parseInt(action.substring(1));
            else if (action.contains("W")) waypointEastWest -= Integer.parseInt(action.substring(1));
            else if (action.contains("L")) switch (Integer.parseInt(action.substring(1)) % 360) {
                case 0:
                    break;
                case 90:
                    int oldNS = waypointNorthSouth;
                    waypointNorthSouth = waypointEastWest;
                    waypointEastWest = -oldNS;
                    break;
                case 180:
                    waypointNorthSouth = -waypointNorthSouth;
                    waypointEastWest = -waypointEastWest;
                    break;
                case 270:
                    int oldEW = waypointEastWest;
                    waypointEastWest = waypointNorthSouth;
                    waypointNorthSouth = -oldEW;
                    break;
            }
            else if (action.contains("R")) switch (Integer.parseInt(action.substring(1)) % 360) {
                case 0:
                    break;
                case 90:
                    int oldEW = waypointEastWest;
                    waypointEastWest = waypointNorthSouth;
                    waypointNorthSouth = -oldEW;
                    break;
                case 180:
                    waypointNorthSouth = -waypointNorthSouth;
                    waypointEastWest = -waypointEastWest;
                    break;
                case 270:
                    int oldNS = waypointNorthSouth;
                    waypointNorthSouth = waypointEastWest;
                    waypointEastWest = -oldNS;
                    break;
            }
            else if (action.contains("F")) {
                int numberOfLegs = Integer.parseInt(action.substring(1));
                shipNorthSouth += waypointNorthSouth * numberOfLegs;
                shipEastWest += waypointEastWest * numberOfLegs;
            }
        }
        return Math.abs(shipNorthSouth) + Math.abs(shipEastWest);
    }
}