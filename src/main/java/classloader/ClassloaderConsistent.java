package classloader;

/**
 * <p>测试两个类的类加载器是否相等</p>
 *
 * @author 李尚庭
 * @date
 */
public class ClassloaderConsistent {
    public static void main(String[] args) {
        if (A.class.getClassLoader() == B.class.getClassLoader()) {
            A.class.getResourceAsStream("");
            System.out.println(System.getProperty("lst"));
            System.out.println(A.class.getClassLoader().toString());
        }
        System.out.println(System.getProperties());
        //appClassLoader
        ClassLoader contextClassLoader1 = Thread.currentThread().getContextClassLoader();
        System.out.println(contextClassLoader1);
        //appClassLoader
        ClassLoader systemClassLoader = ClassLoader.getSystemClassLoader();
        System.out.println(systemClassLoader);


    }
}
