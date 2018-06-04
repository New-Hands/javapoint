package thread.threadtonfinement;

/**
 * <P>使用threadlocal类进行线程封闭</P>
 * <P>threadlocal变量类似于全局变量 是全局的</P>
 * <P>ThreadLocal里有保存变量的容器</P>
 * <P>Threadlocal是封装好的对象安全访问工具  实现了线程封闭的效果</P>
 * <P>又是一个lambda的使用</P>
 *
 * @author lst
 */
public class ThreadLocalDemo {
    private static ThreadLocal<String> state = ThreadLocal.withInitial(() -> "true");

    public static String getState() {
        //通过Threadlocal获取到变量
        return state.get();
    }

    public static void main(String[] args) {
        System.out.println(getState());
    }
}
