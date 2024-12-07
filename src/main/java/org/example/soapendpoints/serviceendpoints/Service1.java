package org.example.soapendpoints.serviceendpoints;

import org.example.soapendpoints.annotation.WebServiceEndpoint;
import org.example.soapendpoints.handler.Handler;
import org.example.soapendpoints.services.Registration;
import org.example.soapendpoints.services.SoapServiceDescription;
import org.example.soapendpoints.services.SoapServices;
import org.example.types.InputType;
import org.example.types.OutputType;
import org.springframework.stereotype.Service;
import service.IService1;


@Service
@WebServiceEndpoint("/service1")
public class Service1 implements IService1 {


  private final Registration registration;

  private final Handler handler;
  
  public Service1(Registration registration, Handler handler) {
    this.registration = registration;
    this.handler = handler;
  }

  @Override
  public OutputType callService1(InputType input) {
    registration.register(SoapServiceDescription.class, SoapServices.SERVICE1);
    return handler.handle(input);
  }
}
