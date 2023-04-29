package com.ljz.java;

import com.ljz.annotation.StateSubscribe;
import com.ljz.bus.StateBus;
import com.ljz.java.state.State;
import com.sun.istack.internal.NotNull;

public class Main {

    private static final String TAG = "com.ljz.bus.Main";
    private TestModel testModel;

    public Main() {
        testModel = new TestModel();
    }

    public static void main(String[] args) {
//        com.ljz.bus.Main main = new com.ljz.bus.Main();
//        System.out.println("main,com.ljz.bus.TestModel: " + main.testModel);
//        com.ljz.bus.TestModel testModel1 = main.testModel;
//        System.out.println("main,copy,com.ljz.bus.TestModel: " + testModel1);
//        main.giveValue(main.testModel);

//        JDKProxy.getInstance().doProxy();

        StateBus.getDefault().register(Main.class);
        StateBus.getDefault().by(State.class)
                .trigger()
                .post("state trigger");

    }

    public void giveValue(@NotNull TestModel testModel) {
        int a = 1 + 2;
        System.out.println("giveValue,com.ljz.bus.TestModel: " + testModel);
    }


    @StateSubscribe(classPath = State.class, methodName = "trigger")
    public void StateListener(String state) {
        System.out.println(state);
    }

}
