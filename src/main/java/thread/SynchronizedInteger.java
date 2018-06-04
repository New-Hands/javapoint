package thread;

/**
 * <P>线程安全的可变整数类示例</P>
 * <P>非原子的64位操作</P>
 * @author lst
 */
public class SynchronizedInteger {
    private int value;

    public synchronized int getValue() {
        return value;
    }

    public synchronized void setValue(int value) {
        this.value = value;
    }
}
