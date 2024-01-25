package kea.dpang.seller.exception;

public class SellerNotFoundException extends RuntimeException {
    public SellerNotFoundException(Long id) {
        super("해당 ID를 가진 판매처가 존재하지 않습니다: " + id);
    }
}
