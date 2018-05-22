package com.sinoufc.test;

public enum ESex {

	SEX_MAN("男"),
	SEX_WOM("女");
	
	private String value;
	
	ESex(String value){
		this.value = value;
	}

	public String value() {
		return value;
	}

}
