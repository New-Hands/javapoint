package com.lstfight.helloagent.transform;

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

        return new byte[0];
    }
}
