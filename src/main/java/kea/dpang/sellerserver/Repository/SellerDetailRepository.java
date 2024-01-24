package kea.dpang.sellerserver.Repository;

import kea.dpang.sellerserver.Entity.SellerDetailEntity;
import kea.dpang.sellerserver.Entity.SellerEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SellerDetailRepository extends JpaRepository<SellerDetailEntity, SellerEntity> {
    Optional<SellerDetailEntity> findSellerDetailEntityBySeller(SellerEntity seller);
}
