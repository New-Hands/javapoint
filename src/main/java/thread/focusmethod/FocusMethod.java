package thread.focusmethod;

/**
 * <p>
 * thread 的关键方法
 * </p>
 *
 * @author 李尚庭
 * @date 2019-3-14
 */
public class FocusMethod {
    public static void main(String[] args) {
        waitJoin();
    }

    public static void waitJoin() {
        /**
         * 三个时间
         */
        final int millis = 3000;
        final int millis1 = 4000;
        final int millis2 = 1000;
        new Thread(() -> {
            Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        Thread.sleep(millis);
                        System.out.println("睡醒了");
                    } catch (InterruptedException e) {
                        //结束了就不会被打扰
                        System.out.println("被叫醒");
                    }
                }
            });
            thread.start();
            try {
                Thread.sleep(millis1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            thread.interrupt();
            try {
                //这里调用了 thread的wait方法 自己等自己？
                thread.join(millis2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println("不了你的join");

        }).start();
    }
}
