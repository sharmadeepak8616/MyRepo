package util;

public class MathUtils {

    public static boolean checkIfWithInRange (int lower , int higher, int value) {
        boolean result;
        if(lower <= value && value <= higher) {
            result = true;
        } else {
            result = false;
        }
        return result;
    }

    public static boolean checkIfLesser(int amount, int from) {
        boolean result;
        if(amount < from) {
            result = true;
        } else {
            result = false;
        }
        return result;
    }




}
