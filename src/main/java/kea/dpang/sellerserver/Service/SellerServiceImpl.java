package kea.dpang.sellerserver.Service;

import jakarta.persistence.EntityNotFoundException;
import kea.dpang.sellerserver.Dto.Request.SellerCreateDto;
import kea.dpang.sellerserver.Dto.Response.AllSellerGetDto;
import kea.dpang.sellerserver.Entity.SellerDetailEntity;
import kea.dpang.sellerserver.Entity.SellerEntity;
import kea.dpang.sellerserver.Repository.SellerDetailRepository;
import kea.dpang.sellerserver.Repository.SellerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class SellerServiceImpl implements SellerService {

    private final SellerDetailRepository sellerDetailRepository;
    private final SellerRepository sellerRepository;


    @Override
    public List<AllSellerGetDto> getAllSeller() {
        List<SellerEntity> sellerList = sellerRepository.findAll();
        List<AllSellerGetDto> sellerDtoList= new ArrayList<>();
        AllSellerGetDto sellerDto;
        for (SellerEntity seller:sellerList){
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
        Optional<SellerEntity> seller = sellerRepository.findById(id);
        if(seller.isEmpty()){
            throw new EntityNotFoundException("해당 판매처가 존재하지 않습니다.");
        }
        return SellerCreateDto.builder()
                .sellerStaff(seller.get().getSellerStaff())
                .expiryDate(seller.get().getSellerDetail().getExpiryDate())
                .manager(seller.get().getSellerDetail().getSellerManager())
                .name(seller.get().getName())
                .phone(seller.get().getPhoneNumber())
                .note(seller.get().getSellerDetail().getNote())
                .build();
    }

    @Override
    public void createSeller(SellerCreateDto requestDto) {
        SellerEntity seller = SellerEntity.builder()
                .name(requestDto.getName())
                .phoneNumber(requestDto.getPhone())
                .sellerStaff(requestDto.getSellerStaff())
                .build();
        sellerRepository.save(seller);
        sellerDetailRepository.save(SellerDetailEntity.builder()
                        .seller(seller)
                        .sellerManager(requestDto.getManager())
                        .expiryDate(requestDto.getExpiryDate())
                        .note(requestDto.getNote())
                        .build());
    }

    @Override
    public void updateSeller(Long id, SellerCreateDto requestDto) {

        Optional<SellerEntity> seller = sellerRepository.findById(id);
        if(seller.isEmpty()){
            throw new EntityNotFoundException("해당 판매처가 존재하지 않습니다.");
        }

        Optional<SellerDetailEntity> sellerDetail = sellerDetailRepository.findSellerDetailEntityBySeller(seller.get().updateSeller(requestDto.getPhone(), requestDto.getName(), requestDto.getSellerStaff()));
        if(sellerDetail.isEmpty()){
            throw new EntityNotFoundException("해당 판매처 상세 정보가 존재하지 않습니다.");
        }

        sellerDetail.get().updateSellerDetail(requestDto.getSellerStaff(), requestDto.getExpiryDate(), requestDto.getNote());
    }

    @Override
    public void deleteSeller(List<Long> ids) {
        sellerRepository.deleteAllById(ids);
    }
}
