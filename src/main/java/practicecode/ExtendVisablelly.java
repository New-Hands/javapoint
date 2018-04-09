package practicecode;

/**
 * 锐明校招
 * @author lst
 */
public class ExtendVisablelly extends Parent{
    public String grade = "hello";
    public static void main(String[] args) {
        Parent p = new ExtendVisablelly();
        System.out.println(p.color);
    }
}
