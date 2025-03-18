package project.org.techshop.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import project.org.techshop.enitity.Order;
import project.org.techshop.enitity.OrderStatus;
import project.org.techshop.repository.OrderRepository;

import java.util.Random;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@Service
@Slf4j
@RequiredArgsConstructor
public class OrderStatusUpdater {

    private final OrderRepository orderRepository;
    private final ApplicationEventPublisher eventPublisher;
    private final ScheduledExecutorService scheduler = Executors.newSingleThreadScheduledExecutor();
    private final Random random = new Random();

    /**
     * Initiates the asynchronous status update for the given order.
     */
    public void startStatusUpdate(Order order) {
        scheduleNextUpdate(order);
    }

    private void scheduleNextUpdate(Order order) {
        // Calculate a random delay between 30 and 60 seconds.
        long delay = 10 + random.nextInt(11);
        scheduler.schedule(() -> updateStatus(order.getId()), delay, TimeUnit.SECONDS);
    }

    private void updateStatus(Long orderId) {
        // Retrieve the latest state of the order from the repository.
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Order not found"));

        OrderStatus currentStatus = order.getOrderStatus();
        OrderStatus nextStatus = getNextStatus(currentStatus);

        if (nextStatus != null) {
            order.setOrderStatus(nextStatus);
            orderRepository.save(order);
            log.info("Order {} updated to status {}", order.getId(), nextStatus);
            eventPublisher.publishEvent(new OrderStatusChangedEvent(this, order));
            // Schedule the next update.

            scheduleNextUpdate(order);
        } else {
            // If no next status is available, the order has reached a terminal state.
            log.info("Order {} has reached its final status: {}", order.getId(), currentStatus);
        }
    }

    /**
     * Determines the next order status based on the current status.
     */
    private OrderStatus getNextStatus(OrderStatus currentStatus) {
        return switch (currentStatus) {
            case PENDING -> OrderStatus.PROCESSING;
            case PROCESSING -> OrderStatus.SHIPPED;
            case SHIPPED -> OrderStatus.DELIVERED;
            case DELIVERED -> OrderStatus.COMPLETED;
            default -> null;
        };
    }
}

