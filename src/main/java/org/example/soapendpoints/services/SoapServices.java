package org.example.soapendpoints.services;

import org.example.soapendpoints.processor.Processor;
import org.example.soapendpoints.processor.ProcessorService1;
import org.example.soapendpoints.processor.ProcessorService2;

public enum SoapServices implements SoapServiceDescription {

  SERVICE1(serviceName("Service1")) {
    @Override
    public Processor getProcessor() {
      return new ProcessorService1();
    }
  },

  SERVICE2(serviceName("Service2")) {
    @Override
    public Processor getProcessor() {
      return new ProcessorService2();
    }
  };

  private final String serviceName;

  SoapServices(Builder builder) {
    serviceName = builder.name;
  }

  static Builder serviceName(String name) {
    return new Builder(name);
  }

  @Override
  public String getServiceName() {
    return serviceName;
  }

  private record Builder(String name) {

  }
}
