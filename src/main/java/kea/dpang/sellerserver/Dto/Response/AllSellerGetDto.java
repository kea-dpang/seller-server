package kea.dpang.sellerserver.Dto.Response;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@NoArgsConstructor
@Getter
public class AllSellerGetDto {

    private Long id;
    private String name;
    private String phone;
    private String sellerStaff;
    private String manager;
    private LocalDate expiryDate;

    @Builder
    public AllSellerGetDto(Long id, String name, String phone, String sellerStaff, String manager, LocalDate expiryDate) {
        this.id = id;
        this.name = name;
        this.phone = phone;
        this.sellerStaff = sellerStaff;
        this.manager = manager;
        this.expiryDate = expiryDate;
    }
}
