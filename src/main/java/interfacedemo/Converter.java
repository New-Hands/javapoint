package interfacedemo;

/**
 * <P>利用FunctionalInterface 实现对方法的引用
 * 函数式编程？
 * 定义自己的lambda方法
 * 功能性接口中只能有一个方法
 * @author lst
 * @param <V>
 */
@FunctionalInterface
public interface Converter<V> {
    /**
     * <P>功能性接口示例</P>
     * @param from 被转换的参数
     */
    void convert(V from);
}

class FunctionalInterfaceTest {
    public static void main(String[] args) {

        Converter<String> converter = (from -> {
            char [] chars = from.toCharArray();
            System.out.println(chars[1]);
        });
        converter.convert("woshi");
    }
}