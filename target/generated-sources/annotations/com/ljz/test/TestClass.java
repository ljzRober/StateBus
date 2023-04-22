package com.ljz.test;

import com.ljz.annotation.AbstractSubscribers;
import com.ljz.annotation.bean.SubscribeInfo;
import com.ljz.annotation.bean.SubscribeMethod;

public final class TestClass extends AbstractSubscribers {
  static {
    putIndex(new SubscribeInfo("com.ljz.bus.Main"));
    putMethod("com.ljz.bus.Main", new SubscribeMethod("StateListener", "com.ljz.bus.state.State$trigger"));
  }

  public static void importPackages(SubscribeInfo SubscribeInfo, SubscribeMethod SubscribeMethod) {
  }
}
