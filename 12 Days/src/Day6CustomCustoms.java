import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;

public class Day6CustomCustoms {

    public static void main(String[] args) {
        System.out.println(partOne("12 Days/Files/Day6.txt"));
        System.out.println(partTwo("12 Days/Files/Day6.txt"));
    }

    public static String[] readFile(String filePath)  {
        try {
            return Files.readAllLines(Path.of(filePath)).toArray(new String[0]);
        } catch (IOException e) {
            e.printStackTrace();
            return new String[0];
        }
    }

    public static int partTwo(String filePath) {
        System.out.println("Part Two: ");
        return addEntries(addUpCharacters(assembleGroupsTwo(readFile(filePath)))) ;
    }

    private static ArrayList<ArrayList<Character>> assembleGroupsTwo(String[] lines) {
        ArrayList<ArrayList<Character>> groups = new ArrayList<>();

        groups.add(assembleUniqueAnswersTwo(lines, 0));

        for (int i = 1; i < lines.length; i++) {

            if (lines[i].trim().isEmpty()) {
                groups.add(assembleUniqueAnswersTwo(lines, i+1));
            }

        }
        return groups;
    }

    private static ArrayList<Character> assembleUniqueAnswersTwo(String[] lines, int start) {
        ArrayList<Character> answers = new ArrayList<>();

        for (Character c : lines[start].toCharArray()) {
            answers.add(c);
        }

        for (int j = start; j < lines.length; j++) {

            String line = lines[j];

            if (line.trim().isEmpty()) {
                break;
            }

            for (int i = 1; i < line.length(); i++) {

                Character c = line.charAt(i);

                for (int k = 0; k < answers.size(); k++) {
                    Character answer = answers.get(k);

                    if (lines[i].contains(answer.toString())) continue;
                    else answers.remove(answer);
                }
            }



        }
        return answers;
    }

    public static int partOne (String filePath) {
        System.out.println("Part One: ");
        return addEntries(addUpCharacters(assembleGroups(readFile(filePath)))) ;
    }

    private static ArrayList<Integer> addUpCharacters(ArrayList<ArrayList<Character>> groups) {
        ArrayList<Integer> counts = new ArrayList<>();

        for (ArrayList<Character> group : groups) {
            counts.add(group.size());
        }

        return counts;
    }

    private static int addEntries(ArrayList<Integer> numbers) {
        int total = 0;
        for (Integer number : numbers) {
            total += number;
        }
        return total;
    }

    private static ArrayList<ArrayList<Character>> assembleGroups(String[] lines) {
        ArrayList<ArrayList<Character>> groups = new ArrayList<>();

        groups.add(assembleUniqueAnswers(lines, 0));

        for (int i = 1; i < lines.length; i++) {

            if (lines[i].trim().isEmpty()) {
                groups.add(assembleUniqueAnswers(lines, i+1));
            }

        }
        return groups;
    }

    private static ArrayList<Character> assembleUniqueAnswers(String[] lines, int start) {
        ArrayList<Character> answers = new ArrayList<>();

        for (int j = start; j < lines.length; j++) {

            String line = lines[j];

            for (Character c : line.toCharArray()) {
                if (answers.contains(c)) continue;
                else answers.add(c);
            }

            if (line.trim().isEmpty()) {
                break;
            }

        }
        return answers;
    }

}
