package jmx;


import com.sun.tools.attach.VirtualMachine;
import com.sun.tools.attach.VirtualMachineDescriptor;

import javax.management.*;
import javax.management.remote.JMXConnector;
import javax.management.remote.JMXConnectorFactory;
import javax.management.remote.JMXServiceURL;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.List;
import java.util.function.Consumer;
import java.util.stream.Stream;


/**
 * <p>
 * 连接虚拟机
 * </p>
 *
 * @author 李尚庭
 * @date 2019-3-11
 */
public class JMXDemo {
    public static final String URL = "";


    public static void main(String[] args) {
        JMXDemo demo = new JMXDemo();
        demo.connect1();

    }

    /**
     * pid 连接
     */
    public void connect1() {
        List<VirtualMachineDescriptor> list = VirtualMachine.list();
        //Steam的for循环
        list.stream().
                forEach(virtualMachineDescriptor -> System.out.println(virtualMachineDescriptor.id()));

        Stream<VirtualMachineDescriptor> stream = list.stream();


    }

    /**
     *  jmx 连接
     */
    public void connect2() {
        try {
            JMXServiceURL serviceURL = new JMXServiceURL(URL);
            //jmx连接
            JMXConnector jmx = JMXConnectorFactory.connect(serviceURL);

            //通信管道
            MBeanServerConnection jmxServer = jmx.getMBeanServerConnection();
            //jmx对象描述 通过名字获取
            ObjectName threadObject = new ObjectName("jmx对象描述");
            Object attribute = jmxServer.getAttribute(threadObject, "属性名");

            //mBeanInfo
            MBeanInfo mBeanInfo = jmxServer.getMBeanInfo(threadObject);

        } catch (MalformedURLException e) {

            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (MalformedObjectNameException e) {
            e.printStackTrace();
        } catch (AttributeNotFoundException e) {
            e.printStackTrace();
        } catch (InstanceNotFoundException e) {
            e.printStackTrace();
        } catch (ReflectionException e) {
            e.printStackTrace();
        } catch (MBeanException e) {
            e.printStackTrace();
        } catch (IntrospectionException e) {
            e.printStackTrace();
        }
    }
}
