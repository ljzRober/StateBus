package com.ljz.bus.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Objects;

public class JDKProxy {

    private static JDKProxy jdkProxy;
    public static JDKProxy getInstance() {
        if (jdkProxy == null) {
            jdkProxy = new JDKProxy();
        }
        return jdkProxy;
    }

    public void doProxy() {
        InvokeHandler invokeHandler = new InvokeHandler();
        StateListener proxyInstance = (StateListener) Proxy.newProxyInstance(ClassLoader.getSystemClassLoader(),
                new Class[]{StateListener.class},
                invokeHandler);
        String change = proxyInstance.onStateChange();
        System.out.println(change);
    }

}

class InvokeHandler implements InvocationHandler {

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        String methodName = method.getName();
        if (Objects.equals(methodName, "onStateChange")) {
            return "State changed";
        }
        return method.invoke(proxy, args);
    }
}
