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

/*
    public void ssay(SChild sChild) {
        System.out.println(" s child !");
    }
*/

    public void ssay(inn child) {
        System.out.println(" child !");
    }

 /*   public class inns {

    }*/

    public class inn {
        public void hhh() {

        }

    }

    //测试
    public static void main(String[] args) {
        //重载和重写调用时的细节 静态分派 动态分派 方法签名
        Parent parent = new Child();
        //访问性问题
/*        Child.inn child = Child.new inn();
        parent.say(child);*/
        parent.rel();
    }

}
