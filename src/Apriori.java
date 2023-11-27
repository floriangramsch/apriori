package src;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import static src.Main.print_array;

public class Apriori {
    private final List<List<Integer>> data;
    private List<List<Integer>> positive_border;
    private List<List<Integer>> negative_border;
    private List<List<Integer>> frequent_set;
    public Apriori(List<List<Integer>> data) {
        this.data = data;
        this.positive_border = new ArrayList<>();
        this.negative_border = new ArrayList<>();
        this.frequent_set = new ArrayList<>();
    }

    public float support(List<Integer> this_set) {
        int count = 0;
        for (List<Integer> row : this.data) {
            boolean supported = true;
            for (int val : this_set) {
                boolean tmp = row.get(val) == 1;
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
        return (float) count / data.size();
    }

    private List<List<Integer>> generate(List<List<Integer>> candidates) {
        int n = candidates.size();
        int num = candidates.get(0).size();
        List<List<Integer>> result = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            for (int j = i+1; j < n; j++){
                int a = candidates.get(i).get(num-1);
                int b = candidates.get(j).get(num-1);
                List<Integer> base_a = new ArrayList<>(candidates.get(i).subList(0, candidates.get(i).size() - 1));
                List<Integer> base_b = new ArrayList<>(candidates.get(j).subList(0, candidates.get(j).size() - 1));
                ;
                if (base_a.equals(base_b)) {
                    List<Integer> tmpList = new ArrayList<>(base_a);
                    tmpList.add(a);
                    tmpList.add(b);
                    result.add(tmpList);
                }
            }
        }
        return result;
    }

    private List<List<Integer>> prune(List<List<Integer>> to_prune, double k) {
        List<List<Integer>> pruned = new ArrayList<>();
        for (List<Integer> can : to_prune) {
//            for (int x : can) {
//                System.out.print(x);
//            }
            if (this.support(can) >= k) {
                pruned.add(can);
            }
            else {
                this.negative_border.add(can);
            }
        }
        return pruned;
    }

    public List<List<Integer>> maximize(List<List<Integer>> set) {
        List<List<Integer>> maximized = new ArrayList<>(set);
        for (int i=0; i<set.size(); i++) {
            boolean is_drin = false;
            for (int j=i+1; j<set.size(); j++){
                if (new HashSet<>(set.get(j)).containsAll(set.get(i))) {
//                    is_drin = true;
                    maximized.remove(set.get(j));
                }
            }
//            if (!is_drin) {
//                maximized.add(set.get(i));
//            }
        }
        return maximized;
    }

    public void run(double k) {
        this.frequent_set = new ArrayList<>();
        this.negative_border = new ArrayList<>();
        this.positive_border = new ArrayList<>();
        List<List<Integer>> c = new ArrayList<>();
        for (int i=0; i<this.data.get(0).size(); i++) {
            int finalI = i;
            c.add(new ArrayList<>(){{
                add(finalI);
            }});
        }
        c = this.prune(c, k);
        while (!c.isEmpty()) {
            this.frequent_set.addAll(c);
            c = this.generate(c);
            c = this.prune(c, k);
        }
        print_array(this.negative_border);
        this.negative_border = this.maximize(this.negative_border);
        print_array(this.negative_border);
    }

    public List<List<Integer>> getf() {
        return this.frequent_set;
    }
    public List<List<Integer>> getNegative() {
        return this.negative_border;
    }

    public void show(float start, float end, float step) {
        for (float i = start; i <= end; i=i+step) {
            this.run(i);
            System.out.println("k: " + i);
            System.out.println("Data Set: ");
            print_array(this.data);
            System.out.println("Frequent Set: ");
            print_array(this.getf());
            System.out.println("Negative Border: ");
            print_array(this.getNegative());
        }
    }

    public void tree() {
        List<List<List<Integer>>> patterns = new ArrayList<>();
        List<List<Integer>> c = new ArrayList<>();
        for (int i = 0; i < this.data.get(0).size(); i++) {
            int finalI = i;
            c.add(new ArrayList<>() {{
                add(finalI);
            }});
        }
        while (!c.isEmpty()) {
            patterns.add(c);
            c = generate(c);
        }
        for (List<List<Integer>> x : patterns) {
            for (List<Integer> y : x) {
                float rounded_support = Math.round(this.support(new ArrayList<>(y)) * 100.0) / 100.0f;
                if (rounded_support >= 0.4) {
                    System.out.print("( ");
                    for (int z : y) {
                        System.out.print(z);
                    }
                    System.out.print(": " + rounded_support);
                    System.out.print(" )");
                }
            }
            System.out.println();
        }
    }
}
//      {}
//   0  1  2  3
// 01 02 03 12 13 23
//   012 013 123
//      0123

// 23 28 38