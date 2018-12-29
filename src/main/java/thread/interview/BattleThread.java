package thread.interview;

/**
 * @author 李尚庭
 * @date 2018-9-10 上午 10:10 
 */
public class BattleThread implements Runnable {

    /**
     * 属性没有锁保护
     */
    private int b = 100;

    private long time;

    public synchronized int getB() {
        return b;
    }

    public synchronized void m1() throws InterruptedException {
        //b = 1000;
        //Thread.sleep(500);
        System.out.println(Thread.currentThread().getName()+"m1 b="+ b+ (System.currentTimeMillis()-time));
    }

    public synchronized void m2() throws InterruptedException {
        b = 2000;
        time = System.currentTimeMillis();
        Thread.sleep(250);
        //System.out.println(Thread.currentThread().getName()+"m2 b=" +b);
    }

    @Override
    public void run() {
        try {
            m1();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        BattleThread battleThread = new BattleThread();
        Thread thread = new Thread(battleThread,"battle");

        thread.start();

        battleThread.m2();

        //读数据改方法没有要求锁
        System.out.println(Thread.currentThread().getName()+" b =" + battleThread.b + (System.currentTimeMillis()-battleThread.time));

        //一般情况main先运行m2修改值2000在sleep250（不会失去监视器权限） battle线程已经执行修改值1000 main线程醒来读到1000
    }
}
