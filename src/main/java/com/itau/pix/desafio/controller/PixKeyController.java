package com.itau.pix.desafio.controller;

import com.itau.pix.desafio.domain.KeyType;
import com.itau.pix.desafio.domain.PixKey;
import com.itau.pix.desafio.dto.PixKeyResponseDto;
import com.itau.pix.desafio.dto.PixKeyUpdateDto;
import com.itau.pix.desafio.request.PixKeyPostRequestBody;
import com.itau.pix.desafio.request.PixKeyPutRequestBody;
import com.itau.pix.desafio.service.PixKeyService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("pixkey")
@Log4j2
@RequiredArgsConstructor
public class PixKeyController {

    private PixKeyService pixKeyService;

    @Autowired
    public PixKeyController(PixKeyService pixKeyService) {
        this.pixKeyService = pixKeyService;
    }
    @GetMapping(path = "/{id}")
    public ResponseEntity<PixKeyResponseDto> findById(@PathVariable UUID id){
        log.info("Method: findById, args={}", id);
        return ResponseEntity.ok(pixKeyService.findById(id));
    }

    @Validated
    @PostMapping
    public ResponseEntity<UUID> save(@RequestBody @Valid PixKeyPostRequestBody pixKeyPostRequestBody){
        log.info("Method: save, args={}", pixKeyPostRequestBody);
        return new ResponseEntity<>(pixKeyService.save(pixKeyPostRequestBody), HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<PixKeyUpdateDto> update(@RequestBody @Valid PixKeyPutRequestBody pixKeyPutRequestBody){
        log.info("Method: update, args={}", pixKeyPutRequestBody);
        return ResponseEntity.ok(pixKeyService.update(pixKeyPutRequestBody));
    }

    @GetMapping
    public ResponseEntity<List<PixKeyResponseDto>> listByCombinedResources(
                                            @RequestParam(required = false) KeyType keyType,
                                              @RequestParam(required = false) Integer agency,
                                              @RequestParam(required = false) Integer account,
                                              @RequestParam(required = false) String name){
        log.info("Method: listByCombinedResources, args=keyType: {}, agency: {}, account: {}, name: {}", keyType, agency, account, name);
        return ResponseEntity.ok(pixKeyService.listByCombinedResources(keyType, agency, account, name));
    }

}
