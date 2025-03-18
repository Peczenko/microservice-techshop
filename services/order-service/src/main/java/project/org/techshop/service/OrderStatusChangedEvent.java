package project.org.techshop.service;

import lombok.Getter;
import org.springframework.context.ApplicationEvent;
import project.org.techshop.enitity.Order;

@Getter
public class OrderStatusChangedEvent extends ApplicationEvent {
    private final Order order;

    public OrderStatusChangedEvent(Object source, Order order) {
        super(source);
        this.order = order;
    }
}

