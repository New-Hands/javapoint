package lambda;

/**
 * <P>使用lambda表达式代替匿名内部类的原理</P>
 * <P>普通接口和功能性接口</P>
 * <P>对方法的引用</P>
 * <P>当一个接口里只有一个方法的时候 就可以使用lambda的方式代替匿名内部类使用</P>
 * <P>一些</P>
 * <P></P>
 * @author lst
 */
public class LambdaDemo {
    public static void main(String[] args) {
        LambdaClass.sayHI(() ->
            System.out.println("hi")
        );
    }
}

/**
 * 接收lambda参数
 */
class LambdaClass {
    static void sayHI(LambdaInterface hi) {
        hi.say();
    }
}

/**
 * lambda接口
 */
interface LambdaInterface {
    /**
     * 功能性接口方法
     */
    void say();
}
