package kea.dpang.sellerserver.exception;

public class SellerDetailNotFoundException extends RuntimeException{
    public SellerDetailNotFoundException(Long id) {super("해당 판매처의 상세 정보가 존재하지 않습니다: " + id);}
}
