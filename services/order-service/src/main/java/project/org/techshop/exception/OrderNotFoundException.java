package project.org.techshop.exception;


import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class OrderNotFoundException extends RuntimeException{
    private final String message;
}
