package com.mjroh.boot.doc.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.mjroh.boot.common.model.dto.PageDto;
import com.mjroh.boot.common.util.StringUtil;
import com.mjroh.boot.doc.model.dto.MDocumentDto;
import com.mjroh.boot.doc.model.entity.MDocument;

@Service
public class DocService {

	@Autowired
	private DocRepository docDao;
	
	/**
	 * 
	  * @Method Name : getDocList
	  * @작성일 : 2021. 6. 9.
	  * @작성자 : mjroh
	  * @Method 설명 : 게시판 검색
	  * @param map
	  * @return
	 */
	public List<MDocumentDto> getDocList(Map<String, Object> map){
		Integer pageNum = StringUtil.parseInt((String) map.get("page"), 1);
		List<MDocumentDto> list = new ArrayList<MDocumentDto>();
		Page<MDocument> page = docDao.findAll(PageRequest.of(pageNum - 1, PageDto.PAGE_POST_COUNT, Sort.by(Sort.Direction.ASC, "createStamp")));
		List<MDocument> elist = page.getContent();
		
		if(elist.isEmpty()) {
			return list;
		}
		
		for(MDocument e : elist) {
			list.add(convertEntityToDto(e));
		}
		return list;
	}
	
	/**
	 * 
	  * @Method Name : getDocCount
	  * @작성일 : 2021. 6. 9.
	  * @작성자 : mjroh
	  * @Method 설명 : 게시판 전체 글 수
	  * @return
	 */
	public Long getDocCount() {
		return docDao.count();
	}
	
	public PageDto getDocPageDto(Map<String, Object> map) {
		Integer page = StringUtil.parseInt((String) map.get("page"), 1);
		
		//총 게시글 수
		Double docsTotalCount = Double.valueOf(getDocCount());//20
		
		//마지막 페이지 번호 계산
		Integer totalLastPageNum = (int)(Math.ceil(docsTotalCount/PageDto.PAGE_POST_COUNT));//2
		
		//블럭의 마지막 페이지 번호 계산
		Integer blockLastPageNum = PageDto.BLOCK_PAGE_NUM_COUNT;
		if(totalLastPageNum > page) {
			int ee = (int)(Math.ceil(page/Double.valueOf(PageDto.BLOCK_PAGE_NUM_COUNT)))*PageDto.BLOCK_PAGE_NUM_COUNT;
			if(ee > totalLastPageNum) {
				blockLastPageNum = totalLastPageNum;
			}else {
				blockLastPageNum = ee;
			}
		}else {
			blockLastPageNum = totalLastPageNum;
		}
		
		
		//블럭의 시작 페이지 번호 계산
		Integer blockStartPageNum = 1;
		if(blockLastPageNum % Double.valueOf(PageDto.BLOCK_PAGE_NUM_COUNT) != 0) {
			blockStartPageNum = (blockLastPageNum / 10) * 10 + 1;
		}else {
			blockStartPageNum = 1 + blockLastPageNum - PageDto.BLOCK_PAGE_NUM_COUNT;
		}
		
		//이전 페이지 번호 계산
		Integer prePageNum = blockStartPageNum - PageDto.BLOCK_PAGE_NUM_COUNT;
		if(prePageNum < 1) {
			prePageNum = 0;
		}
		
		//다음 페이지 번호 계산
		Integer nextPageNum = blockStartPageNum + PageDto.BLOCK_PAGE_NUM_COUNT;
		if(nextPageNum > totalLastPageNum) {
			nextPageNum = 0;
		}
		
		//페이지 번호 할당
		Integer[] pageList = new Integer[blockLastPageNum - blockStartPageNum + 1];
		for(int val = blockStartPageNum, idx = 0; val <= blockLastPageNum; val++, idx++) {
			pageList[idx] = val;
		}
		
		PageDto dto = PageDto.builder()
				.page(page)
				.pageList(pageList)
				.firstPageNum(PageDto.First_Page_Num)
				.LastPageNum(totalLastPageNum)
				.previousPageNum(prePageNum)
				.nextPageNum(nextPageNum)
				.build();
		
		return dto;
	}
	
	public MDocumentDto convertEntityToDto(MDocument e) {
	    return MDocumentDto.builder()
	            .title(e.getTitle())
	            .content(e.getContent())
	            .creator(e.getCreator())
	            .id(e.getId())
	            .isEnabled(e.getIsEnabled())
	            .isDeleted(e.getIsDeleted())
	            .modifier(e.getModifier())
	            .createStamp(e.getCreateStamp())
	            .modifyStamp(e.getModifyStamp())
	            .build();
	}
}
