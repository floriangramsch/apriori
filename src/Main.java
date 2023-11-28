package src;

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.Random;

public class Main{
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

    public static List<List<Integer>> intersect(List<List<Integer>> sub1, List<List<Integer>> sub2) {
        List<List<Integer>> result = new ArrayList<>();
        for (List<Integer> outerList1 : sub1) {
            if (sub2.contains(outerList1) && !result.contains(outerList1)) {
                result.add(outerList1);
            }
        }
        return result;
    }

    public static List<List<Integer>> constraint(List<List<Integer>> data1, List<List<Integer>> data2, float k1, boolean k1_greater, float k2, boolean k2_greater) {
        Apriori that = new Apriori(data1);
        Apriori other = new Apriori(data2);
        that.run(k1);
        other.run(k2);
        List<List<Integer>> sub1 = k1_greater ? that.getFrequent_set() : that.getInfrequent_set();
        List<List<Integer>> sub2 = k2_greater ? other.getFrequent_set() : other.getInfrequent_set();
        return intersect(sub1, sub2);
    }

    public static void main(String[] args) {
        List<List<Integer>> data1 = read_file("src/dm1.csv");
        List<List<Integer>> data2 = read_file("src/dm2.csv");
        List<List<Integer>> test = read_file("src/test.csv");
        List<List<Integer>> vier = read_file("src/drei.csv");

        int n = 7;
        List<List<Integer>> random = new ArrayList<>(n);
        Random random_generator = new Random();
        for (int row = 0 ; row < 10; row++) {
            List<Integer> tmp_row = new ArrayList<>();
            for (int i=0; i<5; i++) {
                tmp_row.add(random_generator.nextInt(2));
            }
            random.add(tmp_row);
        }

        Apriori apriored = new Apriori(data1);
        double k = 0.1;
        apriored.run(k);
//        apriored.draw(k);
//        apriored.show(k, k, 0.1);

        List<List<Integer>> con = constraint(data1, data2, 0.3f, true, 0.5f, false);
        System.out.println(con);
    }
}

