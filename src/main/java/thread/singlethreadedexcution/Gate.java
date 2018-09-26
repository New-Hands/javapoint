package thread.singlethreadedexcution;

/**
 * <p>临界区 门</p>
 *
 * @author 李尚庭
 */
public class Gate {
    private String name = "NO";
    private String address = "NO";
    private int counter = 0;

    public synchronized void pass(String address,String name) {
        counter++;
        this.address = address;
        this.name = name;
        check();
    }

    private void check() {
        if (name.charAt(0) != address.charAt(0)) {
            System.out.println("break" + toStting());
        }
    }

    private String toStting() {
        return "no" + counter + ": " + name + "," + address;
    }

}
