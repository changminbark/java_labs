/* PLACE YOUR BANNER HERE! */
package lab03;

import java.util.Arrays;

/**
 * Array Challenge
 * 
 * Complete each one of the static methods below. Do NOT touch the main program!
 * 
 * @author brk009
 */
public class ArrayChallenge {

    /**
     * Given array a, return a new array that contains from index position 
     * iStart up to one before iEnd.
     * 
     * @param a - the array whose range is to be copied
     * @param iStart - the starting index to copy
     * @param iEnd - one after the last index to copy.
     * @return - a new array with the subrange specified
     */
    public static int[] subArray(int[] a, int iStart, int iEnd) {
        // Prepare a new array
        int [] result = new int[iEnd-iStart];

        // Copy over the sub range of a to the new array
        for (int i = iStart; i < iEnd; i++) {
            result[i-iStart] = a[i];

            // Prevent going beyond the end of the array
            if (iEnd >= a.length) break;
        }
        return result;
    }

    /**
     * Given two arrays a1 and a2, return a new array that has a2 appended to the
     * end of a1
     * 
     * @param a1 - the first array of int
     * @param a2 - the second array of int
     * @return a new array consisting of the elements of both a1 and a2
     */
    public static int[] concat(int[] a1, int[] a2) {
        int totalLength = a1.length + a2.length;
        int[] result = new int[totalLength];
        for (int i = 0; i < a1.length; i++){
            result[i] = a1[i];
        }
        for (int i = 0; i < a2.length; i++){
            result[i + a1.length] = a2[i];
        }
        return result;
    }
    
    /**
     * Recursively compute the reverse of an array
     * 
     * @param a the array to compute the reverse of
     * @return the reverse of a
     */
    public static int[] recReverse(int[] a) {
        // Base case
        if (a.length == 2) {
            int temp = a[0];
            a[0] = a[1];
            a[1] = temp;
        }
        else if (a.length == 1) {
            ;
        // Recursive case
        } else {
            int temp = a[0];
            a[0] = a[a.length - 1];
            a[a.length - 1] = temp;
            if (a.length > 2){
                int[] b = subArray(a, 1, a.length - 1);
                recReverse(b); 
                for (int i = 0; i < b.length; i++){
                    a[i+1] = b[i];
                }
            }
        }
        return a;
    }
    
    /**
     * Compute the alternating sum of all elements in a, where an alternating sum 
     * alternates between subtraction and addition every other element. 
     * 
     * For example, the array [2, 3, 5, 7, 11] would be computed as:
     * 2 - 3 + 5 - 7 + 11 = 8
     * 
     * @param a an array of int
     * @return the alternating sum of a
     */
    public static int altSum(int[] a) {
        // TODO - FINISH ME!
        return Integer.MIN_VALUE;
    }
    
    /**
     * Given an array of int, return true if a is sorted in increasing order
     * false otherwise
     * 
     * @param a an array of int
     * @return true if a is sorted, false otherwise
     */
    public static boolean isSorted(int[] a) {
        // TODO - FINISH ME!
        return false;
    }

    /**
     * Given an array a, return true if a has duplicate values, false othersie
     * @param a an array of int
     * @return true if the array has duplicate values, false if every value is unique
     */
    public static boolean hasDuplicateValues(int[] a) {
        // TODO - FINISH ME!
        return false;
    }    

    /**
     * *************************************************************************
     * MAIN PROGRAM
     * 
     * DO NOT TOUCH THIS PROGRAM! 
     * 
     * The main program will test every one of your methods
     * you write and report your results. Focus only on the methods.
     * 
     * @param args
     * **************************************************************************
     */
    public static void main(String[] args) {
        int[] array1 = { 2, 3, 5, 7, 11 };
        int[] array2 = { 1, 4, 6, 8, 0, 1 };
        int[] array3 = { 2, 3, 5, 7, 11, 1, 4, 6, 8, 0, 1 };
        int[] array4 = { 1, 0, 8, 6, 4, 1 };
        int totalPass = 0;
        int totalTests = 0;
        
        System.out.println("Checking subArray... ");
        totalTests++;
        if (Arrays.equals(subArray(array1, 1, 4),Arrays.copyOfRange(array1, 1, 4))) {
            System.out.println("PASSED");
            totalPass++;
        }
        else
            System.out.println("FAIL!");

        System.out.println("Checking concat... ");
        int[] result = concat(array1,array2);
        System.out.printf("EXPECTED: %s%n",Arrays.toString(array3));
        System.out.printf("OBSERVED: %s%n", Arrays.toString(result));
        totalTests++;
        if (Arrays.equals(concat(array1, array2), array3)) {
            System.out.println("PASSED");
            totalPass++;
        }
        else
            System.out.println("FAIL!");


        System.out.println("Checking recReverse...");
        result = recReverse(array2);
        System.out.printf("EXPECTED: %s%n",Arrays.toString(array4));
        System.out.printf("OBSERVED: %s%n",Arrays.toString(result));
        totalTests++;
        if (Arrays.equals(recReverse(array2), array4)) {
            System.out.println("PASSED");
            totalPass++;
        }
        else
            System.out.println("FAIL!");

        System.out.printf("Checking altSum(%s)...%n",Arrays.toString(array2));
        System.out.println("EXPECTED: -6");
        System.out.printf("OBSERVED: %d%n",altSum(array2));
        System.out.printf("Checking altSum(%s)...%n",Arrays.toString(array3));
        System.out.println("EXPECTED: 14");
        System.out.printf("OBSERVED: %d%n",altSum(array3));
        totalTests++;
        if (altSum(array2) == -6 && altSum(array3) == 14) {
            System.out.println("PASSED");
            totalPass++;
        }
        else
            System.out.println("FAIL!");

        int[] aSorted = Arrays.copyOf(array3,array3.length);
        System.out.println("Checking isSorted...");
        Arrays.sort(aSorted);
        totalTests++;
        if (!isSorted(array3) && isSorted(aSorted)) {
            System.out.println("PASSED");
            totalPass++;
        }
        else
            System.out.println("FAIL!");

        System.out.printf("Checking hasDuplicateValues(%s)...%n",Arrays.toString(array1));
        System.out.println("EXPECTED: false");
        System.out.printf("OBSERVED: %b%n",hasDuplicateValues(array1));
        System.out.printf("Checking hasDuplicateValues(%s)...%n",Arrays.toString(array2));
        System.out.println("EXPECTED: true");
        System.out.printf("OBSERVED: %b%n",hasDuplicateValues(array2));
        totalTests++;
        if (!hasDuplicateValues(array1) && hasDuplicateValues(array2)) {
            System.out.println("PASSED");
            totalPass++;
        }
        else
            System.out.println("FAIL!");

        System.out.printf("*** TOTAL PASSED: %d out of %d ***%n",totalPass,totalTests);
    }
    
}
