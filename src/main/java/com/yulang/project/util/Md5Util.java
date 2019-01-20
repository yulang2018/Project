package com.yulang.project.util;

import org.apache.commons.codec.digest.DigestUtils;

public class Md5Util {
    /**
     * 一次MD5
     * @param src
     * @return
     */
    public static String md5(String src){
        return DigestUtils.md5Hex(src);
    }
    /**
     * 两次Md5
     */

    private final static String SALT="123";
    public static String inputPassFormPass(String inputPass){
        return md5(inputPass+SALT);
    }

    public static String formPassToDbPass(String inputPass,String salt){
        return inputPassFormPass(inputPass+salt);
    }

    public static void main(String[] args){
        System.out.println(formPassToDbPass("oooooooo","9999999999"));
    }
}
