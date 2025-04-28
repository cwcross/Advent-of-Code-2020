import java.io.IOException;
import java.lang.reflect.Array;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import java.util.stream.Collectors;

class Food {
    List<String> ingredients;
    List<String> allergens;
}

public class Day21AllergenAssessment {
    public static void main(String[] args) throws IOException {
        System.out.println("Part 1: " + part1("12 Days/Files/Day21.txt"));
        System.out.println("Part 2: " + part2("12 Days/Files/Day21.txt"));
    }

    public static int part1(String filePath) {
        try {
            String[] lines = Files.readAllLines(Path.of(filePath)).toArray(new String[0]);
            List<Food> foods = new ArrayList<>();
            for (String line : lines) {
                String[] parts = line.split(" \\(contains ");
                Food f = new Food();
                f.ingredients = Arrays.asList(parts[0].split(" "));
                String allergenPart = parts[1].substring(0, parts[1].length() - 1);
                f.allergens = Arrays.asList(allergenPart.split(", "));
                foods.add(f);
            }
            // Time to see if I know how to use maps!
            Map<String, Set<String>> possible = new HashMap<>();
            for (Food f : foods) {
                for (String a : f.allergens) {
                    if (!possible.containsKey(a)) {
                        possible.put(a, new HashSet<>(f.ingredients));
                    } else {
                        possible.get(a).retainAll(f.ingredients);
                    }
                }
            }
            Set<String> candidates = new HashSet<>();
            for (Set<String> s : possible.values()) {
                candidates.addAll(s);
            }

            int safeCount = 0;
            for (Food f : foods) {
                for (String ing : f.ingredients) {
                    if (!candidates.contains(ing)) {
                        safeCount++;
                    }
                }
            }
            return safeCount;
        } catch (IOException e) {
            e.printStackTrace();
            return 0;
        }
    }

    // Most of this is copied from part 1
    public static String part2(String filePath) {
        List<String> lines;
        try {
            lines = Files.readAllLines(Path.of(filePath));
        } catch (IOException e) {
            e.printStackTrace();
            return "";
        }

        List<Food> foods = new ArrayList<>();
        for (String line : lines) {
            String[] parts = line.split(" \\(contains ");
            Food f = new Food();
            f.ingredients = Arrays.asList(parts[0].split(" "));
            String allergenPart = parts[1].substring(0, parts[1].length() - 1);
            f.allergens = Arrays.asList(allergenPart.split(", "));
            foods.add(f);
        }

        Map<String, Set<String>> possible = new HashMap<>();
        for (Food f : foods) {
            for (String allergen : f.allergens) {
                if (!possible.containsKey(allergen)) {
                    possible.put(allergen, new HashSet<>(f.ingredients));
                } else {
                    possible.get(allergen).retainAll(f.ingredients);
                }
            }
        }

        Map<String, String> resolved = new HashMap<>();
        while (resolved.size() < possible.size()) {
            for (Map.Entry<String, Set<String>> entry : possible.entrySet()) {
                String allergen = entry.getKey();
                Set<String> candidates = entry.getValue();
                if (candidates.size() == 1 && !resolved.containsKey(allergen)) {
                    String ingredient = candidates.iterator().next();
                    resolved.put(allergen, ingredient);

                    for (Set<String> otherSet : possible.values()) {
                        if (otherSet != candidates) {
                            otherSet.remove(ingredient);
                        }
                    }
                }
            }
        }

        List<String> allergens = new ArrayList<>(resolved.keySet());
        Collections.sort(allergens);

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < allergens.size(); i++) {
            String allergen = allergens.get(i);
            String ingredient = resolved.get(allergen);

            sb.append(ingredient);
            if (i < allergens.size() - 1) {
                sb.append(',');
            }
        }

        return sb.toString();
    }

}
