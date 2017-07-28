package com.java.training.boot.config;

import org.springframework.boot.context.embedded.ServletRegistrationBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.http.converter.xml.Jaxb2RootElementHttpMessageConverter;
import org.springframework.ws.config.annotation.EnableWs;
import org.springframework.ws.config.annotation.WsConfigurerAdapter;
import org.springframework.ws.transport.http.MessageDispatcherServlet;
import org.springframework.ws.wsdl.wsdl11.DefaultWsdl11Definition;
import org.springframework.xml.xsd.XsdSchemaCollection;
import org.springframework.xml.xsd.commons.CommonsXsdSchemaCollection;

/**
 * Created by atp1pak on 7/18/2016.
 */
@EnableWs
@Configuration
public class WebServiceConfig extends WsConfigurerAdapter {

    @Bean
    public Jaxb2RootElementHttpMessageConverter jaxbConverter() {
        return new Jaxb2RootElementHttpMessageConverter();
    }

    @Bean
    public XsdSchemaCollection searchSchema() {
        Resource[] resources = {
                new ClassPathResource("schema/EmployeeSearchRQ.xsd"),
                new ClassPathResource("schema/EmployeeSearchRS.xsd")
        };
        CommonsXsdSchemaCollection schemaCollection = new CommonsXsdSchemaCollection(resources);
        schemaCollection.setInline(true);
        return schemaCollection;
    }

    @Bean
    public XsdSchemaCollection deleteSchema() {
        Resource[] resources = {
                new ClassPathResource("schema/EmployeeDeleteRQ.xsd"),
                new ClassPathResource("schema/EmployeeDeleteRS.xsd")
        };
        CommonsXsdSchemaCollection schemaCollection = new CommonsXsdSchemaCollection(resources);
        schemaCollection.setInline(true);
        return schemaCollection;
    }

    @Bean
    public XsdSchemaCollection addSchema() {
        Resource[] resources = {
                new ClassPathResource("schema/EmployeeAddRQ.xsd"),
                new ClassPathResource("schema/EmployeeAddRS.xsd")
        };
        CommonsXsdSchemaCollection schemaCollection = new CommonsXsdSchemaCollection(resources);
        schemaCollection.setInline(true);
        return schemaCollection;
    }


    @Bean(name = "EmployeeSearch")
    public DefaultWsdl11Definition employeeSearchDefinition(XsdSchemaCollection searchSchema) {
        DefaultWsdl11Definition employeeSearchDefinition = new DefaultWsdl11Definition();
        employeeSearchDefinition.setPortTypeName("EmployeePort");
        employeeSearchDefinition.setServiceName("EmployeeSearch");
        employeeSearchDefinition.setRequestSuffix("RQ");
        employeeSearchDefinition.setResponseSuffix("RS");
        employeeSearchDefinition.setLocationUri("/ws/EmployeeSearch");
        employeeSearchDefinition.setTargetNamespace("http://www.iTech.com/employee/portal");
        employeeSearchDefinition.setSchemaCollection(searchSchema);
        return employeeSearchDefinition;
    }

    @Bean(name = "EmployeeDelete")
    public DefaultWsdl11Definition employeeDeleteDefinition(XsdSchemaCollection deleteSchema) {
        DefaultWsdl11Definition employeeDeleteDefinition = new DefaultWsdl11Definition();
        employeeDeleteDefinition.setPortTypeName("EmployeePort");
        employeeDeleteDefinition.setServiceName("EmployeeDelete");
        employeeDeleteDefinition.setRequestSuffix("RQ");
        employeeDeleteDefinition.setResponseSuffix("RS");
        employeeDeleteDefinition.setLocationUri("/ws/EmployeeDelete");
        employeeDeleteDefinition.setTargetNamespace("http://www.iTech.com/employee/portal");
        employeeDeleteDefinition.setSchemaCollection(deleteSchema);
        return employeeDeleteDefinition;
    }

    @Bean(name = "EmployeeAdd")
    public DefaultWsdl11Definition employeeAddDefinition(XsdSchemaCollection addSchema) {
        DefaultWsdl11Definition employeeAddDefinition = new DefaultWsdl11Definition();
        employeeAddDefinition.setPortTypeName("EmployeePort");
        employeeAddDefinition.setServiceName("EmployeeAdd");
        employeeAddDefinition.setRequestSuffix("RQ");
        employeeAddDefinition.setResponseSuffix("RS");
        employeeAddDefinition.setLocationUri("/ws/EmployeeAdd");
        employeeAddDefinition.setTargetNamespace("http://www.iTech.com/employee/portal");
        employeeAddDefinition.setSchemaCollection(addSchema);
        return employeeAddDefinition;
    }

    @Bean
    public ServletRegistrationBean messageDispatcherServlet(ApplicationContext applicationContext) {
        MessageDispatcherServlet servlet = new MessageDispatcherServlet();
        servlet.setApplicationContext(applicationContext);
        servlet.setTransformWsdlLocations(true);
        return new ServletRegistrationBean(servlet, "/ws/*");
    }

}
