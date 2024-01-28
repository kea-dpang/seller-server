package kea.dpang.seller.entity;

import jakarta.persistence.*;
import kea.dpang.seller.dto.request.UpdateSellerRequestDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

/**
 * 판매처 상세 Entity
 */
@Getter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SellerDetail {

    @Id
    @Column(name = "seller_detail_id")
    private Long id; // Seller 엔티티와 공유하는 ID, 별도의 GeneratedValue 어노테이션은 필요하지 않음.

    @OneToOne
    @MapsId // ID를 Seller 엔티티와 공유함을 명시.
    @JoinColumn(name = "id", referencedColumnName = "id")
    private Seller seller;

    @Column(name = "seller_manager")
    private String sellerManager; // 해당 판매처를 관리하는 관리자

    @Column(name = "expiry_date")
    private LocalDate expiryDate; // 계약 만료 날짜

    @Column(name = "note")
    private String note; // 비고

    /**
     * 판매처 상세 정보를 수정합니다.
     *
     * @param dto 판매처 상세 정보 수정 내용을 가지는 DTO
     */
    public void update(UpdateSellerRequestDto dto) {
        this.sellerManager = dto.getManager();;
        this.expiryDate = dto.getExpiryDate();
        this.note = dto.getNote();
    }
}
