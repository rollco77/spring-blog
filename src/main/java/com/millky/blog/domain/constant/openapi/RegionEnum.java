package com.millky.blog.domain.constant.openapi;

import lombok.Getter;

@Getter
public enum RegionEnum {
	KR("kr-standard"),
	US( "us-standard"),
	SG("sg-standard"),
	JP("jp-standard"),
	DE("de-standard")
    ;

	final private String objectStorageRegionName;

	RegionEnum(String objectStorageRegionName) {
		this.objectStorageRegionName = objectStorageRegionName;
	}
}
