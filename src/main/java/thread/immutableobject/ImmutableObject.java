package thread.immutableobject;

import java.math.BigInteger;
import java.util.Arrays;

/**
 * <P>不可变对象一定是线程安全的</P>
 * <P>怎样的对象才是完整的不可变对象</P>
 * <P>final的初始化安全性线程安全性</P>
 * <P>将除可变域之外的域定义为final是一个良好的习惯</P>
 * <P>不可变对象与volatile</P>
 *
 * @author lst
 */
public class ImmutableObject {

}

/**
 *
 * @author lst
 */
class OneValueCache {
    private final BigInteger lastNum;
    private final BigInteger[] factors;

    public OneValueCache(BigInteger lastNum, BigInteger[] factors) {
        this.lastNum = lastNum;
        this.factors = Arrays.copyOf(factors, factors.length);
    }

    public BigInteger[] getFactors(BigInteger lastNum) {
        if (lastNum == null || !this.lastNum.equals(lastNum)) {
            return null;
        } else {
            return Arrays.copyOf(factors, factors.length);
        }
    }
}


