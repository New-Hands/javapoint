package interfacedemo;

/**
 * java8中接口新特性 默认方法接口
 * @@author lst
 *
 */
public interface DefaultMethodDemo {
    double calculate(double p);

    default double sqrt(double a) {
        return Math.sqrt(a);
    }
}

class test {
    public static void main(String[] args) {
        DefaultMethodDemo defaultMethodDemo = new DefaultMethodDemo() {
            @Override
            public double calculate(double p) {
                return sqrt(p * 100);
            }
        };
        final double calculate = defaultMethodDemo.calculate(100);
        System.out.println(" "+calculate);
        System.out.println(" "+defaultMethodDemo.sqrt(16));
    }
}
