package com.mjroh.boot.doc.controller;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.mjroh.boot.common.model.dto.PageDto;
import com.mjroh.boot.common.util.StringUtil;
import com.mjroh.boot.common.util.WebMap;
import com.mjroh.boot.doc.model.dto.MDocumentDto;
import com.mjroh.boot.doc.model.entity.MDocument;
import com.mjroh.boot.doc.service.DocRepository;
import com.mjroh.boot.doc.service.DocService;

@Controller
@RequestMapping(value = "/doc")
public class DocController {
	@Autowired
	private DocRepository docDao;
	@Autowired
	private DocService service;
	
	@RequestMapping(value = "/saveDocAction")
	@ResponseBody
	public WebMap saveDocAction(@RequestBody MDocument doc){
		WebMap map = new WebMap();
		try {
			boolean isNew = doc.getId() == null;
			MDocument newDoc = docDao.save(doc);
			
			map.setData(newDoc);
			map.setResult(true);
			map.setMsg(isNew? "등록이 완료되었습니다." : "수정이 완료되었습니다.");
			map.setUrl("closeAndReload");
			
		}catch(Exception e) {
			map.setResult(false);
			map.setMsg("등록이 실패하였습니다.\n" + e.getLocalizedMessage());
		}
		return map;
	}
	
	@RequestMapping(value = "/saveDoc")
	public ModelAndView saveDoc(ModelAndView model) {
		MDocumentDto dto = MDocumentDto.builder().build();
		model.setViewName("doc/saveDoc");
		model.addObject("doc", dto);
		return model;
	}
	
	@RequestMapping(value = "/listDoc")
	public ModelAndView listDoc(ModelAndView model, @RequestParam Map<String, Object> map) {
		List<MDocumentDto> list = service.getDocList(map);
		PageDto pageDto = service.getDocPageDto(map);
		model.setViewName("doc/listDoc");
		model.addObject("list", list);
		model.addObject("pageDto", pageDto);
		return model;
	}

	@RequestMapping(value = "/viewDoc")
	public ModelAndView viewDoc(ModelAndView model, @RequestParam Map<String, Object> map) {
		Long id = Long.valueOf(StringUtil.checkReplaceStr((String) map.get("id"), "0"));
		Optional<MDocument> doc = docDao.findById(id);
		MDocumentDto dto = service.convertEntityToDto(doc.get());
		model.setViewName("doc/viewDoc");
		model.addObject("doc", dto);
		return model;
	}
	
	@RequestMapping(value = "/deleteDocAction")
	@ResponseBody
	public WebMap deleteDocAction(@RequestBody MDocument doc){
		WebMap map = new WebMap();
		try {
			docDao.delete(doc);
			
			map.setResult(true);
			map.setMsg("삭제가 완료되었습니다.");
			map.setUrl("closeAndReload");
			
		}catch(Exception e) {
			map.setResult(false);
			map.setMsg("삭제를 실패하였습니다.\n" + e.getLocalizedMessage());
		}
		return map;
	}

	@RequestMapping(value = "/updateDoc")
	public ModelAndView updateDoc(ModelAndView model, @RequestParam Map<String, Object> map) {
		Long id = Long.valueOf(StringUtil.checkReplaceStr((String) map.get("id"), "0"));
		Optional<MDocument> doc = docDao.findById(id);
		MDocumentDto dto = service.convertEntityToDto(doc.get());
		model.setViewName("doc/saveDoc");
		model.addObject("doc", dto);
		return model;
	}
}
