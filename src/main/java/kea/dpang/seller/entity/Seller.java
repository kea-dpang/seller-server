package kea.dpang.seller.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * 판매처 Entity
 *
 * @author Tomas
 */
@Entity
@NoArgsConstructor
@Getter
public class Seller {

    /**
     * 판매처 Entity의 ID
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 판매처 Entity와 연관된 판매처 상세 Entity
     */
    @OneToOne(mappedBy = "seller", orphanRemoval = true)
    @PrimaryKeyJoinColumn
    private SellerDetail sellerDetail;

    /**
     * 판매처 전화번호
     */
    @Column(name = "seller_phone_number")
    private String phoneNumber;

    /**
     * 판매처 이름
     */
    @Column(name = "name")
    private String name;

    /**
     * 판매처 직원 이름
     */
    @Column(name = "seller_staff")
    private String sellerStaff;

    @Builder
    public Seller(String phoneNumber, String name, String sellerStaff) {
        this.phoneNumber = phoneNumber;
        this.name = name;
        this.sellerStaff = sellerStaff;
    }

    /**
     * 판매처 정보 수정 메소드
     *
     * @param phone_number 판매처 전화번호
     * @param name 판매처 이름
     * @param seller_staff 판매처 직원
     * @return 판매처 Entity<br>
     * 판매처 상세 repository에서 판매처 상세 Entity를 찾을 때 사용됩니다.
     */
    public Seller updateSeller(String phone_number, String name, String seller_staff) {
        this.phoneNumber = phone_number;
        this.name = name;
        this.sellerStaff = seller_staff;
        return this;
    }
}
