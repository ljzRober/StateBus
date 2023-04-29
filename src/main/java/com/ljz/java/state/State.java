package com.ljz.java.state;

import com.ljz.bus.StateObserver;

public interface State {

    StateObserver<String> trigger();

}
