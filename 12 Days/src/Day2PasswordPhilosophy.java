public class Day2PasswordPhilosophy {
    public static void main(String[] args) {
    }

    public static Object findPasswordCondition(String input) {
        char character;
        String numbers = "";
        for (int i = 0; i < input.length(); i++) {
            if (input.charAt(i) == '-' || Character.isDigit(input.charAt(i))) {
                numbers += input.charAt(i);
            }
        }
        String[] range = numbers.split("-");
        int min = Integer.parseInt(range[0]);
        int max = Integer.parseInt(range[1]);

        for (int i = 0; true; i++) {
            if (Character.isAlphabetic(input.charAt(i))) {
                character = input.charAt(i);
                break;
            }
        }


    }
}
