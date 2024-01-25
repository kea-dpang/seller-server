package kea.dpang.seller.service;

import kea.dpang.seller.dto.request.CreateSellerRequestDto;
import kea.dpang.seller.dto.request.UpdateSellerRequestDto;
import kea.dpang.seller.dto.response.SellerResponseDto;
import kea.dpang.seller.dto.response.DetailSellerResponseDto;
import kea.dpang.seller.entity.Seller;
import kea.dpang.seller.entity.SellerDetail;
import kea.dpang.seller.exception.SellerDetailNotFoundException;
import kea.dpang.seller.exception.SellerNotFoundException;
import kea.dpang.seller.repository.SellerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class SellerServiceImpl implements SellerService {

    private final SellerRepository sellerRepository;

    @Override
    @Transactional(readOnly = true)
    public Page<SellerResponseDto> getSellerList(Pageable pageable) {
        // 데이터베이스에서 pageable에 따른 Seller 객체들을 페이지로 가져온다.
        Page<Seller> sellerPage = sellerRepository.findAll(pageable);

        // 가져온 Seller 페이지의 각 Seller 객체를 SellerResponseDto로 변환한다.
        return sellerPage.map(SellerResponseDto::new);
    }

    @Override
    @Transactional(readOnly = true)
    public DetailSellerResponseDto getSeller(Long sellerId) {
        // 데이터베이스에서 ID에 해당하는 Seller 객체를 가져온다.
        Seller seller = sellerRepository.findById(sellerId)
                .orElseThrow(() -> new SellerNotFoundException(sellerId));

        // 가져온 Seller 정보를 response할 DTO로 변환 및 반환한다.
        return new DetailSellerResponseDto(seller);
    }

    @Override
    public void createSeller(CreateSellerRequestDto dto) {
        // requestDto의 정보를 이용해서 새로운 Seller 객체를 생성한다.
        // 연관된 SellerDetail 객체도 함께 생성되고, cascade 옵션으로 인해
        // Seller 객체가 영속화될 때 SellerDetail 객체도 함께 영속화된다.
        Seller seller = Seller.createSellerEntityFromDto(dto);

        // 생성한 Seller 객체를 데이터베이스에 저장한다.
        // SellerDetail 객체는 cascade 옵션으로 인해 자동으로 저장된다.
        sellerRepository.save(seller);
    }

    @Override
    public void updateSeller(Long sellerId, UpdateSellerRequestDto dto) {
        // 판매처 ID를 이용하여 해당 판매처를 찾아낸다.
        // 해당 판매처를 찾지 못하면 'SellerNotFoundException'을 발생시킨다.
        Seller seller = sellerRepository.findById(sellerId)
                .orElseThrow(() -> new SellerNotFoundException(sellerId));

        // 찾아낸 판매처의 정보를 DTO에 담긴 정보로 갱신한다.
        seller.update(dto.getPhone(), dto.getName(), dto.getSellerStaff());

        // 판매처의 상세 정보를 얻어온다.
        // 만약 판매처의 상세 정보를 찾지 못하면 'SellerDetailNotFoundException'을 발생시킨다.
        SellerDetail sellerDetail = seller.getSellerDetail();
        if (sellerDetail == null) {
            throw new SellerDetailNotFoundException(sellerId);
        }

        // 상세 정보를 DTO에 담긴 정보로 갱신합니다.
        sellerDetail.update(dto.getManager(), dto.getExpiryDate(), dto.getNote());
    }

    @Override
    public void deleteSeller(List<Long> sellerIds) {
        // 삭제하려는 판매처들이 모두 존재하는지 확인한다.
        // 만약 존재하지 않는 판매처가 있다면 'SellerNotFoundException'을 발생시킨다.
        sellerIds.forEach(id -> {
            if (!sellerRepository.existsById(id)) {
                throw new SellerNotFoundException(id);
            }
        });

        // 모든 판매처가 존재한다면, 해당 판매처들을 모두 삭제한다.
        sellerRepository.deleteAllById(sellerIds);
    }

}