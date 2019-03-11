package com.lstfight.helloagent;

import com.lstfight.helloagent.transform.DefaultTransformer;

import java.lang.instrument.Instrumentation;

/**
 * <p>
 *
 * </p>
 *
 * @author 李尚庭
 * @date 2019-3-11
 */
public class Hello {
    public static void premain(String ops , Instrumentation inst) {
        inst.addTransformer(new DefaultTransformer());
    }

}
