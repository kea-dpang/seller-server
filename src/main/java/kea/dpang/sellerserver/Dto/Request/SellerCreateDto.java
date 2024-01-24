package kea.dpang.sellerserver.Dto.Request;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@NoArgsConstructor
@Getter
public class SellerCreateDto {
    private String name;
    private String phone;
    private String sellerStaff;
    private String manager;
    private LocalDate expiryDate;
    private String note;

    @Builder
    public SellerCreateDto(String name, String phone, String sellerStaff, String manager, LocalDate expiryDate, String note) {
        this.name = name;
        this.phone = phone;
        this.sellerStaff = sellerStaff;
        this.manager = manager;
        this.expiryDate = expiryDate;
        this.note = note;
    }
}