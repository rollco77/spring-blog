package com.millky.blog.domain.dto.userapi.server.req;

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
public class OperateVpcServersRequestDto {
	private String regionCode;
	private List<String> serverInstanceNoList;
}
