package kea.dpang.sellerserver.Dto.Request;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

/**
 * 판매처 등록, 수정, 조회에 사용되는 DTO
 *
 * @author Tomas
 */
@NoArgsConstructor
@Getter
public class SellerCreateDto {

    /**
     * 판매처 이름
     */
    private String name;

    /**
     * 판매처 전화번호
     */
    private String phone;

    /**
     * 판매처 직원 이름
     */
    private String sellerStaff;

    /**
     * 판매처 담당 관리자
     */
    private String manager;

    /**
     * 계약 만료 날짜
     */
    private LocalDate expiryDate;

    /**
     * 비고
     */
    private String note;

    @Builder
    public SellerCreateDto(String name, String phone, String sellerStaff, String manager, LocalDate expiryDate, String note) {
        this.name = name;
        this.phone = phone;
        this.sellerStaff = sellerStaff;
        this.manager = manager;
        this.expiryDate = expiryDate;
        this.note = note;
    }
}