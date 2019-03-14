package com.lstfight.helloagent.transform;


import javassist.*;

import java.io.IOException;
import java.lang.instrument.ClassFileTransformer;
import java.lang.instrument.IllegalClassFormatException;
import java.security.ProtectionDomain;

/**
 * <p>
 *     拦截类加载操作
 * </p>
 *
 * @author 李尚庭
 * @date 2019-3-11
 */
public class DefaultTransformer implements ClassFileTransformer {

    @Override
    public byte[] transform(ClassLoader loader, String className, Class<?> classBeingRedefined, ProtectionDomain protectionDomain, byte[] classfileBuffer) throws IllegalClassFormatException {
        if (className.equals("OrderController") || loader == null) {
            return null;
        }
        try {
            System.out.println(className);
            className = className.replace("/", ".");
            CtClass ctClass = ClassPool.getDefault().get(className);
            CtMethod[] methods = ctClass.getMethods();
            for (int i = 0; i < methods.length; i++) {
                CtMethod method = methods[i];
                //插入代码片段
                if (method.getName().equals("getOrdertList")) {
                    CtMethod declaredMethod = ctClass.getDeclaredMethod(method.getName());
                    //使用"" 需要转义
                    //可以使用$ 获取方法参数
                    System.out.println("insert into" + method.getName());
                    declaredMethod.insertBefore("System.out.println(\"agent magic\");");
                }
            }
            return ctClass.toBytecode();
        } catch (NotFoundException e) {
            e.printStackTrace();
        } catch (CannotCompileException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            System.out.println("error "+ className);
            System.out.println(e.getMessage());
        }

        return null;
    }
}
