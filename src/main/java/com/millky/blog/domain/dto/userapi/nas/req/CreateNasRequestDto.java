package com.millky.blog.domain.dto.userapi.nas.req;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CreateNasRequestDto {
    private String regionCode;
    private String zoneCode;
	@NotBlank
    private String volumeName;
    @NotBlank
    private String volumeAllotmentProtocolTypeCode; // NFS | CIFS
	@NotNull
    private Integer volumeSize; // Min : 500, Max 10,000
	private String cifsUserName;
	private String cifsUserPassword;
	private Boolean isEncryptedVolume;
	private Boolean isReturnProtection;
	private String nasVolumeDescription;
	private List<String> serverInstanceNoList;
}
