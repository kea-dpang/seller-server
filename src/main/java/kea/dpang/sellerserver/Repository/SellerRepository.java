package kea.dpang.sellerserver.Repository;

import kea.dpang.sellerserver.Entity.SellerEntity;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * 판매처 정보 repository
 *
 * @author Tomas
 */
public interface SellerRepository extends JpaRepository<SellerEntity,Long> {
}
