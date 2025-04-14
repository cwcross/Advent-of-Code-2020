import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class Day3TobogganTrajectory {
    public static void main(String[] args) {
        String[] map = readFile("12 Days/Files/Day3Map.txt");
        System.out.println(countTrees(map, 1, 1));
        System.out.println(countTrees(map, 3, 1));
        System.out.println(countTrees(map, 5, 1));
        System.out.println(countTrees(map, 7, 1));
        System.out.println(countTrees(map, 1, 2));
        long multipliedTotal = (long) countTrees(map, 1, 1) * countTrees(map, 3, 1) * countTrees(map, 5, 1) * countTrees(map, 7, 1) * countTrees(map, 1, 2);
        System.out.println("All multiplied: " + multipliedTotal);
    }


    public static String[] readFile(String fileName) {
        try {
            return Files.readAllLines(Path.of(fileName)).toArray(new String[0]);
        } catch (IOException e) {
            e.printStackTrace();
            return new String[0];
        }
    }

    public static int countTrees(String[] map, int x, int y) {
        int count = 0;
        int currentY = 0;
        int currentX = 0;
        int rowLength = map[0].length();
        int columnHeight = map.length;
        for(int i = 0; i < map.length/y; i++) {
            if(map[currentY % columnHeight].charAt(currentX % rowLength) == '#') count++;
            currentY += y;
            currentX += x;
        }
        return count;
    }
}
