package gp.cnusambe.controller;

import gp.cnusambe.repository.domain.OssLicense;
import gp.cnusambe.service.dto.OssLicenseDto;
import gp.cnusambe.service.dto.PageInfoDto;
import gp.cnusambe.controller.payload.response.OssLicenseListResponse;
import gp.cnusambe.service.OssLicenseService;
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
    @GetMapping("/licenses/search")
    public ResponseEntity<OssLicenseListResponse> search(
            @RequestParam(value = "lc-name",required = false)String licenseNameKeyWord,
            @RequestParam(value = "lc-type",required = false)String licenseTypeName,
            @RequestParam(value = "restriction",required = false)String restrictionName,
            @PageableDefault(size=9, page = 0,sort = "id",direction = Sort.Direction.DESC) Pageable pageable)
    {
        OssLicenseListResponse response;
        Page<OssLicenseDto> licenseDtoPage;

        if(licenseNameKeyWord != null) {
            licenseDtoPage = this.ossLicenseService.searchByLicenseName(licenseNameKeyWord,pageable);
        }else if(licenseTypeName != null) {
            licenseDtoPage = this.ossLicenseService.searchByLicenseTypeName(licenseTypeName,pageable);
        }else if(restrictionName != null){
            //TODO : restrictionName이 아닌 id로 찾기
            licenseDtoPage = this.ossLicenseService.searchByRestrictionName(restrictionName,pageable);
        }else {
            licenseDtoPage = this.ossLicenseService.searchAll(pageable);
        }
        response = new OssLicenseListResponse(new PageInfoDto().makeMetaResponse(licenseDtoPage),licenseDtoPage.getContent());

        return new ResponseEntity<>(response,HttpStatus.OK);
    }


    @GetMapping("/license-names")
    public ResponseEntity<List<OssLicense>> getOssLicenseData(){
        List<OssLicense> licenseList = this.ossLicenseService.getOssLicenseData();
        return new ResponseEntity(licenseList,HttpStatus.OK);
    }

    @DeleteMapping("/licenses/{license_id}")
    //TODO : PathVariable Long인 int인지 확인
    public ResponseEntity delete(@PathVariable("license_id") int id)
    {
        this.ossLicenseService.delete((long) id);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }



    private boolean isEnd(Page<OssLicenseDto> licenseDtoPage){
        return licenseDtoPage.getTotalPages() == licenseDtoPage.getPageable().getPageNumber()+1;
    }
}

