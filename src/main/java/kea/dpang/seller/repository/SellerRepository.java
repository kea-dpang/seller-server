package kea.dpang.seller.repository;

import kea.dpang.seller.entity.Seller;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * 판매처 정보 repository
 *
 * @author Tomas
 */
public interface SellerRepository extends JpaRepository<Seller,Long> {
}
