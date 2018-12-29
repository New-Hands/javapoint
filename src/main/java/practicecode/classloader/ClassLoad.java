package practicecode.classloader;

/**
 * @author lst
 * <p>类加载考题</p>
 * <p>类加载机制的加载初始化过程
 * <p>主要是加载的机制（触发加载和加载策略和初始化过程
 * <p>整个流程加载 验证 准备 解析 初始化 使用 卸载</p>
 * <p>收集类型新cinit 收集实例信息init</p>
 * <p>cinit（按顺序收集执行代码块和属性） init（按循顺序收集执行代码块和属性 随后执行相应构造方法）</p>
 */
public class ClassLoad {

    static {
        System.out.printf("1");
    }

    /**
     * 静态属性
     */
    private static int b = 112;
    /**
     * 静态对象
     */
    static ClassLoad  load =new ClassLoad(4);

    {
        System.out.printf("2");
    }

    public  ClassLoad() {
        System.out.printf("3");
        System.out.printf("a="+a+",b="+b);
    }

    public  ClassLoad(int var) {
        System.out.printf(var+"");
        System.out.printf("a="+a+",b="+b);
    }
    int a = 110;


    /**
     * 在main方法之前会先执行初始化动作
     * @param args
     */
    public static void main(String[] args) {
        staticFunction();
    }

    private static void staticFunction() {
        System.out.printf("4");
    }

}
