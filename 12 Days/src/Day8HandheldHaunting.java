import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;

public class Day8HandheldHaunting {
    public static void main(String[] args) {
        System.out.println("Day 8 Part 1: ");
        System.out.println(findValueBeforeRepeat("12 Days/Files/Day8.txt"));
        System.out.println("Day 8 Part 2: ");
        System.out.println(fixCorruptedInstruction("12 Days/Files/Day8.txt"));
    }

    public static String[] readFile(String filePath)  {
        try {
            return Files.readAllLines(Path.of(filePath)).toArray(new String[0]);
        } catch (IOException e) {
            e.printStackTrace();
            return new String[0];
        }
    }

    public static Integer findValueBeforeRepeat (String filePath) {
        String[] lines = readFile(filePath);
//        lines[57] = "nop";
        ArrayList<Integer> occurredAction = new ArrayList<>();
        int index = 0;
        int acc = 0;
        return interpretActions(lines, index, acc, occurredAction);
    }

    public static Integer interpretActions(String[] data, int index, int acc, ArrayList<Integer> occurredActions) {
        if (occurredActions.contains(index)) {
            return acc;
        }
        occurredActions.add(index);
        System.out.println(index);
        String action = data[index];
        switch (action.substring(0, 3)) {
            case "nop":
                return interpretActions(data, index + 1, acc, occurredActions);
            case "acc":
                if (action.charAt(4) == '+') acc += Integer.parseInt(action.substring(5));
                else if (action.charAt(4) == '-') acc -= Integer.parseInt(action.substring(5));
                return interpretActions(data, index + 1, acc, occurredActions);
            case "jmp":
                int jump = 1;
                if (action.charAt(4) == '+') jump = Integer.parseInt(action.substring(5));
                else if (action.charAt(4) == '-') jump = -(Integer.parseInt(action.substring(5)));
                return interpretActions(data, index + jump, acc, occurredActions);
            default:
                throw new IllegalArgumentException("Unknown instruction: " + action);
        }
    }

    public static int[] runProgramIterative(String[] data) {
        int acc = 0;
        int index = 0;
        boolean[] visited = new boolean[data.length];

        while (index < data.length) {
            if (visited[index]) {
                return new int[]{acc, 0};
            }
            visited[index] = true;

            String line = data[index];
            String op = line.substring(0, 3);
            int arg = Integer.parseInt(line.substring(4));

            switch (op) {
                case "acc":
                    acc += arg;
                    index++;
                    break;
                case "jmp":
                    index += arg;
                    break;
                case "nop":
                    index++;
                    break;
            }
        }

        return new int[]{acc, 1};
    }

    public static Integer fixCorruptedInstruction(String filePath) {
        String[] lines = readFile(filePath);

        for (int i = 0; i < lines.length; i++) {
            String op = lines[i].substring(0, 3);

            if (op.equals("acc")) continue;

            String[] modified = lines.clone();

            if (op.equals("nop")) {
                modified[i] = "jmp" + modified[i].substring(3);
            } else if (op.equals("jmp")) {
                modified[i] = "nop" + modified[i].substring(3);
            }

            int[] result = runProgramIterative(modified);
            if (result[1] == 1) {
                return result[0];
            }
        }

        return null;
    }


}