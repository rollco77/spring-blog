package com.millky.blog.domain.dto.userapi.objectstorage.param;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.millky.blog.domain.constant.openapi.RegionEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.StringUtils;

import javax.validation.constraints.NotBlank;
import java.io.File;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UploadObjectFileParamDto {
	@NotBlank
    private RegionEnum region;
	@NotBlank
    private String bucketName;
    private String folderPath;
	@NotBlank
    private String objectName;
	@NotBlank
    private File objectFile;

    public String getObjectFilePath() {
    	return (StringUtils.isEmpty(folderPath) ? objectName : folderPath+objectName);
    }
}
