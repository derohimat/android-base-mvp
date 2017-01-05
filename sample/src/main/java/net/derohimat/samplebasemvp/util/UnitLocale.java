package net.derohimat.samplebasemvp.util;

import java.util.Locale;

/**
 * Get the units type: metric / imperial.
 * You could add them in the "settings" of the app for a better usage...
 */
public class UnitLocale {
    public static String Imperial = "imperial";
    public static String Metric = "metric";

    public static String getDefault() {
        return getFrom(Locale.getDefault());
    }

    public static String getFrom(Locale locale) {
        String countryCode = locale.getCountry();
        if ("US".equals(countryCode)) return Imperial; // USA
        if ("LR".equals(countryCode)) return Imperial; // liberia
        if ("MM".equals(countryCode)) return Imperial; // burma
        return Metric;
    }
}