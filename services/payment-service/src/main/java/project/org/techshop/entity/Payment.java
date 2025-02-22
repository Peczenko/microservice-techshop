package project.org.techshop.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private double totalPrice;
    @Enumerated(EnumType.STRING)
    private PaymentMethod paymentMethod;
    private Long orderId;
    @Enumerated(EnumType.STRING)
    private PaymentStatus paymentStatus;
    @OneToOne(cascade = CascadeType.ALL)
    private UserInfo userInfo;
}
