# StateBus
a study message passing reference eventbus and modular-event

## 使用方式
1. 定义状态事件
>状态的返回值为StateObserver
```java
public interface State {
    StateObserver<String> trigger();
}
```
2. 注册和注销订阅
```java
StateBus.getDefault().register(Main.class);
StateBus.getDefault().unregister(Main.class);
```
3. 订阅事件
```java
@StateSubscribe(classPath = State.class, methodName = "trigger")
public void StateListener(String state) {
    System.out.println(state);
}
```
4. 触发状态事件
```java
StateBus.getDefault().by(State.class)
                .trigger()
                .post("state trigger");
```
## 后记
这个项目是作为一个学习项目使用，主要是学习使用apt技术已经消息传递的设计思想。如果有人看到的话可以提出宝贵的意见。
## 参考文章
* eventbus：https://github.com/greenrobot/EventBus
* modular-event：https://tech.meituan.com/2018/12/20/modular-event.html