package fs19.azure.paymentserviceapplication.controller;

import fs19.azure.paymentserviceapplication.dto.PaymentRequest;
import fs19.azure.paymentserviceapplication.dto.PaymentResponse;
import fs19.azure.paymentserviceapplication.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/payments")
public class PaymentController {

  @Autowired
  private PaymentService paymentService;

  public PaymentResponse processPayment(@RequestBody PaymentRequest request) {
    return paymentService.processPayment(request);
  }
}
