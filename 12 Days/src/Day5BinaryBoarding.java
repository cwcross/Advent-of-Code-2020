import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;

public class Day5BinaryBoarding {
    public static void main(String[] args) {
//        System.out.println(findSeatIndex("FBFBBFFRLR"));
        System.out.println(findHighestSeatIndex("12 Days/Files/Day5.txt"));
        System.out.println(findOpenSeatFull("12 Days/Files/Day5.txt"));
    }

    public static int findHighestSeatIndex (String filePath) {
        try { return findGreatestInteger(assembleSeatIndices(readFile(filePath))); } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    public static String[] readFile(String filePath) throws IOException {
        try {
            return Files.readAllLines(Path.of(filePath)).toArray(new String[0]);
        } catch (IOException e) {
            e.printStackTrace();
            return new String[0];
        }
    }

    public static ArrayList<Integer> assembleSeatIndices (String[] seats) {
        ArrayList<Integer> seatIndices = new ArrayList<>();
        for (String seat : seats) {
            seatIndices.add(findSeatIndex(seat));
        }
        return seatIndices;
    }

    public static int findSeatIndex(String seat) {
        assert seat.length() == 10;
        int row = 0;
        int col = 0;
        for (int i = 0; i < seat.length() - 2; i++) {
            if (seat.charAt(i) == 'B') {
                row += 128/(Math.pow(2,i+1));
            }
        }
        for (int j = 7; j < seat.length(); j++) {
            if (seat.charAt(j) == 'R') {
                col += 8/(Math.pow(2,j-6));
            }
        }
//        System.out.println(row + " " + col);
        return row * 8 + col;
    }

    public static int findGreatestInteger(ArrayList<Integer> numbers) {
        int greatest = -2147483647;
        for (Integer number : numbers) {
            if (number > greatest) {
                greatest = number;
            }
        }
        return greatest;
    }

    private static ArrayList<Integer> assembleAllSeatNumbers() {
        ArrayList<Integer> seatIndices = new ArrayList<>();
        for (int i = 0; i < 936; i++) {
            seatIndices.add(i);
        }
        return seatIndices;
    }

    public static int findOpenSeat(ArrayList<Integer> takenSeats) {
        ArrayList<Integer> allPossibleSeats = assembleAllSeatNumbers();
        int openSeat = -1;
        for (Integer seat : takenSeats) {
            if (allPossibleSeats.contains(seat)) {
                allPossibleSeats.remove(seat);
            }
        }
        ArrayList<Integer> freeSeats = allPossibleSeats;
        for (Integer seat : freeSeats) {
            if (takenSeats.contains(seat+1) && takenSeats.contains(seat-1)) {
                openSeat = seat;
            }
        }
        return openSeat;
    }

    public static int findOpenSeatFull(String filePath) {
        try {
            return findOpenSeat(assembleSeatIndices(readFile(filePath)));
        }
        catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }
}
