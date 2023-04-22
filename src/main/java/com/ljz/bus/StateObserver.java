package com.ljz.bus;

public interface StateObserver<T> {

    void post(T data);

}
