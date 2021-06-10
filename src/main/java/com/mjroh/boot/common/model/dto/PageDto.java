package com.mjroh.boot.common.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@EqualsAndHashCode(callSuper = false)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PageDto {
	public static final int First_Page_Num = 1;  // 시작 페이지 번호
	public static final int BLOCK_PAGE_NUM_COUNT = 10;  // 블럭에 존재하는 페이지 번호 수
    public static final int PAGE_POST_COUNT = 15;       // 한 페이지에 존재하는 게시글 수
    
    Integer[] pageList;
    int page;
    int firstPageNum;
    int LastPageNum;
    int previousPageNum;
    int nextPageNum;
}
