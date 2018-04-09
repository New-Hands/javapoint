package practicecode;

/**
 * @author lst
 * 给定一个字符串，记录字符串中每个字符出现的次数
 * ？
 */
public class CharCounter {
    public static void main(String[] args) {
        CharCounter cc = new CharCounter();
        char[] chars = {'a','a','b','c'};
        cc.couter(chars);
    }

    //最大可能存储的元素的个数
    public static final int NUM = 26;

    /**
     *
     * @param arg
     *
     */
    public void couter (char[] arg) {
        int[] counter = new int[NUM];
        for (int i = 0; i < arg.length; i++) {
            //利用相同的字符相同ascii码值作为数组的下表为标志，省去字符判断的环节
            //a元素的值为97所以减去97使得从0计数
            counter[arg[i]-97]++;
        }
        for (int i = 0; i < counter.length; i++) {
            if (counter[i] == 0)
                continue;
            System.out.println( (char)(i+97) +":"+ counter[i]);
        }
    }
}
