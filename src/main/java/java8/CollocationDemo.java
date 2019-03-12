package java8;


import java.util.Arrays;
import java.util.Properties;

/**
 * <p>
 * 集合处理新方式
 * </p>
 *
 * @author 李尚庭
 * @date 2019-3-11
 */
public class CollocationDemo {
    public static void main(String[] args) {
        /**
         * Stream 不是具体的数据结构 而是数据的视图
         * 对Stream的处理不会影响Stream的支持数据
         * 惰式执行
         * 只能被消费一次
         */
        CollocationDemo demo = new CollocationDemo();
        demo.StreamTest();
    }

    public void StreamTest() {

        Properties properties = System.getProperties();
        properties.stringPropertyNames()
                .stream().forEach(name -> System.out.println(name + ":" + System.getProperty(name)));

        Arrays.stream(System.getProperty("java.class.path").split(";"))
                .filter(str -> str.startsWith("F"))
                .forEach(str -> System.out.println(str));
        /**
         *  lambda 一行代码时不需要花括号
         */
        Arrays.stream(System.getProperty("java.library.path").split(";"))
                .filter(str -> str.startsWith("F"))
                .forEach(str -> System.out.println(str));
    }
}
