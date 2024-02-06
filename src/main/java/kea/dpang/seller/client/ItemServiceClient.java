package kea.dpang.seller.client;

import kea.dpang.seller.base.BaseResponse;
import kea.dpang.seller.client.dto.DeleteItemDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient("item-server")
public interface ItemServiceClient {
    @DeleteMapping
    ResponseEntity<BaseResponse> deleteItem (@RequestBody DeleteItemDto requestDto);
}
