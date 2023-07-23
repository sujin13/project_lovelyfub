package example.domain.store.controller;

import example.domain.store.entity.Store;
import example.domain.store.mapper.StoreMapper;
import example.domain.store.service.StoreService;
import example.global.exception.BusinessLogicException;
import example.global.exception.ExceptionCode;
import example.global.response.MultiResponseDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import javax.validation.constraints.Positive;
import java.util.List;
@RestController
@RequestMapping
@Validated
@Slf4j
public class StoreController {

    private StoreService service;
    private StoreMapper mapper;

    public StoreController(StoreService service, StoreMapper mapper) {
        this.service = service;
        this.mapper = mapper;
    }
    //식당 리스트
    @GetMapping("/cafe")
    public ResponseEntity getCafeList(@Positive @RequestParam int page, @Positive @RequestParam int size) {
        Page<Store> pageStore = service.findStore("cafe",page -1, size);
        List<Store> store = pageStore.getContent();
        return new ResponseEntity<>(new MultiResponseDto<>(mapper.storeToStoreResponseDto(store), pageStore), HttpStatus.OK);
    }
    //마켓리스트
    @GetMapping("/market")
    public ResponseEntity getMarketList(@Positive @RequestParam int page, @Positive @RequestParam int size) {
        Page<Store> pageStore = service.findStore("market",page -1, size);
        System.out.println(pageStore);
        List<Store> store = pageStore.getContent();
        return new ResponseEntity<>(new MultiResponseDto<>(mapper.storeToStoreResponseDto(store), pageStore), HttpStatus.OK);
    }
    //식당상세페이지
    @GetMapping("/cafe/{id}")
    public ResponseEntity getCafe(@PathVariable("id") @Positive int storeid) {
        Store store = service.findStoreDetail(storeid);
        return new ResponseEntity<>(mapper.detailToDetailResponseDto(store), HttpStatus.OK);
    }
    //마켓상세페이지
    @GetMapping("/market/{id}")
    public ResponseEntity getMarket(@PathVariable("id") @Positive int storeid) {
        Store store = service.findStoreDetail(storeid);
        return new ResponseEntity<>(mapper.detailToDetailResponseDto(store), HttpStatus.OK);
    }
    //길찾기
}