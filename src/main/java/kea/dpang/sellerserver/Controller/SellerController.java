package kea.dpang.sellerserver.Controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.persistence.EntityNotFoundException;
import jakarta.websocket.server.PathParam;
import kea.dpang.sellerserver.Dto.Request.SellerCreateDto;
import kea.dpang.sellerserver.Dto.Response.AllSellerGetDto;
import kea.dpang.sellerserver.Service.SellerServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * seller 서비스의 컨트롤러
 * @author Tomas
 */
@Tag(name="Seller",description = "seller 서비스 api")
@RestController
@RequestMapping("/seller")
@RequiredArgsConstructor
@Slf4j
public class SellerController {

    private final SellerServiceImpl sellerService;

    /**
     * API
     * GET : 모든 판매처들을 조회합니다.
     * @return 성공 : 모든 판매처 정보 리스트, 200 OK
     */
    @GetMapping
    @Operation(summary = "모든 판매처 조회", description = "모든 판매처를 조회합니다.")
    public ResponseEntity<List<AllSellerGetDto>> getAllSeller(){
        return new ResponseEntity<>(sellerService.getAllSeller(),HttpStatus.OK);
    }

    /**
     * API
     * GET : 특정 판매처 정보를 조회합니다.
     * @param id 조회할 판매처의 id
     * @return 성공 : 특정 판매처 정보, 200 OK<br>
     * 실패 : 404 Not Found
     */
    @GetMapping("/{sellerId}")
    @Operation(summary = "특정 판매처 조회", description = "특정 판매처 정보를 조회합니다.")
    public ResponseEntity<SellerCreateDto> getSeller(@PathVariable("sellerId") Long id){
        SellerCreateDto seller;
        try{
            seller = sellerService.getSeller(id);
            return new ResponseEntity<>(seller,HttpStatus.OK);
        } catch (EntityNotFoundException e){
            log.error(e.getMessage());
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    /**
     * API
     * POST : 하나의 판매처를 데이터베이스에 등록합니다.
     * @param sellerCreateDto 등록할 판매처의 정보가 담긴 DTO
     * @return 성공 : 201 Created
     */
    @PostMapping
    @Operation(summary = "판매처 등록", description = "하나의 판매처를 데이터베이스에 등록합니다.")
    public ResponseEntity<HttpStatus> postSeller(@RequestBody SellerCreateDto sellerCreateDto){
        sellerService.createSeller(sellerCreateDto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    /**
     * API
     * PUT : 특정 판매처의 정보를 수정합니다.
     * @param id 수정할 판매처의 ID
     * @param sellerUpdateDto 수정할 내용이 담긴 DTO
     * @return 성공 : 200 OK<br>
     * 실패 : 404 Not Found
     */
    @PutMapping("/{sellerId}")
    @Operation(summary = "판매처 수정", description = "특정 판매처의 정보를 수정합니다.")
    public ResponseEntity<HttpStatus> updateSeller(@PathVariable("sellerId") Long id, @RequestBody SellerCreateDto sellerUpdateDto){
        try{
            sellerService.updateSeller(id,sellerUpdateDto);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (EntityNotFoundException e){
            log.error(e.getMessage());
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    /**
     * API
     * DELETE : 하나 혹은 복수의 판매처를 데이터베이스에서 삭제합니다.
     * @param ids 삭제할 판매처들의 ID List
     * @return 성공 : 200 OK
     */
    @DeleteMapping
    @Operation(summary = "판매처 삭제", description = "하나 혹은 복수의 판매처를 데이터베이스에서 삭제합니다.")
    public ResponseEntity<List<Long>> deleteSellers(@RequestParam("sellerId") List<Long> ids){
        sellerService.deleteSeller(ids);
        return new ResponseEntity<>(ids,HttpStatus.OK);
    }
}