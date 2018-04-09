package practicecode.classloader;

/**
 * @author lst
 * <p>类加载机制的加载初始化过程
 * <p>主要是加载的机制（触发加载和加载策略和初始化过程
 * <p>整个流程加载 验证 准备 解析 初始化 使用 卸载</p>
 * @// TODO: 2018/4/9
 */
public class ClassLoad {

    static ClassLoad  load =new ClassLoad();

    static {
        System.out.printf("1");
    }
    {
        System.out.printf("2");
    }

    public  ClassLoad() {
        System.out.printf("3");
        System.out.printf("a="+a+",b="+b);
    }

    int a = 110;
    private static int b = 112;
    public static void main(String[] args) {
        staticFunction();
    }

    private static void staticFunction() {
        System.out.printf("4");
    }


}
