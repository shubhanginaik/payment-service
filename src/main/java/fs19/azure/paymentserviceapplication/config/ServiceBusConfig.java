package fs19.azure.paymentserviceapplication.config;

import com.azure.messaging.servicebus.ServiceBusClientBuilder;
import com.azure.messaging.servicebus.ServiceBusProcessorClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ServiceBusConfig {

  @Value("${azure.servicebus.connection-string}")
  private String connectionString;

  @Value("${azure.servicebus.queue-name}")
  private String paymentQueueName;

  @Bean
  public ServiceBusProcessorClient serviceBusProcessorClient() {
    return new ServiceBusClientBuilder()
        .connectionString(connectionString)
        .processor()
        .queueName(paymentQueueName)
        .processMessage(context -> {
          // Process the payment message
          String messageBody = context.getMessage().getBody().toString();
          System.out.println("Processing payment message: " + messageBody);

          // Add your message processing logic here
        })
        .processError(context -> {
          System.err.println("Error processing message: " + context.getException().getMessage());
        })
        .buildProcessorClient();
  }
}
