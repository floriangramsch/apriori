package src;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Scanner;
import java.io.FileNotFoundException;
import java.util.stream.Stream;

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
//        System.out.print("Total count of (");
//        for (int x : this_set) {
//            System.out.print(x);
//        }
//        System.out.println("): " + count);
        return (float) count / data.length;
    }

    public static int[] concat_int(int[] a, int[] b ) {
        int len_a = a.length;
        int len_b = b.length;
        int[] result = new int[len_a+len_b];
        System.arraycopy(a, 0, result, 0, len_a);
        System.arraycopy(b, 0, result, len_a, len_b);
        return result;
    }
    public static int[][] generate(int[][] candidates) {
        int n = candidates.length;
        int num = candidates[0].length;
        int maybe_length = (n*(n-1)/2);
        int[][] result = new int[maybe_length][];
        int counter = 0;
        for (int i = 0; i < n; i++) {
            for (int j = i+1; j < n; j++){
                int[] a = {candidates[i][num - 1]};
                int[] b = {candidates[j][num - 1]};
                int[] base_a = new int[num-1];
                int[] base_b = new int[num-1];
                System.arraycopy(candidates[i], 0, base_a, 0, num-1);
                System.arraycopy(candidates[j], 0, base_b, 0, num-1);
                if (Arrays.equals(base_a, base_b)) {
                    result[counter] = concat_int(concat_int(base_a, a), b);
                    counter++;
                }
            }
        }
        result = Arrays.copyOf(result, counter);
        return result;
    }

    public static int[][] prune(int[][] data, int[][] to_prune, double k) {
        int[][] pruned = new int[to_prune.length][];
        int counter = 0;
        for (int[] can : to_prune) {
//            for (int x : can) {
//                System.out.print(x);
//            }
            if (support(data, can) >= k) {
                pruned[counter] = can;
                counter++;
            }
        }
        pruned = Arrays.copyOf(pruned, counter);
        return pruned;
    }


//    public static int[][] apriori(int[][] data) {
//        int[][] result = new int[10][];
//
//
//        return result;
//    }

    public static void main(String[] args) {
        int[][] data1 = read_file("src/dm1.csv");
        int[][] data2 = read_file("src/dm2.csv");
        int[][] test = {
                {0, 0, 1},
                {1, 1, 0},
                {1, 0, 0}
        };
//        float support = support(data1, new int[] {0, 2, 3});
//        System.out.println("Support: " + support);
        int[][] cands = new int[][]{{0}, {1}, {2}};
        cands = generate(cands);
        int[][] pruning = prune(test, cands, 0.3);
//        int[][] to_gen = new int[][]{{0}, {1}, {2}, {3}, {4}, {5}, {6}, {7}, {8}, {9}};
//        int[][] cands = generate(to_gen);
//        cands = generate(cands);
        print_array(pruning);
    }
}