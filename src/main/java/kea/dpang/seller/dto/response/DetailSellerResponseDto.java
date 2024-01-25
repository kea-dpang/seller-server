package kea.dpang.seller.dto.response;

import kea.dpang.seller.entity.Seller;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DetailSellerResponseDto {
    private String name; // 판매처 이름
    private String phone; // 판매처 전화번호
    private String sellerStaff; // 판매처 직원 이름
    private String manager; // 판매처 담당 관리자
    private LocalDate expiryDate; // 계약 만료 날짜
    private String note; // 비고

    public DetailSellerResponseDto(Seller seller) {
        this.name = seller.getName();
        this.phone = seller.getPhoneNumber();
        this.sellerStaff = seller.getSellerStaff();
        this.manager = seller.getSellerDetail().getSellerManager();
        this.expiryDate = seller.getSellerDetail().getExpiryDate();
        this.note = seller.getSellerDetail().getNote();
    }
}
