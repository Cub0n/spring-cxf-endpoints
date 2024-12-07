Example for autowiring Apache CXF endpoints.

The implementation circumvents the problem if the beans are registered too early.

_JaxWsServerFactoryBean_ does not initialize the underlying service bean properly if the registration is done via 
_public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory)_ (so in Apache CXF Version 4.0.5)

This means: The endpoint and the service beans are created, but the service beans have no other @Autowiring beans.

Moreover: With the new paradigm to @Autowire Beans in the contructor, the service bean can not be initialized, because no appropriate Constructor was found and the runtime throws an error.

So, the registration of the endoints is done in later step with a @Configuration-bean.

Inspired from:
https://blog.mpesteban.dev/post/how-to-create-soap-services-with-spring-boot-apache-cxf
