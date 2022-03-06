package common;

import java.util.concurrent.TimeUnit;

public class MillisFormatUtils {
    public static String getFormattedMillis(long millis) {
        return String.format("%02d min, %02d sec %02d millis",
                TimeUnit.MILLISECONDS.toMinutes(millis),
                TimeUnit.MILLISECONDS.toSeconds(millis) -
                        TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(millis)),
                TimeUnit.MILLISECONDS.toMillis(millis) -
                        TimeUnit.SECONDS.toMillis(TimeUnit.MILLISECONDS.toSeconds(millis)));
    }
}
