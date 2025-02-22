package project.org.techshop.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PhotoResponse {
    private Long id;
    private byte[] data;
    private int display_order;
}
