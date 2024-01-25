package kea.dpang.seller.entity;

import jakarta.persistence.*;
import kea.dpang.seller.dto.request.CreateSellerRequestDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * 판매처를 나타내는 엔티티
 */
@Getter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Seller {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(mappedBy = "seller", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    @PrimaryKeyJoinColumn
    private SellerDetail sellerDetail;

    @Column(name = "seller_phone_number")
    private String phoneNumber; // 판매처 전화번호

    @Column(name = "seller_name")
    private String name; // 판매처 이름

    @Column(name = "seller_staff")
    private String sellerStaff; // 판매처 직원 이름

    /**
     * 판매처의 정보를 갱신합니다.
     *
     * @param phoneNumber 새로운 전화번호
     * @param name        새로운 판매처 이름
     * @param sellerStaff 새로운 직원 이름
     */
    public void update(String phoneNumber, String name, String sellerStaff) {
        this.phoneNumber = phoneNumber;
        this.name = name;
        this.sellerStaff = sellerStaff;
    }

    /**
     * CreateSellerDto 객체를 기반으로 Seller 엔티티를 생성합니다.
     * 연관된 SellerDetail 엔티티도 함께 생성되고, cascade 옵션으로 인해
     * Seller 엔티티가 영속화될 때 SellerDetail 엔티티도 영속화됩니다.
     *
     * @param dto 판매처 생성에 필요한 데이터를 담은 DTO
     * @return Seller 새로 생성된 Seller 엔티티
     */
    public static Seller createSellerEntityFromDto(CreateSellerRequestDto dto) {
        Seller seller = Seller.builder()
                .phoneNumber(dto.getPhone())
                .name(dto.getName())
                .sellerStaff(dto.getSellerStaff())
                .build();

        SellerDetail detail = SellerDetail.builder()
                .seller(seller)
                .sellerManager(dto.getManager())
                .expiryDate(dto.getExpiryDate())
                .note(dto.getNote())
                .build();

        seller.updateSellerDetail(detail);

        return seller;
    }

    /**
     * 판매처 상세 정보를 갱신합니다.
     *
     * @param detail 연관시킬 SellerDetail 엔티티
     */
    private void updateSellerDetail(SellerDetail detail) {
        this.sellerDetail = detail;
    }
}
