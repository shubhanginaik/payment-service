package fs19.azure.paymentserviceapplication.service;

import fs19.azure.paymentserviceapplication.dto.PaymentRequest;
import fs19.azure.paymentserviceapplication.dto.PaymentResponse;
import org.springframework.stereotype.Service;

@Service
public class PaymentService {

  public PaymentResponse processPayment(PaymentRequest request) {
    // Simulate payment processing logic
    boolean success = request.getAmount() > 0;
    String message = success ? "Payment successful" : "Payment failed";

    return new PaymentResponse(request.getRegistrationId(), success, message);
  }
}
