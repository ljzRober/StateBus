package com.ljz.bus;

import com.ljz.annotation.bean.SubscribeInfo;
import com.ljz.annotation.bean.SubscribeMethod;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class SimpleObserver<T> implements StateObserver<T> {

    private final List<SubscribeInfo> methodList = new ArrayList<>();

    public SimpleObserver(List<SubscribeInfo> methods) {
        methodList.addAll(methods);
    }

    @Override
    public void post(T data) {
        methodList.forEach(subscribeInfo -> {
            Set<SubscribeMethod> subscribeMethods = subscribeInfo.getSubscribeMethods();
            String classPath = subscribeInfo.getClassPath();
            try {
                Class<?> forName = Class.forName(classPath);
                Object instance = forName.newInstance();
                for (SubscribeMethod subscribeMethod : subscribeMethods) {
                    String methodName = subscribeMethod.getMethodName();
                    Method method = forName.getDeclaredMethod(methodName, data.getClass());
                    method.invoke(instance, data);
                }
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InstantiationException e) {
                e.printStackTrace();
            }
        });
    }
}
