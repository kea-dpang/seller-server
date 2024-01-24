package kea.dpang.sellerserver.Entity;

import jakarta.persistence.*;

@Entity
public class SellerEntity {
    @Id
    @OneToOne(mappedBy = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private SellerDetailEntity id;
}
