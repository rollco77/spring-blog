package com.millky.blog.domain.dto.userapi.archivestorage.param;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CreateDirectoryParamDto {
	@NotBlank
    private String containerName;

	@NotBlank
    private String directoryPath;
}
