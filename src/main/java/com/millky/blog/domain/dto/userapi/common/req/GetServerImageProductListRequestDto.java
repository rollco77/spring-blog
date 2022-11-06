package com.millky.blog.domain.dto.userapi.common.req;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class GetServerImageProductListRequestDto {
	private String regionCode;
	private String blockStorageSize;
	private String exclusionProductCode;
	private String productCode;
	private List<String> platformTypeCodeList;
}
