package pattern.proxy;

/**
 * @author lst
 * Created on 2018/6/26.
 */
public class NomalProxyer implements Proxy {
    @Override
    public void sayhello() {
        System.out.printf("hello");
    }
}
