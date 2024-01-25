package kea.dpang.seller.service;

import kea.dpang.seller.dto.request.SellerCreateDto;
import kea.dpang.seller.dto.response.AllSellerGetDto;
import kea.dpang.seller.entity.SellerDetail;
import kea.dpang.seller.entity.Seller;
import kea.dpang.seller.repository.SellerDetailRepository;
import kea.dpang.seller.repository.SellerRepository;
import kea.dpang.seller.exception.SellerDetailNotFoundException;
import kea.dpang.seller.exception.SellerNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class SellerServiceImpl implements SellerService {

    private final SellerDetailRepository sellerDetailRepository;
    private final SellerRepository sellerRepository;

    @Override
    public Page<AllSellerGetDto> getSellerList(Pageable pageable) {
        Page<Seller> sellerPage = sellerRepository.findAll(pageable);
        return sellerPage.map((seller) ->
                AllSellerGetDto.builder()
                        .id(seller.getId())
                        .name(seller.getName())
                        .manager(seller.getSellerDetail().getSellerManager())
                        .sellerStaff(seller.getSellerStaff())
                        .expiryDate(seller.getSellerDetail().getExpiryDate())
                        .phone(seller.getPhoneNumber())
                        .build()
        );
    }

    @Override
    public List<AllSellerGetDto> getAllSeller() {
        List<Seller> sellerList = sellerRepository.findAll();
        List<AllSellerGetDto> sellerDtoList = new ArrayList<>();
        AllSellerGetDto sellerDto;
        for (Seller seller : sellerList) {
            sellerDto = AllSellerGetDto.builder()
                    .id(seller.getId())
                    .name(seller.getName())
                    .manager(seller.getSellerDetail().getSellerManager())
                    .sellerStaff(seller.getSellerStaff())
                    .expiryDate(seller.getSellerDetail().getExpiryDate())
                    .phone(seller.getPhoneNumber())
                    .build();
            sellerDtoList.add(sellerDto);
        }
        return sellerDtoList;
    }

    @Override
    public SellerCreateDto getSeller(Long id) {

        // 데이터베이스에서 ID에 해당하는 Seller 객체를 가져온다.
        Seller seller = sellerRepository.findById(id)
                .orElseThrow(() -> new SellerNotFoundException(id));

        // 가져온 Seller 정보를 response할 DTO에 저장한다.
        return SellerCreateDto.builder()
                .sellerStaff(seller.getSellerStaff())
                .expiryDate(seller.getSellerDetail().getExpiryDate())
                .manager(seller.getSellerDetail().getSellerManager())
                .name(seller.getName())
                .phone(seller.getPhoneNumber())
                .note(seller.getSellerDetail().getNote())
                .build();
    }

    @Override
    public void createSeller(SellerCreateDto requestDto) {

        // request의 정보를 이용해서 새로운 Seller 객체를 생성한다.
        Seller seller = Seller.builder()
                .name(requestDto.getName())
                .phoneNumber(requestDto.getPhone())
                .sellerStaff(requestDto.getSellerStaff())
                .build();

        // 생성한 Seller 객체를 데이터베이스에 저장한다.
        sellerRepository.save(seller);

        // 생성한 Seller 객체와 연관되는 Seller Detail 객체를 생성하여 저장한다.
        sellerDetailRepository.save(SellerDetail.builder()
                .seller(seller)
                .sellerManager(requestDto.getManager())
                .expiryDate(requestDto.getExpiryDate())
                .note(requestDto.getNote())
                .build());
    }

    @Override
    public void updateSeller(Long id, SellerCreateDto requestDto) {

        // 데이터베이스에서 ID에 해당하는 Seller 객체를 가져온다.
        Seller seller = sellerRepository.findById(id)
                .orElseThrow(() -> new SellerNotFoundException(id));

        // 가져온 Seller 객체와 연관된 Seller Detail 객체를 가져온다.
        SellerDetail sellerDetail = sellerDetailRepository.findSellerDetailEntityBySeller(
                        // 가져온 Seller 객체의 정보를 request의 정보로 업데이트한다.
                        seller.updateSeller(requestDto.getPhone(), requestDto.getName(), requestDto.getSellerStaff()))
                .orElseThrow(() -> new SellerDetailNotFoundException(id));

        // 가져온 Seller Detail 객체의 정보를 request의 정보로 업데이트한다.
        sellerDetail.updateSellerDetail(requestDto.getSellerStaff(), requestDto.getExpiryDate(), requestDto.getNote());
    }

    @Override
    public void deleteSeller(List<Long> ids) {
        // 요청받은 ID 리스트를 순회하면서 각각의 Seller를 삭제한다.
        // Seller를 삭제하면 이와 연관된 Seller Detail은 자동으로 삭제된다.(SellerEntity 참고)
        for (Long id : ids) {

            // 데이터베이스에서 ID에 해당하는 Seller 객체를 조회한다.
            Seller seller = sellerRepository.findById(id)
                    .orElseThrow(() -> new SellerNotFoundException(id));

            // 조회한 Seller를 데이터베이스에서 삭제한다.
            sellerRepository.deleteAllById(ids);
        }
    }
}