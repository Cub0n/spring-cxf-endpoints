package org.example.soapendpoints;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import jakarta.xml.bind.Marshaller;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import org.example.types.InputType;
import org.example.types.ObjectFactory;
import org.example.types.OutputType;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.ws.client.core.WebServiceTemplate;


@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class SoapEndpointApplicationTest {

  private static final Jaxb2Marshaller MARSHALLER = new Jaxb2Marshaller();

  private final ObjectFactory objectFactory = new ObjectFactory();

  @LocalServerPort
  private int port;

  @BeforeAll
  public static void setUp() throws Exception {
    MARSHALLER.setContextPath("org.example.types");
    Map<String, Object> properties = new HashMap<>();
    properties.put(Marshaller.JAXB_ENCODING, StandardCharsets.UTF_8.name());
    properties.put(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
    MARSHALLER.setMarshallerProperties(properties);
    MARSHALLER.afterPropertiesSet();
  }

  @Test
  void callService1() {
    WebServiceTemplate ws = new WebServiceTemplate(MARSHALLER);

    InputType input = objectFactory.createInputType();
    input.setRequest("Test 1");

    OutputType output = (OutputType) ws.marshalSendAndReceive(
        "http://localhost:" + port + "/ws/service1", input);

    assertNotNull(output);
    assertEquals("Processed in 1: Test 1", output.getResponse());
  }


  @Test
  void callService2() {
    WebServiceTemplate ws = new WebServiceTemplate(MARSHALLER);

    InputType input = objectFactory.createInputType();
    input.setRequest("Test 2");

    OutputType output = (OutputType) ws.marshalSendAndReceive(
        "http://localhost:" + port + "/ws/service2", input);

    assertNotNull(output);
    assertEquals("Processed in 2: Test 2", output.getResponse());
  }

}
