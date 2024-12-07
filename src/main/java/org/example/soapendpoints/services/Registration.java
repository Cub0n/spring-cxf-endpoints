package org.example.soapendpoints.services;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import org.springframework.context.ApplicationContext;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class Registration {

  private final ApplicationContext applicationContext;

  private final Map<Class<?>, Object> registration = new ConcurrentHashMap<>();

  public Registration(ApplicationContext applicationContext) {
    this.applicationContext = applicationContext;
  }

  public <T, S extends T> void register(Class<T> clazzDesc, Class<S> clazzImpl) {
    registration.put(clazzDesc, clazzImpl);
  }

  public <T> void register(Class<T> clazzDesc, Object object) {
    if (clazzDesc.isAssignableFrom(object.getClass())) {
      registration.put(clazzDesc, object);
    } else {
      throw new ClassCastException();
    }
  }

  @Nullable
  public <T> T getRegisteredObject(Class<T> clazzDesc) {
    return switch (registration.get(clazzDesc)) {
      case null -> null;
      case Class<?> clazz -> clazzDesc.cast(applicationContext.getBean(clazz));
      case Object obj -> clazzDesc.cast(obj);
    };
  }
}
