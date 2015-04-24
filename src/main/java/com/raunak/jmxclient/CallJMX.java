package com.raunak.jmxclient;

import java.io.IOException;

import javax.management.MBeanServerConnection;
import javax.management.MBeanServerInvocationHandler;
import javax.management.MalformedObjectNameException;
import javax.management.ObjectName;
import javax.management.remote.JMXConnector;
import javax.management.remote.JMXConnectorFactory;
import javax.management.remote.JMXServiceURL;

import com.inmobi.platform.rbac.AuthManagementMBean;

/**
 * Created by raunak.agrawal on 4/23/15.
 */
public class CallJMX {

    public static final String HOST = "10.14.118.53";
    public static final String PORT = "9102";

    public static void main(String[] args) throws IOException, MalformedObjectNameException {

        JMXServiceURL url = new JMXServiceURL("service:jmx:rmi:///jndi/rmi://" + HOST + ":" + PORT + "/jmxrmi");

        JMXConnector jmxConnector = JMXConnectorFactory.connect(url);

        MBeanServerConnection mbeanServerConnection = jmxConnector.getMBeanServerConnection();

        // ObjectName should be same as your MBean name
        ObjectName mbeanName = new ObjectName("RBAC MBeans:type=AuthBean");

        // Get MBean proxy instance that will be used to make calls to registered MBean
        AuthManagementMBean mbeanProxy = (AuthManagementMBean) MBeanServerInvocationHandler.newProxyInstance(
                mbeanServerConnection, mbeanName, AuthManagementMBean.class, true);

        // let's make some calls to mbean through proxy and see the results.
        mbeanProxy.refreshCache();
    }
}
