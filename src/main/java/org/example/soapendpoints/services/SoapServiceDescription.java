package org.example.soapendpoints.services;

import org.example.soapendpoints.processor.Processor;

public interface SoapServiceDescription {

  String getServiceName();

  Processor getProcessor();
}
