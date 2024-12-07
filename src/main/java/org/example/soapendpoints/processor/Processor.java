package org.example.soapendpoints.processor;

import org.example.types.InputType;
import org.example.types.OutputType;

public interface Processor {

  OutputType process(InputType input);

}
