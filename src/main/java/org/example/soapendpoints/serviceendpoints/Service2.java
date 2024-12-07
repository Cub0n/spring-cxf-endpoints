package org.example.soapendpoints.serviceendpoints;

import org.example.soapendpoints.annotation.WebServiceEndpoint;
import org.example.soapendpoints.handler.Handler;
import org.example.soapendpoints.services.Registration;
import org.example.soapendpoints.services.SoapServiceDescription;
import org.example.soapendpoints.services.SoapServices;
import org.example.types.InputType;
import org.example.types.OutputType;
import org.springframework.stereotype.Service;
import service.IService2;


@Service
@WebServiceEndpoint("/service2")
public class Service2 implements IService2 {

  private final Registration registration;

  private final Handler handler;

  public Service2(Registration registration, Handler handler) {
    this.registration = registration;
    this.handler = handler;
  }

  @Override
  public OutputType callService2(InputType input) {
    registration.register(SoapServiceDescription.class, SoapServices.SERVICE2);
    return handler.handle(input);
  }
}
