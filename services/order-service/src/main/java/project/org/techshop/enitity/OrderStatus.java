package project.org.techshop.enitity;

public enum OrderStatus {
    NEW,          // Order is newly created
    PENDING,      // Awaiting confirmation or payment
    PROCESSING,   // Order is being processed
    SHIPPED,      // Order has been shipped
    DELIVERED,    // Order has been delivered
    CANCELLED,    // Order has been cancelled
    RETURNED,     // Order has been returned
    REFUNDED,     // Payment has been refunded
    COMPLETED     // Order has been completed
}
