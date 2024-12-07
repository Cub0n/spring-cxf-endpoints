package org.example.soapendpoints.configuration;

import jakarta.annotation.PostConstruct;
import java.util.List;
import org.apache.cxf.Bus;
import org.apache.cxf.endpoint.Server;
import org.apache.cxf.jaxws.JaxWsServerFactoryBean;
import org.example.soapendpoints.annotation.WebServiceEndpoint;
import org.springframework.boot.web.servlet.context.AnnotationConfigServletWebServerApplicationContext;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Configuration;

@Configuration
public class EndpointConfiguration {

  private final ApplicationContext applicationContext;

  private final Bus bus;

  public EndpointConfiguration(ApplicationContext applicationContext, Bus bus) {
    this.applicationContext = applicationContext;
    this.bus = bus;
  }

  @PostConstruct
  public void initializeEndpoints() {
    List<Object> endpointBeans = applicationContext.getBeansWithAnnotation(
        WebServiceEndpoint.class).values().stream().toList();

    for (Object bean : endpointBeans) {
      ((AnnotationConfigServletWebServerApplicationContext) applicationContext).getBeanFactory()
          .registerSingleton("endpoint" + bean.getClass().getSimpleName(), generateEndpoint(bean));
    }
  }

  private Server generateEndpoint(Object bean) {
    JaxWsServerFactoryBean jaxWsServerFactoryBean = new JaxWsServerFactoryBean();

    jaxWsServerFactoryBean.setBus(bus);
    jaxWsServerFactoryBean.setServiceBean(bean);

    WebServiceEndpoint webServiceEndpoint = bean.getClass().getAnnotation(WebServiceEndpoint.class);
    jaxWsServerFactoryBean.setAddress(webServiceEndpoint.value());

    return jaxWsServerFactoryBean.create();
  }
}
