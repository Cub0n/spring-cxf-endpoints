package org.example.soapendpoints.processor;


import org.example.types.ObjectFactory;

public abstract class ProcessorStrategy implements Processor {

  protected final ObjectFactory objectFactory = new ObjectFactory();

}
