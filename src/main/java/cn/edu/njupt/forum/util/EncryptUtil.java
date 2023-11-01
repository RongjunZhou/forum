package cn.edu.njupt.forum.util;

import java.util.Base64;

public class EncryptUtil {
    Base64.Encoder encoder = Base64.getEncoder();
    Base64.Decoder decoder = Base64.getDecoder();

    public String encode(String data) {
        return encoder.encodeToString(data.getBytes());
    }

    public String decode(String data) {
        return new String(decoder.decode(data));
    }
}
