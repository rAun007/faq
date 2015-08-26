package com.raunak.mail;

import org.apache.velocity.app.VelocityEngine;

import java.util.Properties;

/**
 * Created by raunak.agrawal on 8/26/15.
 */
public class VelocityHelper {
    private static VelocityEngine velocityEngine;

    static VelocityEngine getVelocityEngine() {
        if (velocityEngine == null)
            init();
        return velocityEngine;
    }

    private static void init() {
        velocityEngine = new VelocityEngine();
        Properties velocityProperties = new Properties();
        velocityProperties.put("resource.loader", "class");
        velocityProperties.put("class.resource.loader.description", "Velocity Classpath Resource Loader");
        velocityProperties.put("class.resource.loader.class",
                "org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader");
        velocityEngine.init(velocityProperties);
    }
}
