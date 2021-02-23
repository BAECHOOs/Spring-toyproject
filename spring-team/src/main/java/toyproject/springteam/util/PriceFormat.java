package toyproject.springteam.util;

import java.text.DecimalFormat;

public class PriceFormat {
    private static DecimalFormat formatter = new DecimalFormat("###,###,###");

    public static String format(Long price){
        return formatter.format(price);
    }
}
