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
public class GetVpcDetailResponseDto {
	private GetVpcDetailRawResponseDto getVpcDetailResponse;

	@Data
	@Builder
	@AllArgsConstructor
	@NoArgsConstructor
	@JsonIgnoreProperties(ignoreUnknown = true)
	public static class GetVpcDetailRawResponseDto {
		private List<VpcInstanceDto> vpcList;
	}

	@Data
	@Builder
	@AllArgsConstructor
	@NoArgsConstructor
	@JsonIgnoreProperties(ignoreUnknown = true)
	public static class VpcInstanceDto {
		private String vpcNo;
		private String vpcName;
		private String ipv4CidrBlock;
		private String regionCode;
		private String createDate;
		private CommonCode vpcStatus;
	}
}
