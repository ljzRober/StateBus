package com.ljz.bus;

import com.ljz.annotation.AbstractSubscribers;
import com.ljz.annotation.StateConstants;
import com.ljz.annotation.bean.SubscribeInfo;

import javax.annotation.Nonnull;
import java.lang.reflect.*;
import java.util.*;

public class StateBus {

    private AbstractSubscribers subscribers;
    private final Map<String, SubscribeInfo> subscribeInfoByClass = new HashMap<>();
    private final Map<String, List<SubscribeInfo>> methodsByState = new HashMap<>();

    public StateBus() {
        try {
            Class<?> clazz = Class.forName(StateConstants.SubscribesClass);
            Constructor<?> constructor = clazz.getConstructor();
            subscribers = (AbstractSubscribers) constructor.newInstance();
        } catch (ClassNotFoundException | InstantiationException |
                NoSuchMethodException | InvocationTargetException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    public static StateBus getDefault() {
        return LazyHolder.INSTANCE;
    }

    public void register(@Nonnull Class<?> subscriber) {
        SubscribeInfo subscribeInfo = subscribers.getSubscribeInfo(subscriber.getCanonicalName());
        subscribeInfoByClass.put(subscribeInfo.getClassPath(), subscribeInfo);
        subscribeInfo.getSubscribeMethods().forEach(subscribeMethod -> {
            String state = subscribeMethod.getSubscribeState();
            if (methodsByState.containsKey(state)) {
                methodsByState.get(state).add(subscribeInfo);
            } else {
                methodsByState.put(state, new ArrayList<>(Collections.singleton(subscribeInfo)));
            }
        });
    }

    public void unRegister(@Nonnull Class<?> subscribe) {
        SubscribeInfo subscribeInfo = subscribeInfoByClass.remove(subscribe.getCanonicalName());
        if (subscribeInfo == null) {
            return;
        }
        methodsByState.forEach((s, subscribeInfos) -> {
            subscribeInfos.removeIf(info -> Objects.equals(info.getClassPath(), subscribe.getCanonicalName()));
        });
    }

    public <T> StateObserver<T> getChannel(String state, Class<T> type) {
        List<SubscribeInfo> subscribeInfos = methodsByState.get(state);
        return new SimpleObserver<>(subscribeInfos);
    }

    public StateObserver<Object> getChannel(String target) {
        return getChannel(target, Object.class);
    }

    @SuppressWarnings("unchecked")
    public <C> C by(Class<C> cClass) {
        return (C) Proxy.newProxyInstance(ClassLoader.getSystemClassLoader(),
                new Class[]{cClass},
                new InvocationHandler() {
                    @Override
                    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                        String state = cClass.getCanonicalName() + "$" +method.getName();
                        return getChannel(state);
                    }
                });
    }

    /**
     * 私有静态内部类
     */
    private static class LazyHolder {
        private static final StateBus INSTANCE = new StateBus();
    }

}
