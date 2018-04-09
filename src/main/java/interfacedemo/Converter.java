package interfacedemo;

import java.util.Collections;

/**
 * 利用FunctionalInterface 实现对方法的引用
 * @author lst
 * @param <V>
 */
@FunctionalInterface
public interface Converter<V> {
    String convert(V from);
}

class FunctionalInterfaceTest {
    public static void main(String[] args) {
        Converter<String> converter = (from -> from+"heh");
        //converter.convert("woshi");
        //System.out.println(converted);
        //Collections.sort();
    }
}