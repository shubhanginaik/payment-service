package fs19.azure.paymentserviceapplication.service;

import com.azure.messaging.servicebus.ServiceBusClientBuilder;
import com.azure.messaging.servicebus.ServiceBusErrorContext;
import com.azure.messaging.servicebus.ServiceBusProcessorClient;
import com.azure.messaging.servicebus.ServiceBusReceivedMessageContext;
import com.fasterxml.jackson.databind.ObjectMapper;
import fs19.azure.paymentserviceapplication.dto.PaymentRequest;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class PaymentMessageProcessor {

  private final Logger LOGGER = LogManager.getLogger(PaymentMessageProcessor.class);
  private final ServiceBusClientBuilder serviceBusClientBuilder;
  private ServiceBusProcessorClient serviceBusProcessorClient;
  @Value("${azure.servicebus.queue-name}")
  private String paymentQueueName;
  private final ObjectMapper objectMapper = new ObjectMapper();

  @Autowired
  public PaymentMessageProcessor(ServiceBusClientBuilder serviceBusClientBuilder) {
    this.serviceBusClientBuilder = serviceBusClientBuilder;
  }

  @PostConstruct
  public void init() {
    this.serviceBusProcessorClient = serviceBusClientBuilder
        .processor()
        .queueName(paymentQueueName)
        .processMessage(this::processMessage)
        .processError(this::processError)
        .buildProcessorClient();
  }

  @Scheduled(fixedDelay = 5000, initialDelay = 1000)
  public void pollForMessages() {
    LOGGER.info("Starting service bus processor client");
    serviceBusProcessorClient.start();
  }

  public void processMessage(ServiceBusReceivedMessageContext serviceBusReceivedMessageContext) {
    try {
      LOGGER.info("Processing message...");
      var serviceBusMessage = serviceBusReceivedMessageContext.getMessage();
      if (serviceBusMessage.getSubject().equals("PaymentRequest")) {
        String message = serviceBusReceivedMessageContext.getMessage().getBody().toString();
        PaymentRequest paymentRequest = objectMapper.readValue(message, PaymentRequest.class);
        if (paymentRequest == null) {
          LOGGER.error("Received message is null");
          return;
        }
        if (paymentRequest.getAmount() > 0) {
          LOGGER.info("Payment request processed successfully: {}", paymentRequest);
        } else {
          LOGGER.error("Payment request failed: {}", paymentRequest);
        }
      } else {
        LOGGER.info("Received unknown message with subject: {}", serviceBusMessage.getSubject());
      }

    } catch (Exception e) {
      LOGGER.error("Error during message processing: {}", e.getMessage(), e);
    }
  }

  public void processError(ServiceBusErrorContext serviceBusErrorContext) {
    LOGGER.error("Error occurred: {}", serviceBusErrorContext.getException());
  }

  @PreDestroy
  public void stop() {
    LOGGER.info("Stopping service bus processor client");
    serviceBusProcessorClient.stop();
  }
}