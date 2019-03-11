package guava;

import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;
import com.google.common.base.Optional;
import com.google.common.base.Preconditions;
import com.google.common.collect.ComparisonChain;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 李尚庭
 * @date 2019-3-5
 */
public class BasicUtilTest {
    public static void main(String[] args) {
        List<String> list = null;
        //创建可能为空的optional
        Optional<List<String>> optional = Optional.fromNullable(list);
        //判断是否为空
        boolean present = optional.isPresent();
        System.out.println(present);
        //设置默认值
        List<String> or = optional.or(new ArrayList<>());
        or.add("df");
        System.out.println(or);
        //可以返回控制
        List<String> strings = optional.orNull();
        //创建optional并检查是否为空 可抛出空异常
        Optional<List<String>> of = Optional.of(new ArrayList<>());
        //创建一个默认的
        Optional<Object> absent = Optional.absent();

        /**
         * predication 前置条件判断 提供很多检查的方法  通过抛出异常的方式来终端运行
         * /
        /**
         * Preconditions类与Assert断言类的思想基本是一致的
         */
        try {
            List<String> strings1 = Preconditions.checkNotNull(list);
        } catch (Exception e) {
            //e.printStackTrace();
        }

        /**
         * Objects 需要重写Object的方法
         */
        System.out.println(Objects.equal("a", "b"));
        Objects.hashCode(list);
        //MoreObjects.toStringHelper(list);
        int result = ComparisonChain.start().compare(12, 7).compare(10, 9).result();
        System.out.println(result);

    }
}
