package practice.collections.sets;

import java.util.Iterator;

public class MyHashSet {

    public static void main(String[] args) {
        /* HashSet - as a set, does not allow duplicate elements
        * It uses a hash table internally and implements Serializable and Cloneable interfaces.
        * It is not synchronized (thread safe) and not guaranteed to be ordered.
        *
        *
        * Collisions are handled using chaining (open addressing / LinkedList) and converted to a
        * red/black tree (balanced binary search tree) when the number of elements exceeds the threshold.
        *
        * Load factor = number of elements / capacity (default 0.75)*/

        java.util.HashSet<Integer> intSet = new java.util.HashSet<>();
        intSet.add(1);
        intSet.add(5);
        intSet.add(3);
        intSet.add(8);

        // HashSet printed
        System.out.println(intSet);

        // Iterator
        Iterator<Integer> iterator = intSet.iterator();
        while(iterator.hasNext()) {
            System.out.print(iterator.next() + " ");
        }

        // Enhanced for loop
        System.out.println();
        for(int i : intSet){
            System.out.print(i + " ");
        }

        intSet.clear();
        System.out.println(intSet);

        if(intSet.isEmpty()) System.out.println("Set is empty");

        intSet.add(1);

        System.out.println(intSet);
        if(intSet.contains(1)) System.out.println("Set contains 1");


        System.out.println(intSet);

        System.out.println(intSet.size());


    }
}
