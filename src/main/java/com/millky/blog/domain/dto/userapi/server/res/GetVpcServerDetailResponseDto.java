package com.millky.blog.domain.dto.userapi.server.res;

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
public class GetVpcServerDetailResponseDto {
	private GetServerInstanceRawResponseDto getServerInstanceDetailResponse;

	@Data
	@Builder
	@AllArgsConstructor
	@NoArgsConstructor
	@JsonIgnoreProperties(ignoreUnknown = true)
	public static class GetServerInstanceRawResponseDto {
		private List<ServerInstanceDto> serverInstanceList;
	}

	@Data
	@Builder
	@AllArgsConstructor
	@NoArgsConstructor
	@JsonIgnoreProperties(ignoreUnknown = true)
	public static class ServerInstanceDto {
		private String serverInstanceNo;
		private String serverName;
		private String serverInstanceStatusName;
	}
}