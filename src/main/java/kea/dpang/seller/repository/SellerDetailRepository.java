package kea.dpang.seller.repository;

import kea.dpang.seller.entity.SellerDetail;
import kea.dpang.seller.entity.Seller;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * 판매처 상세 정보 repository
 *
 * @author Tomas
 */
public interface SellerDetailRepository extends JpaRepository<SellerDetail, Seller> {
    /**
     * 판매처 Entity로 판매처 상세 Entity를 찾는 메소드
     *
     * @param seller 판매처 Entity
     * @return 판매처 Entity와 연관된 판매처 상세 Entity
     */
    Optional<SellerDetail> findSellerDetailEntityBySeller(Seller seller);
}
