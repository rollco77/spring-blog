package com.millky.blog.domain.dto.userapi.networkinterface.req;

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
public class CreateNetworkInterfaceRequestDto {
    private String regionCode;
    private String vpcNo;
    private String subnetNo;
    private List<String> accessControlGroupNoList;
}
