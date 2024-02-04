package kea.dpang.seller.repository;

import kea.dpang.seller.entity.Seller;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

/**
 * 판매처 정보 repository
 *
 * @author Tomas
 */
public interface SellerRepository extends JpaRepository<Seller,Long> {
    @Query("SELECT DISTINCT s " +
            "FROM Seller s " +
            "WHERE 1=1 " +
            "AND (:sellerName IS null OR s.name LIKE concat('%',:sellerName,'%'))")
    Page<Seller> findAllBySellerName(
            @Param("sellerName") String sellerName,
            Pageable pageable
    );
}
