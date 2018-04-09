package alg;

/**
 * 反转字符串
 *
 * @author lst
 */
public class reserveString {

    public static void main(String[] args) {
        System.out.println(reserve("lishangting "));
    }

    public static String reserve(String string) {
        //有什么需要检查的条件
        /**
         * 首尾有空格的情况
         */
        char[] temp = string.toCharArray();
        int i = 0;
        int j = string.length() - 1;
        //两端同时进行
        while (j > i) {
            //相比之前的算法 进行了代码的优化 利用自增和自减
            char ch = temp[j];
            temp[j--] = temp[i];
            temp[i++] = ch;
        }
        return String.valueOf(temp);
    }

    /**
     * 利用字符转到节数组的转换方式
     *
     * @param string
     * @return
     */
    public static String reserve2(String string) {

        byte[] bytes = string.getBytes();
        return "";
    }
}
