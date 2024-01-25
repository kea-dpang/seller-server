package kea.dpang.sellerserver.Service;

import kea.dpang.sellerserver.Dto.Request.SellerCreateDto;
import kea.dpang.sellerserver.Dto.Response.AllSellerGetDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

/**
 * Seller 서비스 인터페이스
 * @author Tomas
 */
public interface SellerService {

    /**
     * 판매처를 페이지네이션하여 조회합니다. 모든 판매처를 조회합니다.
     *
     * @param pageable 페이지네이션 정보
     * @return 페이지네이션된 QnA 정보
     */
    Page<AllSellerGetDto> getSellerList(Pageable pageable);

    /**
     * @deprecated getSellerList로 대체되었습니다.
     * 데이터베이스에 있는 모든 판매처들의 정보를 조회합니다.
     *
     * @return 조회된 판매처의 정보가 담긴 DTO List
     */
    List<AllSellerGetDto> getAllSeller();

    /**
     * 주어진 ID에 해당하는 판매처의 정보를 조회합니다.
     * 판매처 수정에서만 사용되고, 판매처 수정은 판매처 등록과 같은 입력폼을 가지므로 판매처 등록과 동일한 return type을 가집니다.
     *
     * @param id 조회할 판매처의 ID
     * @return 조회된 판매처의 상세 정보가 담긴 Detail DTO
     */
    SellerCreateDto getSeller(Long id);

    /**
     * 새로운 판매처를 등록합니다.
     *
     * @param requestDto 등록할 판매처의 정보가 담긴 DTO
     */
    void createSeller(SellerCreateDto requestDto);


    /**
     * 등록된 판매처의 정보를 수정합니다.
     *
     * @param id 수정할 판매처의 ID
     * @param requestDto 수정할 정보가 담긴 DTO
     */
    void updateSeller(Long id, SellerCreateDto requestDto);

    /**
     * 등록된 판매처를 데이터베이스에서 삭제합니다.
     * 복수의 판매처를 삭제할 수 있습니다.
     *
     * @param ids 삭제할 판매처의 ID List
     */
    void deleteSeller(List<Long> ids);
}
