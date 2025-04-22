import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

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

//    private static ArrayList<ArrayList<Character>> assembleGroupsTwo(String[] lines) {
//        ArrayList<ArrayList<Character>> groups = new ArrayList<>();
//
//        groups.add(assembleUniqueAnswersTwo(lines, 0));
//
//        for (int i = 1; i < lines.length; i++) {
//
//            if (lines[i].trim().isEmpty()) {
//                groups.add(assembleUniqueAnswersTwo(lines, i+1));
//            }
//
//        }
//        return groups;
//    }

    private static ArrayList<ArrayList<Character>> assembleGroupsTwo(String[] lines) {
        ArrayList<ArrayList<Character>> groups = new ArrayList<>();
        ArrayList<String> currentGroup = new ArrayList<>();

        for (String line : lines) {
            if (line.trim().isEmpty()) {
                groups.add(findCommonAnswers(currentGroup));
                currentGroup.clear();
            } else {
                currentGroup.add(line);
            }
        }

        if (!currentGroup.isEmpty()) {
            groups.add(findCommonAnswers(currentGroup));
        }

        return groups;
    }

    private static ArrayList<Character> findCommonAnswers(ArrayList<String> group) {
        Set<Character> common = new HashSet<>();

        for (int i = 0; i < group.size(); i++) {
            Set<Character> personAnswers = new HashSet<>();
            for (char c : group.get(i).toCharArray()) {
                personAnswers.add(c);
            }

            if (i == 0) {
                common.addAll(personAnswers);
            } else {
                common.retainAll(personAnswers);  // intersection
            }
        }

        return new ArrayList<>(common);
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

            for (int k = 0; k < answers.size(); k++) {
                Character answer = answers.get(k);

                if (lines[k].contains(answer.toString())) continue;
                else answers.remove(answer);
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
