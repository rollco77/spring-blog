package com.millky.blog.domain.dto.userapi.archivestorage.param;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.File;
import java.util.Map;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UploadArchiveObjectFileParamDto {
	@NotBlank
	private String containerName;

	@Builder.Default
	private String directoryPath = "";

	@NotBlank
	private String objectName;

	private String contentType;

	@NotNull
	private File file;

	private Map<String, String> metaData;
}
