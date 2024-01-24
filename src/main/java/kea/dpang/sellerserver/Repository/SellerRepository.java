package kea.dpang.sellerserver.Repository;

import kea.dpang.sellerserver.Entity.SellerEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SellerRepository extends JpaRepository<SellerEntity,Long> {
}
