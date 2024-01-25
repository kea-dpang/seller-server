package kea.dpang.sellerserver.Entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

/**
 * 판매처 상세 Entity
 *
 * @author Tomas
 */
@Entity
@NoArgsConstructor
@Getter
public class SellerDetailEntity {

    /**
     * 판매처 상세 Entity의 ID
     */
    @Id
    @Column(name="id")
    private Long id;

    /**
     * 판매처 상세 Entity와 연관된 판매처 Entity
     */
    @OneToOne
    @MapsId
    @JoinColumn(name = "id", referencedColumnName = "id")
    private SellerEntity seller;

    /**
     * 해당 판매처를 관리하는 관리자
     */
    @Column(name = "seller_manager")
    private String sellerManager;

    /**
     * 계약 만료 날짜
     */
    @Column(name = "expiry_date")
    private LocalDate expiryDate;

    /**
     * 비고
     */
    @Column(name = "note")
    private String note;

    @Builder
    public SellerDetailEntity(SellerEntity seller, String sellerManager, LocalDate expiryDate, String note) {
        this.seller = seller;
        this.sellerManager = sellerManager;
        this.expiryDate = expiryDate;
        this.note = note;
    }

    /**
     * 판매처 상세 수정 메소드
     *
     * @param seller_manager 판매처 관리자
     * @param expiry_date 계약 만료 날짜
     * @param note 비고
     */
    public void updateSellerDetail(String seller_manager, LocalDate expiry_date, String note){
        this.sellerManager = seller_manager;
        this.expiryDate = expiry_date;
        this.note = note;
    }
}
