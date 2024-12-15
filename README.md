# payment-service

* To ensure ordered message processing for the Event Registration workflow, where the following steps need to happen in sequence:
## Process Payment
## Update Event Participant Count
## Update Registration Status
## Send Email Confirmation
- We can use Azure Service Bus with Sessions and FIFO (First-In-First-Out) 
delivery to guarantee that these messages are processed in order. Each message in a session will be processed sequentially by a single consumer.

- Use Service Bus Sessions:
Each message sent to the Service Bus queue should have a Session ID. Messages with the same Session ID will be processed sequentially in the order they were sent.
- 
- Enable Sessions in the Queue:
When creating the Service Bus queue, enable Sessions. This ensures that messages with the same Session ID are grouped and processed in order.
- Single Consumer per Session:
- Only one consumer (Function App or Service Bus Worker) processes messages from a specific session at a time, ensuring FIFO order.
Auto-Complete Behavior:
- Ensure that messages are marked as complete only after they have been successfully processed to avoid re-delivery.
- Event Registration Controller (Sending Ordered Messages)
- Ensure all messages share the same Session ID (e.g., registrationId) to group related messages.
