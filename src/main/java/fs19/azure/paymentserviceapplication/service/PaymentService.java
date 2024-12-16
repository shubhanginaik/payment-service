package fs19.azure.paymentserviceapplication.service;

import fs19.azure.paymentserviceapplication.dto.PaymentRequest;
import fs19.azure.paymentserviceapplication.dto.PaymentResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

@Service
public class PaymentService {

  private static final Logger LOGGER = LogManager.getLogger(PaymentService.class);
  public PaymentResponse processPayment(PaymentRequest request) {
    LOGGER.info("Received payment request for registration ID: {}", request.getRegistrationId());

    // Simulate payment processing logic
    boolean success = request.getAmount() > 0;

    LOGGER.info("Success: {}", success);
    String message = success ? "Payment successful" : "Payment failed";

    LOGGER.info("Message: {}", message);
    return new PaymentResponse(request.getRegistrationId(), success, message);
  }
}
