package enumdemo;


import java.io.Serializable;

/**
 * @author 李尚庭
 * @date 2018-12-25
 */
public enum EnumDemo implements Serializable {
    HE("1","lst"),
    SL("2","sl");

    private String name;
    private String value;

    EnumDemo(String value, String name) {
        this.value = value;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public String getValue() {
        return value;
    }
}


