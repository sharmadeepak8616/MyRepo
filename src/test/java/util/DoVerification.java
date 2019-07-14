package util;

import org.testng.Assert;
import org.testng.asserts.SoftAssert;

import java.util.ArrayList;

public class DoVerification {

    public static SoftAssert assertion = new SoftAssert();

    public static void isTwoStringsEquals(String expectedMsg, String actualMsg) {
        boolean result = expectedMsg.equals(actualMsg);
        //assertion.assertTrue(result, "Message is not as expected - " + actualMsg);
        Assert.assertTrue(result, "Given values are not equal - Actual :" + actualMsg + " - Expected :" + expectedMsg);
        //return result;
    }

    public static void isTwoStringsEqualsIgnorecase(String expectedMsg, String actualMsg) {
        boolean result = expectedMsg.equalsIgnoreCase(actualMsg);
        //assertion.assertTrue(result, "Message is not as expected - " + actualMsg);
        Assert.assertTrue(result, "Given values are not equal(with ignore case) - Actual :" + actualMsg + " - Expected :" + expectedMsg);
        //return result;
    }

    public static void isTwoArrayListEquals(ArrayList expectedArrayList, ArrayList actualArrayList) {
        boolean result = true;
        int expectedArrayLength = expectedArrayList.size();
        int actualArrayLength = actualArrayList.size();
        if (expectedArrayLength == actualArrayLength) {
            for (int i = 0 ; i < actualArrayList.size() ; i++) {
                if (!actualArrayList.get(i).equals(expectedArrayList.get(i))) {
                    result = false;
                    System.out.println("Found : " + actualArrayList.get(i));
                    System.out.println("Expected : " + expectedArrayList.get(i));
                }
            }
        } else {
            Assert.fail("Length of given arrayList didn't match");
        }
        Assert.assertTrue(result, "At least one of the values is mismatch in timeline.");
    }

    public static void isStringStartsWith(String fullString, String startsWith) {
        Assert.assertTrue(fullString.startsWith(startsWith));
    }

    public static void isvalueLessOrEqual(double valueInConsideration, double compareFrom) {
        Assert.assertTrue(valueInConsideration <= compareFrom);
    }


}
