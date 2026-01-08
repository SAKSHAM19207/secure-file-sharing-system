package util;

import java.io.FileWriter;
import java.time.LocalDateTime;

public class Logger {

    private static final String LOG = "activity.log";

    public static void log(String msg) {
        try {
            FileWriter fw = new FileWriter(LOG, true);
            fw.write(LocalDateTime.now() + " - " + msg + "\n");
            fw.close();
        } catch (Exception ignored) {}
    }
}
