package com.millky.blog.domain.dto.userapi.nas.req;

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
public class ChangeNasVolumeRequestDto {
    private String nasVolumeInstanceNo;
    private Integer volumeSize;
}
