package org.example.soapendpoints.handler;

import org.example.soapendpoints.processor.Processor;
import org.example.soapendpoints.services.Registration;
import org.example.soapendpoints.services.SoapServiceDescription;
import org.example.types.InputType;
import org.example.types.OutputType;
import org.springframework.stereotype.Component;

@Component
public class Handler {

  private final Registration registration;

  public Handler(Registration registration) {
    this.registration = registration;
  }

  public OutputType handle(final InputType input) {

    preHook(input);

    Processor processor = registration.getRegisteredObject(SoapServiceDescription.class)
        .getProcessor();

    OutputType output = processor.process(input);

    postHook(output);

    return output;
  }

  protected void preHook(InputType input) {

  }

  protected void postHook(OutputType output) {

  }

}
