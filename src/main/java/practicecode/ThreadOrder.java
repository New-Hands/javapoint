package practicecode;

/**
 * @author 李尚庭
 * @date 2018-9-10 上午 10:09
 */
public class ThreadOrder {
    public static void main(String[] args) {
        Thread thread = new Thread(() -> pong());
        thread.start();
        /**
         * 小心 Thread.run的迷惑
         */
        thread.run();

        System.out.println("ping");
    }

    static void pong() {
        System.out.println("pong");
    }
}
