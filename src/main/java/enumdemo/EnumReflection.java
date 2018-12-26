package enumdemo;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * @author 李尚庭
 * @date 2018-12-25
 */
public class EnumReflection {
    Class demoEnum = EnumDemo.class;

    public static Map<String, String> getDemoEnum(Class<? extends Enum> clazz) {
        //枚举类所有实例
        Object[] enumConstants = clazz.getEnumConstants();

        Method getValue;
        Method getName;
        try {
            getValue = clazz.getMethod("getValue");
            getName = clazz.getMethod("getName");
        } catch (NoSuchMethodException e) {
            throw new RuntimeException("请照规则配置常量类!");
        }

        Map<String, String> res = new HashMap<>();
        for (int i = 0; i < enumConstants.length; i++) {
            Object enumConstant = enumConstants[i];
            try {
                res.put(getValue.invoke(enumConstant).toString(), getName.invoke(enumConstant).toString());
            } catch (IllegalAccessException e) {
                throw new RuntimeException("多给我一点权限！");
            } catch (InvocationTargetException e) {
                throw new RuntimeException(".....");
            }
        }
        return res;
    }

    public static void main(String[] args) {
        Map<String, String> demoEnum = getDemoEnum(EnumDemo.class);
        System.out.println("compele");

    }
}
