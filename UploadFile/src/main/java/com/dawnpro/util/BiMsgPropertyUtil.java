package com.dawnpro.util;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@PropertySource("classpath:biMsgProperty.properties")
@ConfigurationProperties(prefix = "BI")
@Component
public class BiMsgPropertyUtil {
	private List<String> savepath = new ArrayList<>();

	private List<String> callurl = new ArrayList<>();

	public List<String> getSavepath() {
		return savepath;
	}

	public void setSavepath(List<String> savepath) {
		this.savepath = savepath;
	}

	public List<String> getCallurl() {
		return callurl;
	}

	public void setCallurl(List<String> callurl) {
		this.callurl = callurl;
	}
}
