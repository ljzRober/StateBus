package com.ljz.annotation.bean;

import java.util.HashSet;
import java.util.Set;

public class SubscribeInfo {

    private String mClassPath;
    private Set<SubscribeMethod> mSubscribeMethods = new HashSet<>();

    public SubscribeInfo() {

    }

    public SubscribeInfo(String classPath) {
        setClassPath(classPath);
    }

    public SubscribeInfo(String classPath, Set<SubscribeMethod> subscribeMethods) {
        setClassPath(classPath);
        this.mSubscribeMethods = subscribeMethods;
    }

    public Class<?> getClassType() {
        try {
            return Class.forName(mClassPath);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    public String getClassPath() {
        return mClassPath;
    }

    public void setClassPath(String mClassPath) {
        this.mClassPath = mClassPath;
    }

    public Set<SubscribeMethod> getSubscribeMethods() {
        return mSubscribeMethods;
    }

    public void addSubscribeMethod(SubscribeMethod subscribeMethod) {
        this.mSubscribeMethods.add(subscribeMethod);
    }
}
