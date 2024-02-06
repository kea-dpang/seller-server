package kea.dpang.seller.client.dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Builder
@Data
public class DeleteEventDto {
    private List<Long> sellerIds;
}
