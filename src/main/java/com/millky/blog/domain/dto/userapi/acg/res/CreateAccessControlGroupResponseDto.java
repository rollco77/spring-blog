package com.millky.blog.domain.dto.userapi.acg.res;

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
public class CreateAccessControlGroupResponseDto {
    private CreateAccessControlGroupRawResponseDto createAccessControlGroupResponse;

    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class CreateAccessControlGroupRawResponseDto {
        private String requestId;
        private String returnCode;
        private String returnMessage;
        private List<AcgInstanceDto> accessControlGroupList;
    }

    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class AcgInstanceDto {
        private String accessControlGroupNo;
        private String accessControlGroupName;
    }
}
