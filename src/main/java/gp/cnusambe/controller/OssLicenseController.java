package gp.cnusambe.controller;

import gp.cnusambe.dto.OssLicenseDto;
import gp.cnusambe.payload.response.MetaResponse;
import gp.cnusambe.payload.response.OssLicenseListResponse;
import gp.cnusambe.service.OssLicenseService;
import io.lettuce.core.dynamic.annotation.Param;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RequiredArgsConstructor
@RestController
public class OssLicenseController {
    private final OssLicenseService ossLicenseService;

    @PostMapping("/licenses")
    public ResponseEntity<OssLicenseDto> post(@Valid @RequestBody OssLicenseDto licenseDto){
        OssLicenseDto newLicense = this.ossLicenseService.create(licenseDto);
        return new ResponseEntity<>(newLicense,HttpStatus.OK);

    }

    //TODO : Specification 알아보기
    //TODO : size = 9로 변경
    @GetMapping("/licenses/search") //TODO:search? 체크하기
    public ResponseEntity<OssLicenseListResponse> get(
            @RequestParam(value = "lc-name",required = false)String licenseNameKeyWord,
            @RequestParam(value = "lc-type",required = false)String licenseTypeName,
            @RequestParam(value = "restriction",required = false)String restrictionName,
            @PageableDefault(size=3, page = 0,direction = Sort.Direction.ASC) Pageable pageable)
    {
        OssLicenseListResponse response;
        Page<OssLicenseDto> licenseDtoPage;

        if(licenseNameKeyWord != null) {
            licenseDtoPage = this.ossLicenseService.searchByLicenseName(licenseNameKeyWord,pageable);
        }else if(licenseTypeName != null) {
            licenseDtoPage = this.ossLicenseService.searchByLicenseTypeName(licenseTypeName,pageable);
        }else if(restrictionName != null){
            licenseDtoPage = this.ossLicenseService.searchByRestrictionName(restrictionName,pageable);
        }else {
            licenseDtoPage = this.ossLicenseService.searchAll(pageable);
        }
        response = new OssLicenseListResponse(new MetaResponse(licenseDtoPage.getTotalElements(),isEnd(licenseDtoPage)),licenseDtoPage.getContent());

        return new ResponseEntity<>(response,HttpStatus.OK);
    }

    private boolean isEnd(Page<OssLicenseDto> licenseDtoPage){
        return licenseDtoPage.getTotalPages() == licenseDtoPage.getPageable().getPageNumber()+1;
    }
}

