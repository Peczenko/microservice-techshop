package project.org.techshop.email;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring6.SpringTemplateEngine;
import project.org.techshop.kafka.order.ProductServiceResponse;
import project.org.techshop.kafka.payment.PaymentMethod;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.nio.charset.StandardCharsets.UTF_8;
import static project.org.techshop.email.EmailTemplate.*;


@Service
@RequiredArgsConstructor
@Slf4j
public class EmailService {
    private final JavaMailSender javaMailSender;
    private final SpringTemplateEngine templateEngine;

    @Async
    public void sendOrderConfirmation(
            String to,
            String customerName,
            double totalPrice,
            Long orderId,
            List<ProductServiceResponse> products
    ) throws MessagingException {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage, MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED, UTF_8.name());
        messageHelper.setFrom("service@techshop.com");

        final String templateName = ORDER_CREATE_CONFIRMATION.getTemplate();

        Map<String, Object> variables = new HashMap<>();
        variables.put("customerName", customerName);
        variables.put("totalAmount", totalPrice);
        variables.put("orderReference", orderId);
        variables.put("products", products);

        Context context = new Context();
        context.setVariables(variables);
        messageHelper.setSubject(ORDER_CREATE_CONFIRMATION.getSubject());

        try {
            String htmlTemplate = templateEngine.process(templateName, context);
            messageHelper.setText(htmlTemplate, true);

            messageHelper.setTo(to);
            javaMailSender.send(mimeMessage);
            log.info(String.format("INFO - Email successfully sent to %s with template %s ", to, templateName));
        } catch (MessagingException e) {
            log.warn("WARNING - Cannot send Email to {} ", to);
        }

    }

    @Async
    public void sendPaymentConfirmation(
            String to,
            String customerName,
            double totalPrice,
            Long orderId,
            PaymentMethod paymentMethod,
            boolean paymentSuccess
    ) throws MessagingException {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper messageHelper = new MimeMessageHelper(
                mimeMessage,
                MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED,
                UTF_8.name()
        );
        messageHelper.setFrom("service@techshop.com");

        final String templateName = paymentSuccess
                ? PAYMENT_SUCCESS_CONFIRMATION.getTemplate()
                : PAYMENT_FAILURE_CONFIRMATION.getTemplate();
        final String subject = paymentSuccess
                ? PAYMENT_SUCCESS_CONFIRMATION.getSubject()
                : PAYMENT_FAILURE_CONFIRMATION.getSubject();

        Map<String, Object> variables = new HashMap<>();
        variables.put("customerName", customerName);
        variables.put("totalAmount", totalPrice);
        variables.put("orderReference", orderId);
        variables.put("paymentMethod", paymentMethod);

        Context context = new Context();
        context.setVariables(variables);

        messageHelper.setSubject(subject);

        try {
            String htmlTemplate = templateEngine.process(templateName, context);
            messageHelper.setText(htmlTemplate, true);
            messageHelper.setTo(to);
            javaMailSender.send(mimeMessage);
            log.info("INFO - Payment confirmation email successfully sent to {} using template {}", to, templateName);
        } catch (MessagingException e) {
            log.warn("WARNING - Cannot send Payment Email to {}", to);
        }
    }

}
