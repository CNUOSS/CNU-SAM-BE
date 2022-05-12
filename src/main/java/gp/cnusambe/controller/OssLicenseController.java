package gp.cnusambe.controller;

import gp.cnusambe.dto.OssLicenseDto;
import gp.cnusambe.service.OssLicenseService;
import io.lettuce.core.dynamic.annotation.Param;
import lombok.RequiredArgsConstructor;
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
    //TODO:content-type은 application/x-www-form-urlencoded; charset=UTF-8;
    @GetMapping("/licenses/search") //TODO:search? 체크하기
    public ResponseEntity<List<OssLicenseDto>> get(
            @RequestParam(value = "lc-name",required = false)String licenseNameKeyWord,
            @RequestParam(value = "lc-type",required = false)String licenseTypeName,
            @RequestParam(value = "restriction",required = false)String restrictionName,
            @RequestParam(value = "limit",required = false, defaultValue = "9")Integer limit,
            @RequestParam(value = "offset",required = false,defaultValue = "1")Integer offset,
            @PageableDefault(size=3, page = 0,direction = Sort.Direction.ASC) Pageable pageable)
    {
        List<OssLicenseDto> licenseDtoList =  null;
        if(licenseNameKeyWord != null) {
            licenseDtoList = this.ossLicenseService.searchByLicenseName(licenseNameKeyWord,pageable);
        }else if(licenseTypeName != null) {
            licenseDtoList = this.ossLicenseService.searchByLicenseTypeName(licenseTypeName,pageable);
        }else if(restrictionName != null){
            licenseDtoList = this.ossLicenseService.searchByRestrictionName(restrictionName,pageable);
        }else {
            licenseDtoList = this.ossLicenseService.searchAll(pageable);
        }
        return new ResponseEntity<>(licenseDtoList,HttpStatus.OK);
    }

}

