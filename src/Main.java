import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;
import java.io.FileNotFoundException;

public class Main {
    public static int getlines(String fileName) {
        Path path = Paths.get(fileName);
        int lines = 0;
        try {
            lines = (int) Files.lines(path).count();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return lines;
    }
    public static int[][] read_file(String path) {
        int[][] dm;
        try {
            File file = new File(path);
            Scanner scanner = new Scanner(file);
            int length = getlines(path);
            dm = new int[length][];

            int counter = 0;
            while (scanner.hasNextLine()) {
                String[] teile = scanner.nextLine().split(",");
                int[] tmp = new int[teile.length];
                for (int i = 0; i < teile.length; i++) {
                    tmp[i] = Integer.parseInt(teile[i]);
                }
                dm[counter] = tmp;
                counter++;
            }
            scanner.close();
            return dm;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return new int[][]{{-1}};
        }
    }

    public static void print_array(int[][] data) {
        System.out.println("[");
        for (int[] x : data) {
            System.out.print(" [ ");
            for (int y : x) {
                System.out.print(y + " ");
            }
            System.out.println("]");
        }
        System.out.println("]");
    }
    public static float support(int[][] data, int[] this_set) {
        int count = 0;
        for (int[] row : data) {
            boolean supported = true;
            for (int val : this_set) {
                boolean tmp = row[val] == 1;
                supported = supported && tmp;
            }
            if (supported) {
                count++;
            }
        }
        System.out.println("Total count: " + count);
        return (float) count / data.length;
    }

    public static int[][] generate(int[][] candidates) {
        int[][] result = new int[3][];
        int length = candidates.length;
        for (int i = 0; i < length; i++) {
            for (int j = i+1; j<length; j++){
                System.out.println(i + j);
            }
        }
        return result;
    }

    // (0, 1, 2, 3)
    // (01, 02, 03, 12, 13, 23)
    // (012, 013, 023, 123)
    // (0123)

    public static void main(String[] args) {
        int[][] data1 = read_file("src/dm1.csv");
        int[][] data2 = read_file("src/dm2.csv");
        int[][] test = {
                {0, 0, 1},
                {1, 1, 0},
                {1, 0, 0}
        };
//        for (int[] x : test) {
//            for (int y : x) {
//                System.out.print(y);
//            }
//        }
        float support = support(data1, new int[] {0, 2, 3});
//        System.out.println("Support: " + support);
        int[][] to_gen = new int[][]{{0, 69}, {1}, {2}, {3}};
        int[][] cands = generate(to_gen);
//        print_array(to_gen);
    }
}