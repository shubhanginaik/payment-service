package fs19.azure.paymentserviceapplication.controller;

import fs19.azure.paymentserviceapplication.dto.PaymentRequest;
import fs19.azure.paymentserviceapplication.dto.PaymentResponse;
import fs19.azure.paymentserviceapplication.service.PaymentService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/payments")
public class PaymentController {

  private final PaymentService paymentService;

  public PaymentController(PaymentService paymentService) {
    this.paymentService = paymentService;
  }

  @PostMapping
  public PaymentResponse processPayment(@RequestBody PaymentRequest request) {
    System.out.println("Received payment request for registration ID: " + request.getRegistrationId());
    return paymentService.processPayment(request);
  }
}
