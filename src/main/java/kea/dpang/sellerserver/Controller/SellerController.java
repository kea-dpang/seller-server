package kea.dpang.sellerserver.Controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.persistence.EntityNotFoundException;
import jakarta.websocket.server.PathParam;
import kea.dpang.sellerserver.Dto.Request.SellerCreateDto;
import kea.dpang.sellerserver.Dto.Response.AllSellerGetDto;
import kea.dpang.sellerserver.Service.SellerServiceImpl;
import kea.dpang.sellerserver.base.BaseResponse;
import kea.dpang.sellerserver.base.SuccessResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * seller 서비스의 컨트롤러
 * @author Tomas
 */
@Tag(name="Seller",description = "seller 서비스 api")
@RestController
@RequestMapping("/seller")
@RequiredArgsConstructor
public class SellerController {

    private final SellerServiceImpl sellerService;

    /**
     * API
     * GET : 판매처를 페이지네이션하여 조회합니다.
     * @return 응답 코드, 판매처 정보를 담은 DTO 페이지
     */
    @GetMapping
    @Operation(summary = "판매처 목록 조회", description = "판매처를 페이지네이션하여 조회합니다.")
    public ResponseEntity<SuccessResponse<Page<AllSellerGetDto>>> getSellerList(
            Pageable pageable
    ){
        Page<AllSellerGetDto> sellerDtoPage = sellerService.getSellerList(pageable);
        return ResponseEntity.ok(new SuccessResponse<>(200,"판매처 목록 조회가 완료되었습니다.", sellerDtoPage));
    }

    /**
     * @deprecated getSellerList로 대체됨
     * API
     * GET : 모든 판매처들을 조회합니다.
     * @return 성공 : 모든 판매처 정보 리스트, 200 OK
     */
//    @GetMapping
    @Operation(summary = "모든 판매처 조회", description = "모든 판매처를 조회합니다.")
    public ResponseEntity<List<AllSellerGetDto>> getAllSeller(){
        return new ResponseEntity<>(sellerService.getAllSeller(),HttpStatus.OK);
    }

    /**
     * API
     * GET : 특정 판매처 정보를 조회합니다.
     * @param id 조회할 판매처의 id
     * @return 응답 코드, 판매처 ID에 해당하는 판매처 정보
     */
    @GetMapping("/{id}")
    @Operation(summary = "특정 판매처 조회", description = "특정 판매처 정보를 조회합니다.")
    public ResponseEntity<SuccessResponse<SellerCreateDto>> getSeller(
            @PathVariable @Parameter(description = "Seller ID") Long id
    ){
        SellerCreateDto seller = sellerService.getSeller(id);
        return ResponseEntity.ok(new SuccessResponse<>(200, "판매처 조회가 완료되었습니다.",seller));
    }

    /**
     * API
     * POST : 하나의 판매처를 데이터베이스에 등록합니다.
     * @param sellerCreateDto 등록할 판매처의 정보가 담긴 DTO
     * @return 응답 코드
     */
    @PostMapping
    @Operation(summary = "판매처 등록", description = "하나의 판매처를 데이터베이스에 등록합니다.")
    public ResponseEntity<BaseResponse> postSeller(
            @RequestBody @Parameter(description = "판매처 생성 정보") SellerCreateDto sellerCreateDto
    ){
        sellerService.createSeller(sellerCreateDto);
        return ResponseEntity.ok(new BaseResponse(201,"판매처 생성이 완료되었습니다."));
    }

    /**
     * API
     * PUT : 특정 판매처의 정보를 수정합니다.
     * @param id 수정할 판매처의 ID
     * @param sellerUpdateDto 수정할 내용이 담긴 DTO
     * @return 응답 코드
     */
    @PutMapping("/{id}")
    @Operation(summary = "판매처 수정", description = "특정 판매처의 정보를 수정합니다.")
    public ResponseEntity<BaseResponse> updateSeller(
            @PathVariable @Parameter(description = "수정할 판매처 ID") Long id,
            @RequestBody @Parameter(description = "수정 내용") SellerCreateDto sellerUpdateDto
    ){
        sellerService.updateSeller(id,sellerUpdateDto);
        return ResponseEntity.ok(new BaseResponse(200,"판매처 정보 수정이 완료되었습니다."));
    }

    /**
     * API
     * DELETE : 하나 혹은 복수의 판매처를 데이터베이스에서 삭제합니다.
     * @param ids 삭제할 판매처들의 ID List
     * @return 성공 : 응답 코드
     */
    @DeleteMapping
    @Operation(summary = "판매처 삭제", description = "하나 혹은 복수의 판매처를 데이터베이스에서 삭제합니다.")
    public ResponseEntity<BaseResponse> deleteSellers(
            @RequestBody @Parameter(description = "삭제할 판매처 ID 리스트") List<Long> ids
    ){
        sellerService.deleteSeller(ids);
        return ResponseEntity.ok(new BaseResponse(200,"판매처 삭제가 완료되었습니다."));
    }
}