package thread;

/**
 * <P>对象构造时 涉及到内部类 造成this指针逸出  导致外部对象还未初始化完成就被使用 而出现问题</P>
 * <P>实际上是因为内部类构造的时候，会把外部类的对象this隐式的作为一个参数传递给内部类的构造方法，这个工作是编译器做的，他会给你内部类所有的构造方法添加这个参数</P>
 * <p>不正确的示例</p>
 *
 * @author lst
 */
public class ThisEscape {

    int a = 0;
    /**
     * 在构造函数中 对象未构造完成之前使用对象this指针
     * @param resource
     */
    public ThisEscape(EventResource resource) {
        //练习使用了lambda表达式 这里是内部类
        resource.register((event) -> {
            //暴露了隐式的引用 导致可以提前的使用到对象的方法 这是错误的做法
            doSomeThing(event);
            /**
             *
             */
            ThisEscape.this.doSomeThing(event);

        });

        a = 4;
    }

    /**
     *
     * @param event
     */
    public void doSomeThing(Event event) {

    }

    /**
     * 模拟事件注册  更好的做法应该是创建一个外部的类
     */
    interface EventResource {
        /**
         * 事件监听注册
         * @param listener 事件监听
         */
        void register(EvntListener listener);
    }

    @FunctionalInterface
    interface EvntListener {

        /**
         * 实践注册
         * @param event 事件
         */
        void onEvent(Event event);
    }

    interface Event {

    }

}
