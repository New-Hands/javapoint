package DynamicProxy;

public class HelloImpl implements Hello{
    @Override
    public void sayHello() {
        System.out.println("hello");
    }

    @Override
    public void sayWorld() {
        System.out.println("world");
    }
}
