package fs19.azure.paymentserviceapplication.config;

import com.azure.messaging.servicebus.ServiceBusClientBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;

@Configuration
@EnableScheduling
public class ServiceBusConfig {

  @Value("${azure.servicebus.connection-string}")
  private String connectionString;

  @Bean
  public ServiceBusClientBuilder serviceBusClientBuilder() {
    return new ServiceBusClientBuilder().connectionString(connectionString);
  }
}