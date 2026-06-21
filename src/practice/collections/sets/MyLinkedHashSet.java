package practice.collections.sets;

import java.util.Set;

public class MyLinkedHashSet {
    private static final Integer[] SAMPLE_VALUES = {100, 200, 3};

    public static void main(String[] args) {
        Set<Integer> linkedHashSet = new java.util.LinkedHashSet<>();

        for (Integer value : SAMPLE_VALUES) {
            linkedHashSet.add(value);
        }

        System.out.println(linkedHashSet);
    }
}
