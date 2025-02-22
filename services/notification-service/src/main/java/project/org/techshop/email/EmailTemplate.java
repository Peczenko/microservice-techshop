package project.org.techshop.email;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum EmailTemplate {
    PAYMENT_SUCCESS_CONFIRMATION("payment-success-confirmation.html", "Payment Confirmation"),
    PAYMENT_FAILURE_CONFIRMATION("payment-failed-confirmation.html", "Payment Confirmation"),
    ORDER_CREATE_CONFIRMATION("order-create-confirmation.html", "Order creation confirmation"),
    ORDER_PAYED_CONFIRMATION("order-create-confirmation.html", "Order payed confirmation");

    private final String template;
    private final String subject;

}
