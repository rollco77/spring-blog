package com.millky.blog.domain.dto.userapi.vpc.res;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.millky.blog.domain.dto.userapi.common.CommonCode;
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
public class GetNetworkAclListResponseDto {
	private GetNetworkAclListRawResponseDto getNetworkAclListResponse;

	@Data
	@Builder
	@AllArgsConstructor
	@NoArgsConstructor
	@JsonIgnoreProperties(ignoreUnknown = true)
	public static class GetNetworkAclListRawResponseDto {
		private List<NetworkAclInstanceDto> networkAclList;
	}

	@Data
	@Builder
	@AllArgsConstructor
	@NoArgsConstructor
	@JsonIgnoreProperties(ignoreUnknown = true)
	public static class NetworkAclInstanceDto {
		private String networkAclNo;
		private String networkAclName;
		private String vpcNo;
		private CommonCode networkAclStatus;
		private Boolean isDefault;
	}
}
