package cn.edu.njupt.forum.util;

import java.util.Base64;

public class EncryptUtil {
    private static final Base64.Encoder encoder = Base64.getEncoder();
    private static final Base64.Decoder decoder = Base64.getDecoder();

    public static String encode(String data) {
        return encoder.encodeToString(data.getBytes());
    }

    public static String decode(String data) {
        return new String(decoder.decode(data));
    }
}
