package com.ljz.annotation.bean;

public class SubscribeMethod {
    private String methodName;
    private String mSubscribeState;

    public SubscribeMethod() {
    }

    public SubscribeMethod(String methodName, String mSubscribeState) {
        this.methodName = methodName;
        this.mSubscribeState = mSubscribeState;
    }

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    public String getSubscribeState() {
        return mSubscribeState;
    }

    public void setSubscribeState(String mSubscribeState) {
        this.mSubscribeState = mSubscribeState;
    }
}
