package com.ljz.bus.state;

import com.ljz.bus.StateObserver;

public interface State {

    StateObserver<String> trigger();

}
