package practicecode;

/**
 * 锐明校招
 * 指令执行 栈结构相关
 * @author lst
 */
public class WhoFirst {
    public static void main(String[] args) {
        int i = 12;
        System.out.println(i+=i-=i*=i);
        //等同于 i = i + (i - (i * i));怎样的数据结构来支撑这样的运算

        i*=i;
        i-=i;
        i+=i;
        System.out.println(i);
    }
}

