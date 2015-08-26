package com.raunak.mail;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;

import java.io.StringWriter;

/**
 * Created by raunak.agrawal on 8/26/15.
 */
public class TestVelocity {

    public static void main(String[] args) {

        VelocityContext velocityContext = new VelocityContext();
        velocityContext.put("name", "Raunak");
        velocityContext.put("transactionId", "IMB8e723");
        VelocityEngine ve = VelocityHelper.getVelocityEngine();

        Template template = ve.getTemplate("vm-templates/citibank/ReviewEmail.vm");

        StringWriter writer = new StringWriter();

        template.merge(velocityContext, writer);

        String content = writer.toString();

        System.out.println(content);

        SendMail sendMail = new SendMail();
        sendMail.sendMail(content);
    }

}
