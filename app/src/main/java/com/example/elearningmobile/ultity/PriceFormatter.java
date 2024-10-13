package com.example.elearningmobile.ultity;

import java.text.NumberFormat;
import java.util.Locale;

public class PriceFormatter {
    public static String formatPriceInVND(long price) {
        // Create a number formatter for Vietnam locale
        NumberFormat vndFormat = NumberFormat.getCurrencyInstance(new Locale("vi", "VN"));
        return vndFormat.format(price);
    }
}
