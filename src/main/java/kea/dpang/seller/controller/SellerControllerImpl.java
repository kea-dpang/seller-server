package kea.dpang.seller.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import kea.dpang.seller.base.BaseResponse;
import kea.dpang.seller.base.SuccessResponse;
import kea.dpang.seller.dto.request.CreateSellerRequestDto;
import kea.dpang.seller.dto.request.UpdateSellerRequestDto;
import kea.dpang.seller.dto.response.SellerResponseDto;
import kea.dpang.seller.dto.response.DetailSellerResponseDto;
import kea.dpang.seller.service.SellerServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * seller 서비스의 컨트롤러
 *
 * @author Tomas
 */
@Tag(name = "Seller", description = "seller 서비스 api")
@RestController
@RequestMapping("/api/seller")
@RequiredArgsConstructor
public class SellerControllerImpl implements SellerController {

    private final SellerServiceImpl sellerService;

    @Override
    @GetMapping
    @Operation(summary = "판매처 목록 조회", description = "판매처를 페이지네이션하여 조회합니다.")
    public ResponseEntity<SuccessResponse<Page<SellerResponseDto>>> getSellerList(
            @RequestParam @Parameter(description = "판매처 이름 검색") Optional<String> sellerName,
            Pageable pageable
    ) {
        Page<SellerResponseDto> sellerDtoPage = sellerService.getSellerList(sellerName,pageable);
        return ResponseEntity.ok(new SuccessResponse<>(200, "판매처 목록 조회가 완료되었습니다.", sellerDtoPage));
    }

    @Override
    @GetMapping("/{id}")
    @Operation(summary = "특정 판매처 조회", description = "특정 판매처 정보를 조회합니다.")
    public ResponseEntity<SuccessResponse<DetailSellerResponseDto>> getSeller(
            @PathVariable @Parameter(description = "Seller ID") Long id
    ) {
        DetailSellerResponseDto seller = sellerService.getSeller(id);
        return ResponseEntity.ok(new SuccessResponse<>(200, "판매처 조회가 완료되었습니다.", seller));
    }

    @Override
    @GetMapping("/findName")
    @Operation(summary = "특정 판매처 이름 조회", description = "특정 판매처의 이름을 조회합니다.")
    public ResponseEntity<SuccessResponse<String>> getSellerName(
            @RequestParam @Parameter(description = "Seller ID") Long id
    ){
        String sellerName = sellerService.getSellerName(id);

        return ResponseEntity.ok(new SuccessResponse<>(200, "판매처 이름 조회가 완료되었습니다.", sellerName));
    }

    @Override
    @PostMapping
    @Operation(summary = "판매처 등록", description = "하나의 판매처를 데이터베이스에 등록합니다.")
    public ResponseEntity<BaseResponse> postSeller(
            @RequestBody @Parameter(description = "판매처 생성 정보") CreateSellerRequestDto createSellerRequestDto
    ) {
        sellerService.createSeller(createSellerRequestDto);
        return ResponseEntity.ok(new BaseResponse(201, "판매처 생성이 완료되었습니다."));
    }

    @Override
    @PutMapping("/{id}")
    @Operation(summary = "판매처 수정", description = "특정 판매처의 정보를 수정합니다.")
    public ResponseEntity<BaseResponse> updateSeller(
            @PathVariable @Parameter(description = "수정할 판매처 ID") Long id,
            @RequestBody @Parameter(description = "수정 내용") UpdateSellerRequestDto sellerUpdateDto
    ) {
        sellerService.updateSeller(id, sellerUpdateDto);
        return ResponseEntity.ok(new BaseResponse(200, "판매처 정보 수정이 완료되었습니다."));
    }

    @Override
    @DeleteMapping
    @Operation(summary = "판매처 삭제", description = "하나 혹은 복수의 판매처를 데이터베이스에서 삭제합니다.")
    public ResponseEntity<BaseResponse> deleteSellers(
            @RequestBody @Parameter(description = "삭제할 판매처 ID 리스트") List<Long> ids
    ) {
        sellerService.deleteSeller(ids);
        return ResponseEntity.ok(new BaseResponse(200, "판매처 삭제가 완료되었습니다."));
    }
}