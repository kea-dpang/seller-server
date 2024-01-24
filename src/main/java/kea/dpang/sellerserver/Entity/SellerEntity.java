package kea.dpang.sellerserver.Entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@Getter
public class SellerEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(mappedBy = "seller", orphanRemoval = true)
    @PrimaryKeyJoinColumn
    private SellerDetailEntity sellerDetail;

    @Column(name = "seller_phone_number")
    private String phoneNumber;

    @Column(name = "name")
    private String name;

    @Column(name = "seller_staff")
    private String sellerStaff;

    @Builder
    public SellerEntity(String phoneNumber, String name, String sellerStaff) {
        this.phoneNumber = phoneNumber;
        this.name = name;
        this.sellerStaff = sellerStaff;
    }

    public SellerEntity updateSeller(String phone_number, String name, String seller_staff) {
        System.out.println("Seller Update");
        this.phoneNumber = phone_number;
        this.name = name;
        this.sellerStaff = seller_staff;
        return this;
    }
}
