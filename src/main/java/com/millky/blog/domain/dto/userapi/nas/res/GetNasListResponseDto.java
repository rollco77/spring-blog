package com.millky.blog.domain.dto.userapi.nas.res;

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
public class GetNasListResponseDto {
    private NasListRawResponseDto getNasVolumeInstanceListResponse;

    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class NasListRawResponseDto {
	    private List<NasInstanceDto> nasVolumeInstanceList;
    }

	@Data
	@Builder
	@AllArgsConstructor
	@NoArgsConstructor
	@JsonIgnoreProperties(ignoreUnknown = true)
	public static class NasInstanceDto {
		private String nasVolumeInstanceNo;
		private CommonCode nasVolumeInstanceStatus;
		private CommonCode nasVolumeInstanceOperation;
		private String nasVolumeInstanceStatusName;
		private String createDate;
		private String nasVolumeDescription;
		private String mountInformation;
		private CommonCode volumeAllotmentProtocolType;
		private String volumeName;
		private String volumeTotalSize;
		private String volumeSize;
		private String volumeUseSize;
		private String volumeUseRatio;
		private String regionCode;
		private String zoneCode;
		private String isEncryptedVolume;
		private String isReturnProtection;
	}
}
