package practicecode;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

/**
 * 锐明校招 查找文件中某个字符出现的次数(绝对路径或相对路径)
 * 使用IO时注意异常的捕捉
 * 什么时候应注意异常的捕捉
 *
 * @author lst
 */
public class FileScanner {
    public static void main(String[] args) throws IOException{
        System.out.println(read("C:\\IdeaProjects\\javapoint\\src\\main\\java\\practicecode\\str.txt", 'o'));

    }

    public static int read(String path, char ch) throws IOException{
        int sum = 0;
        Scanner in = null;
            in = new Scanner(new File(path));
            while (in.hasNextLine()) {
                String str = in.nextLine();
                char[] chars = str.toCharArray();
                for (int i = 0; i < chars.length; i++) {
                    if (chars[i] == ch) {
                        System.out.println(chars[i]);
                        sum++;
                    }
                }
            }
            return sum;
    }
}
