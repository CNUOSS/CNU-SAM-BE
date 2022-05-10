package gp.cnusambe.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import gp.cnusambe.domain.OssLicense;
import gp.cnusambe.domain.OssLicenseType;
import gp.cnusambe.domain.Restriction;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@Builder
@JsonNaming(value = PropertyNamingStrategy.SnakeCaseStrategy.class)
public class OssLicenseDto {
    private Long id;
    private String licenseName;
    private String licenseUrl;
    private OssLicenseType ossLicenseType;
    private List<Restriction> restriction;

    @Autowired
    private static final ModelMapper modelMapper = new ModelMapper();

    public OssLicense makeOssLicenseEntity(OssLicenseDto licenseDto){
        OssLicense license = modelMapper.map(licenseDto, OssLicense.class);
        return license;
    }

    public List<Restriction> makeRestrictionEntity(){
        List<Restriction> list = restriction.stream().map(p -> modelMapper.map(p, Restriction.class)).collect(Collectors.toList());
        return list;
    }

}

