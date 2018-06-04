package interfacedemo;

/**
 * java8中接口新特性 默认方法接口
 * @author lst
 */
public interface DefaultMethodDemo {
    /**
     * <P>得到参数的计算值 对参数进行*100运算</P>
     * @param p 被及计算的参数
     * @return 计算值
     */
    double calculate(double p);

    /**
     * 得到参数的开方数
     * @param a  被开方的参数
     * @return  计算值
     */
    default double sqrt(double a) {
        return Math.sqrt(a);
    }
}

class Test {
    public static void main(String[] args) {
        DefaultMethodDemo defaultMethodDemo = new DefaultMethodDemo() {
            @Override
            public double calculate(double p) {
                return sqrt(p * 100);
            }
        };

        final double calculate = defaultMethodDemo.calculate(100);
        final double calculate2 = defaultMethodDemo.calculate(100);
        System.out.println(" "+calculate+""+calculate2);
        System.out.println(" "+defaultMethodDemo.sqrt(16));
    }
}
