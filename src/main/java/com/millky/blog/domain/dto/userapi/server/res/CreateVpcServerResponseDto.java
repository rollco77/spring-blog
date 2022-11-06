package com.millky.blog.domain.dto.userapi.server.res;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CreateVpcServerResponseDto {
    private CreateServerInstanceRawResponseDto createServerInstancesResponse;
    private Map responseError;

    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class CreateServerInstanceRawResponseDto {
        private String requestId;
        private String returnCode;
        private String returnMessage;
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
        private String createDate;
    }

}
