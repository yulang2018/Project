package com.yulang.project.util;

import org.apache.commons.lang3.RandomUtils;

import java.util.Random;
import java.util.UUID;

public class UUIDUtils {
    private UUIDUtils(){};
    public static String uuid(){
        return UUID.randomUUID().toString().replace("-","");
    }

    public static long id(){
        Random random=new Random(100000);
        return random.nextInt();
    }

}
