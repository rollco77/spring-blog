package com.millky.blog.domain.dto.userapi.objectstorage.param;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.millky.blog.domain.constant.openapi.RegionEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class DeleteBucketParamDto {
	@NotBlank
    private RegionEnum region;
	@NotBlank
    private String bucketName;
}
