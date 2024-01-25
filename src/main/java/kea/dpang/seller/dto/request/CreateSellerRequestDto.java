package kea.dpang.seller.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

/**
 * 판매처 등록, 조회에 사용되는 DTO
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateSellerRequestDto {
    private String name; // 판매처 이름
    private String phone; // 판매처 전화번호
    private String sellerStaff; // 판매처 직원 이름
    private String manager; // 판매처 담당 관리자
    private LocalDate expiryDate; // 계약 만료 날짜
    private String note; // 비고
}
