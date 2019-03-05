package guava;

import com.google.common.base.Optional;
import com.google.common.cache.*;

import java.util.Scanner;
import java.util.concurrent.TimeUnit;

/**
 * <p>缓存案例</p>
 * <p>
 * 与ConcurrentHashMap使用场景及取舍
 * CacheBuilder 创建配置一个cache
 * CacheLoader 定义没有缓存数据的添加操作 或是使用Callable的回调方式
 * 回收策略 保证不长时间占用 基于容量maximumWeight maximumSize 基于时间 expireAfterWrite expireAfterAccess
 * 监听器
 * 刷新策略
 * 垃圾回收 ？ cache.cleanUp()
 * </p>
 *
 * @author 李尚庭
 * @date 2018-12-29
 */
public class CacheDemo {

    private LoadingCache<String, String> cache;

    private CacheDemo() {
        cache = CacheBuilder.newBuilder()
                //回收策略
                .maximumSize(1000)
                .expireAfterWrite(10, TimeUnit.SECONDS)
                .removalListener(notification -> {
                    //监听器
                })
                .build(new CacheLoader<String, String>() {
                    @Override
                    public String load(String key) {
                        //加载逻辑
                        System.out.println("save:" + key);
                        return key;
                    }
                });
    }

    public static void main(String[] args) {
        CacheDemo cacheDemo = new CacheDemo();
        Scanner scanner = new Scanner(System.in);
        //hasNextLine方法阻塞等待输入
        while (scanner.hasNextLine()) {
            String s = scanner.nextLine();
            try {
                //该方法不会触发缓存
                String ifPresent = cacheDemo.cache.getIfPresent(s);
                Optional<String> ifPresent1 = Optional.fromNullable(ifPresent);
                if (ifPresent1.isPresent()) {
                    System.out.println(ifPresent);
                }
                System.out.println(cacheDemo.cache.get(s));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
