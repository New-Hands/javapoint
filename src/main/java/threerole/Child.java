package threerole;

public class Child extends Parent {
    @Override
    public void abs() {

    }
    @Override
    public void say() {
        System.out.println("hello c");
        //可以不用父类进行调用？
        sts();
    }
    public static void main(String[] args) {
        Parent parent = new Child();
        parent.rel();
    }

}
