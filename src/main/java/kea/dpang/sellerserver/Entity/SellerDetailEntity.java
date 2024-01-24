package kea.dpang.sellerserver.Entity;

import jakarta.persistence.*;

@Entity
public class SellerDetailEntity {
    @Id
    @OneToOne
    @JoinColumn(name="id")
    private SellerEntity id;
}
