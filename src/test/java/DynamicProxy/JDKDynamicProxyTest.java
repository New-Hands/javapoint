package DynamicProxy;

import org.junit.Test;

import java.lang.reflect.Proxy;

public class JDKDynamicProxyTest {
    @Test
    public void test() {
        Hello hello = (Hello) Proxy.newProxyInstance(Thread.currentThread().getContextClassLoader(), HelloImpl.class.getInterfaces(), new HelloHandler());
        hello.sayHello();
        hello.sayWorld();
    }
}
