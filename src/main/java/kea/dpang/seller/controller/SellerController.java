package kea.dpang.seller.controller;

import kea.dpang.seller.base.BaseResponse;
import kea.dpang.seller.base.SuccessResponse;
import kea.dpang.seller.dto.request.CreateSellerRequestDto;
import kea.dpang.seller.dto.request.UpdateSellerRequestDto;
import kea.dpang.seller.dto.response.SellerResponseDto;
import kea.dpang.seller.dto.response.DetailSellerResponseDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

public interface SellerController {

    /**
     * 판매처 목록을 조회합니다.
     *
     * @param pageable 페이지 정보
     * @return 응답 코드와 판매처 정보를 담은 페이지 DTO
     */
    ResponseEntity<SuccessResponse<Page<SellerResponseDto>>> getSellerList(Optional<String> sellerName, Pageable pageable);

    /**
     * 특정 판매처 정보를 조회합니다.
     *
     * @param id 조회할 판매처의 ID
     * @return 응답 코드와 해당 판매처 정보를 담은 DTO
     */
    ResponseEntity<SuccessResponse<DetailSellerResponseDto>> getSeller(Long id);

    /**
     * 특정 판매처의 이름을 조회합니다.
     * @param id 조회할 판매처의 ID
     * @return 응답 코드, 해당 판매처의 이름
     */
    ResponseEntity<SuccessResponse<String>> getSellerName(Long id);

    /**
     * 판매처를 데이터베이스에 등록합니다.
     *
     * @param createSellerRequestDto 등록할 판매처 정보가 담긴 DTO
     * @return 응답 코드
     */
    ResponseEntity<BaseResponse> postSeller(CreateSellerRequestDto createSellerRequestDto);

    /**
     * 특정 판매처의 정보를 수정합니다.
     *
     * @param id              수정할 판매처의 ID
     * @param sellerUpdateDto 수정할 내용이 담긴 DTO
     * @return 응답 코드
     */
    ResponseEntity<BaseResponse> updateSeller(Long id, UpdateSellerRequestDto sellerUpdateDto);

    /**
     * 하나 혹은 여러 판매처를 데이터베이스에서 삭제합니다.
     *
     * @param ids 삭제할 판매처들의 ID 목록
     * @return 응답 코드 (성공 시)
     */
    ResponseEntity<BaseResponse> deleteSellers(List<Long> ids);
}