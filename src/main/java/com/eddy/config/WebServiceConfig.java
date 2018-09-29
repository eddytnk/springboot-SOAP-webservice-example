package com.eddy.config;

import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.ws.config.annotation.EnableWs;
import org.springframework.ws.transport.http.MessageDispatcherServlet;
import org.springframework.ws.wsdl.wsdl11.DefaultWsdl11Definition;
import org.springframework.xml.xsd.SimpleXsdSchema;
import org.springframework.xml.xsd.XsdSchema;

@Configuration
@EnableWs
public class WebServiceConfig {
    //configure a MessageDispatcherServlet to help identify endpoints
    //uri for endpoint should be configure to "ws/*"

    @Bean
    public ServletRegistrationBean messageDispatcherServlet(ApplicationContext context){
        MessageDispatcherServlet messageDispatcherServlet = new MessageDispatcherServlet();
        messageDispatcherServlet.setApplicationContext(context);
        messageDispatcherServlet.setTransformWsdlLocations(true);
        return new ServletRegistrationBean(messageDispatcherServlet,"/ws/*");
    }


    //generate a courses.wsdl using the course-detail.xsd schema
    //Configure
    // expose wsdl at ws/courses.wsdl
    // PortType = CoursePort
    // Namespace: http://eddy.com/courses


    @Bean
    public XsdSchema coursesSchema(){
        return new SimpleXsdSchema(new ClassPathResource("course-details.xsd"));
    }

    // expose wsdl at ws/course.wsdl
    @Bean(name = "courses")
    DefaultWsdl11Definition defaultWsdl11Definition(XsdSchema coursesSchema){
        DefaultWsdl11Definition definition = new DefaultWsdl11Definition();
        definition.setPortTypeName("CoursePort");
        definition.setTargetNamespace("http://eddy.com/courses");
        definition.setLocationUri("/ws");
        definition.setSchema(coursesSchema);
        return definition;
    }
}
