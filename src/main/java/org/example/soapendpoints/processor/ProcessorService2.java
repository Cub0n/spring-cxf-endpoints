package org.example.soapendpoints.processor;


import org.example.types.InputType;
import org.example.types.OutputType;

public class ProcessorService2 extends ProcessorStrategy {

  @Override
  public OutputType process(InputType input) {
    OutputType ot = objectFactory.createOutputType();
    ot.setResponse("Processed in 2: " + input.getRequest());
    return ot;
  }
}
