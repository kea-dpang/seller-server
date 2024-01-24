package kea.dpang.sellerserver.Entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@NoArgsConstructor
@Getter
public class SellerDetailEntity {
    @Id
    @Column(name="id")
    private Long id;

    @OneToOne
    @MapsId
    @JoinColumn(name = "id", referencedColumnName = "id")
    private SellerEntity seller;

    @Column(name = "seller_manager")
    private String sellerManager;

    @Column(name = "expiry_date")
    private LocalDate expiryDate;

    @Column(name = "note")
    private String note;

    @Builder
    public SellerDetailEntity(SellerEntity seller, String sellerManager, LocalDate expiryDate, String note) {
        this.seller = seller;
        this.sellerManager = sellerManager;
        this.expiryDate = expiryDate;
        this.note = note;
    }

    public void updateSellerDetail(String seller_manager, LocalDate expiry_date, String note){
        System.out.println("Seller Detail Updaate");
        this.sellerManager = seller_manager;
        this.expiryDate = expiry_date;
        this.note = note;
    }
}
