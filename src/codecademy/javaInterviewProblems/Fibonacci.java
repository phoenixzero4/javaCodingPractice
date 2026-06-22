package codecademy.javaInterviewProblems;

import java.util.HashMap;
import java.util.Scanner;

public class Fibonacci{

    static HashMap<Integer, Integer> map = new HashMap<>();

    public static long recursiveFib(int n){

        if( n <= 1 ){
            return n;
        }else{
            return (recursiveFib(n-1) + recursiveFib(n-2));
        }
    }

    public static int memoizedFib(int n, HashMap<Integer, Integer> map){

        if(n <= 1){
            return n;
        }
        if(map.containsKey(n)){
            return map.get(n);
        }else{
            int result = memoizedFib(n - 1, map) + memoizedFib(n - 2, map);
            map.put(n, result);
            return result;
        }
    }



    public static void main(String[] args){
        Scanner scan = new Scanner(System.in);

        System.out.println("\nEnter a number to find the Fibonacci number: ");
        int n = scan.nextInt();
        scan.close();
        System.out.printf("The %dth Fibonacci number is %d", n, recursiveFib(n));
        System.out.printf(" (%d + %d)", recursiveFib(n-1), recursiveFib(n-2));

        System.out.println("\nMemoizedFib returns: " + memoizedFib(n, map));

    }
}
