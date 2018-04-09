package generic;

/**
 *
 * @author lst
 * @param <T>
 * <p>如果使用泛型是因为不知道调用者需要传入何种类型，
 * 那么这个方法的编写者怎么确定当前类型的处理方式？
 * 那么这种数据处理方式一定是与数据类型无关的
 * </p>
 */
public class TypeOne<T> {
    private T t ;

    public T saySome(String string) {
        System.out.println(string);
        //强转
        return (T) "he";
    }

    public static void main(String[] args) {
        TypeOne<String> stringTypeOne = new TypeOne<>();
        System.out.println(stringTypeOne.saySome("hello"));
    }
}
