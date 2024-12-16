package fs19.azure.paymentserviceapplication;

import com.azure.messaging.servicebus.ServiceBusProcessorClient;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class PaymentServiceApplication {

  @Autowired
  private ServiceBusProcessorClient serviceBusProcessorClient;

  public static void main(String[] args) {
    SpringApplication.run(PaymentServiceApplication.class, args);
  }

  @PostConstruct
  public void startServiceBusProcessorClient() {

    serviceBusProcessorClient.start();
    System.out.println("Service Bus Processor Client started to listen for payment messages.");
  }
}
