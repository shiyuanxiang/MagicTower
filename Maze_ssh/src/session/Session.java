package session;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class Session {
    public static Map<Object, Object> session = new HashMap<>();
    public static boolean[] kept = {false, false, false};
    public static int screen_width;
    public static int screen_height;
}
