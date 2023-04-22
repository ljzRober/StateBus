package com.ljz.annotation;

import com.ljz.annotation.bean.SubscribeInfo;
import com.ljz.annotation.bean.SubscribeMethod;

import java.util.HashMap;
import java.util.Map;

public class AbstractSubscribers {

    private static Map<String, SubscribeInfo> subscribeInfoMap = new HashMap<>();

    public static void putIndex(SubscribeInfo subscribeInfo) {
        subscribeInfoMap.put(subscribeInfo.getClassPath(), subscribeInfo);
    }

    public static void putMethod(String classPath, SubscribeMethod subscribeMethod) {
        subscribeInfoMap.get(classPath).addSubscribeMethod(subscribeMethod);
    }

    public SubscribeInfo getSubscribeInfo(String clazz) {
        return subscribeInfoMap.get(clazz);
    }

}
