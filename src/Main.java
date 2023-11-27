package src;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.io.FileNotFoundException;
import java.util.stream.Stream;
import java.util.List;

public class Main {
    public static List<List<Integer>> read_file(String path) {
        List<List<Integer>> data = new ArrayList<>();
        try {
            File file = new File(path);
            Scanner scanner = new Scanner(file);

            while (scanner.hasNextLine()) {
                String[] teile = scanner.nextLine().split(",");
                List<Integer> tmp = new ArrayList<>();
                for (String s : teile) {
                    tmp.add(Integer.parseInt(s));
                }
                data.add(tmp);
            }
            scanner.close();
            return data;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return new ArrayList<>() {{add(new ArrayList<>() {{ add(-1);}});}};
        }
    }

    public static void print_array(List<List<Integer>> data) {
        System.out.println("[");
        for (List<Integer> x : data) {
            System.out.print(" [ ");
            for (int y : x) {
                System.out.print(y + " ");
            }
            System.out.println("]");
        }
        System.out.println("]");
    }


    public static int[] concat_int(int[] a, int[] b ) {
        int len_a = a.length;
        int len_b = b.length;
        int[] result = new int[len_a+len_b];
        System.arraycopy(a, 0, result, 0, len_a);
        System.arraycopy(b, 0, result, len_a, len_b);
        return result;
    }


    public static void main(String[] args) {
        List<List<Integer>> data1 = read_file("src/dm1.csv");
        List<List<Integer>> data2 = read_file("src/dm2.csv");
        List<List<Integer>> test = new ArrayList<>() {{
            add(new ArrayList<>() {{ add(1); add(1); add(0); add(0);}});
            add(new ArrayList<>() {{ add(1); add(1); add(0); add(1);}});
            add(new ArrayList<>() {{ add(1); add(0); add(0); add(0);}});
        }};

        Apriori apriored = new Apriori(test);
        apriored.show(0.4f, 0.4f, 0.1f);
        apriored.tree();
    }
}