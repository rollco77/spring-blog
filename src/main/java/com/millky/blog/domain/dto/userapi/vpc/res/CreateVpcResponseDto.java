package com.millky.blog.domain.dto.userapi.vpc.res;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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
public class CreateVpcResponseDto {
	private CreateVpcRawResponseDto createVpcResponse;

	@Data
	@Builder
	@AllArgsConstructor
	@NoArgsConstructor
	@JsonIgnoreProperties(ignoreUnknown = true)
	public static class CreateVpcRawResponseDto {
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
	}
}
