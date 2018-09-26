package thread.singlethreadedexcution;

/**
 * <p>single thread execution模式 通过加锁阻塞的方式，制作临界区，以一个线程的方式执行</p>
 * <p>执行线程</p>
 *
 * @author 李尚庭
 */
public class UserThread extends Thread {
    private final String name;
    private final String address;
    private final Gate gate;

    public UserThread(String name, String address, Gate gate) {
        this.name = name;
        this.address = address;
        this.gate = gate;
    }

    @Override
    public void run() {
        System.out.println(name + "begin");
        while (true) {
            gate.pass(address,name);
        }
    }

    public static void main(String[] args) {
        Gate gate = new Gate();
        new Thread(new UserThread("lst", "lstzz1", gate)).start();
        new Thread(new UserThread("sl", "slfdfd", gate)).start();
        new Thread(new UserThread("zz", "zzfdfd", gate)).start();
    }
}
