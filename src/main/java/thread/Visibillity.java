package thread;

/**
 * <P>错误的可见性示范 可能看不见修改后的值</P>
 * <P>这里充满了不确定性</P>
 * <P>比原书更好的可见性例子</P>
 * volatile变量规则：对一个volatile域的写，happens-before于任意后续对这个volatile域的读。
 *
 *
 * @author lst
 */
public class Visibillity {
    private volatile static boolean unReady;
    private volatile static int num;

    private static class ReaderThread extends Thread {
        @Override
        public void run() {
            while (!unReady) {
                System.out.println(num);
            }
            System.out.println(num);
        }
    }

    private static class WriterThread extends Thread {
        @Override
        public void run() {
            num = 10;
            unReady = true;
        }
    }

    public static void main(String[] args) {
        /**
         * 在多数的时间内是照着代码的逻辑顺序进行的
         */
        new ReaderThread().start();
        new WriterThread().start();

    }
}
