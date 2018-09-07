package DynamicProxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class HelloHandler implements InvocationHandler {

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

        if (method.getName().equals("sayHello")) {
            return  method.invoke(new HelloImpl(),args);
        }

        //？传入的代理对象有何作用
        System.out.println("one");
        //选择目标对象

        /**
         * 也可以不去使用对象的方法
         * 我的目的就是动态的生成一个对象
         */
       // Object invoke = method.invoke(new HelloImpl(),args);
        System.out.println("two");
        return null;
    }
}
