package fs19.azure.paymentserviceapplication.dto;

import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PaymentRequest {
  private UUID registrationId;
  private UUID userId;
  private UUID eventId;
  private double amount;
  private String currency;
  private String paymentMethod;
}
