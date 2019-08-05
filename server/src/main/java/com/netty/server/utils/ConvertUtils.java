package com.netty.server.utils;

public class ConvertUtils {
    public static String bytes2HexString(byte[] bArray) {
        StringBuffer sb = new StringBuffer(bArray.length);
        String sTemp;
        for (int i = 0; i < bArray.length; i++) {
            sTemp = Integer.toHexString(0xFF & bArray[i]);
            if (sTemp.length() < 2)
                sb.append(0);
            sb.append(sTemp.toUpperCase());
        }
        return sb.toString();
    }

    public static byte[] hexString2Bytes(String src) {
        int l = src.length() / 2;
        byte[] ret = new byte[l];
        for (int i = 0; i < l; i++) {
            ret[i] = (byte) Integer.valueOf(src.substring(i * 2, i * 2 + 2), 16).byteValue();
        }
        return ret;
    }

    public static String hex2String(String hex) {
        StringBuffer result = new StringBuffer();
        for (int i = 0; i < hex.length() - 1; i += 2) {
            int decimal = Integer.parseInt(hex.substring(i, (i + 2)), 16);
            result.append((char) decimal);
        }
        return result.toString();
    }
}