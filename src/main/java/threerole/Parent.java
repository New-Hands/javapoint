package threerole;

/**
 * 测试继承 虚拟机是如何确定方法的调用的？？？ 如何从方法列表中选择正确的方法调用
 * @author 李尚庭
 * @date 2019-1-2
 */
public abstract class Parent {
    public abstract void abs();

    public static void sts() {
        System.out.println("static p");
    }

    public void rel() {
        this.say();
    }

    public void say() {
        System.out.printf("hello p");
    }
}
