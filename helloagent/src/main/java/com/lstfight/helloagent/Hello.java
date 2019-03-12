package com.lstfight.helloagent;

import com.lstfight.helloagent.transform.DefaultTransformer;

import java.lang.instrument.Instrumentation;


/**
 * <p>
 *      agent magic
 * </p>
 *
 * @author 李尚庭
 * @date 2019-3-11
 */
public class Hello{
    public static void premain(String ops , Instrumentation inst) {
        main(inst);
    }

    private static synchronized void main(Instrumentation inst) {
        System.out.println("-------- Agent Start -------------");
        inst.addTransformer(new DefaultTransformer());
    }

    /**
     *  动态load
     * @param args
     * @param inst
     * @throws Exception
     */
    private static void agentmain(String args, Instrumentation inst) throws Exception
    {
        System.out.println("-------- Agent Start -------------");
    }
}
