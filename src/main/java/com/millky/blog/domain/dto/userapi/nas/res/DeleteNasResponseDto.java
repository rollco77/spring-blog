package com.millky.blog.domain.dto.userapi.nas.res;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class DeleteNasResponseDto {
    private DeleteNasVolumeRawResponseDto deleteNasVolumeInstancesResponse;

    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class DeleteNasVolumeRawResponseDto {
    	private String returnCode;
    	private String returnMessage;
    }
}
