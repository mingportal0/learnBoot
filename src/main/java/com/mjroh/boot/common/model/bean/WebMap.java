package com.mjroh.boot.common.model.bean;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WebMap {
	/* url
	 * close : 모달 닫기
	 * closeAndReload : 모달 닫고 부모창 새로고침
	 * reload : 새로고침
	 * url 입력 : 입력된 url로 이동
	 */
	String url;
	boolean result;
	String msg;
	Object data;
	List<Object> list;
	
	public WebMap() {
		super();
	}
	
	public WebMap(boolean result, String msg) {
		super();
		this.result = result;
		this.msg = msg;
	}
}
