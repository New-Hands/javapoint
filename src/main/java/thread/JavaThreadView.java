package thread;

/**
 * 阿里巴巴约定不显示的创建线程，推荐使用线程池。
 * 那如果线程池并不适合当前的场景
 *
 * @author lst
 */

public class JavaThreadView {

    public static void main(String[] args) {
        Thread threadOne = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    System.out.println("hrrlo1");
                    try {
                        Thread.sleep(10);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });

        threadOne.start();

        Thread threadTwo = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    System.out.println("hrrlo2");
                    try {
                        Thread.sleep(10);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });

        threadTwo.start();
    }


}
