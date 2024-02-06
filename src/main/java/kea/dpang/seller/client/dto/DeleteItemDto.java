package kea.dpang.seller.client.dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Builder
@Data
public class DeleteItemDto {
    private List<Long> sellerIds;
}
