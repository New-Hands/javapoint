package alg;

/**
 * 枚举类和单例模式
 * 1.枚举规定了构造器的私有（或者默认）
 * 2.枚举类的意义
 *
 * @author lst
 * @create_time 2018/01/30
 */
public enum EnumDemo {
    /**
     * 作为常量提供的方式
     */
    ENUM, ClASS, INTERFACE, ANNOTATION
}

enum Color {
    RED(1, "fsdf"), GREEN(2, "green");
    /**
     * 属性可以是public的
     */
    public String name;
    public int index;

    /**
     * 枚举类的构造器只能是私有的 或者默认的
     *
     * @param index 实例的序号
     * @param name  实例的字符串值
     */
    private Color(int index, String name) {
        this.index = index;
        this.name = name;
    }
}

/**
 * 测试类
 * 测试enum功能
 */
class Test {
    public static void main(String[] args) {
        System.out.println("i can run ");
        /**
         * 配合使用 switch 1.6之前switch智能匹配 int char enum
         */
        EnumDemo type = EnumDemo.ANNOTATION;
        switch (type) {
            case ENUM:
                System.out.println("i am an annotation");

        }
    }
}
