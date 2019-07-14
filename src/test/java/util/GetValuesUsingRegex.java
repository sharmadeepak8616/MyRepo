package util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class GetValuesUsingRegex {

    private static String patternToGetHour = "\\s*(\\d+).*";
    private static String patternToGetCurrentTemp = "\\s*(.*)˚.*";
    private static String patternToGetLowHighTemp = ".*:\\s*(.*)˚.*";
    private static String patternToGetLocation = "\\s*for (.*)";
    private static String patternToGetDistance = "(.*) miles to";

    public static String getHourUsingRegex(String line) {
        Pattern pattern = Pattern.compile(patternToGetHour);
        Matcher match = pattern.matcher(line);
        String hour = null;
        if(match.find()) {
            hour = match.group(1);
        }
        return hour;
    }


    public static int getCurrentTempValueUsingRegEx(String tempLine) {
        int tempValue = 99999;
        // Create a Pattern object
        Pattern r = Pattern.compile(patternToGetCurrentTemp);
        // Now create matcher object.
        Matcher m = r.matcher(tempLine);
        if (m.find( )) {
            tempValue = Integer.parseInt(m.group(1));
        }
        return tempValue;
    }

    public static int getLowHighTempValueUsingRegEx(String tempLine) {
        int tempValue = 99999;
        // Create a Pattern object
        Pattern r = Pattern.compile(patternToGetLowHighTemp);
        // Now create matcher object.
        Matcher m = r.matcher(tempLine);
        if (m.find( )) {
            tempValue = Integer.parseInt(m.group(1));
        }
        return tempValue;
    }

    public static String getLocation(String forLocation) {
        String location = "";
        // Create a Pattern object
        Pattern r = Pattern.compile(patternToGetLocation);
        // Now create matcher object.
        Matcher m = r.matcher(forLocation);
        if (m.find( )) {
            location = m.group(1);
        }
        return location;
    }

    public static double getDistance(String distanceInfo) {
        String distance = "";
        // Create a Pattern object
        Pattern r = Pattern.compile(patternToGetDistance);
        // Now create matcher object.
        Matcher m = r.matcher(distanceInfo);
        if (m.find( )) {
            distance = m.group(1);
        }
        return Double.valueOf(distance);
    }

}
