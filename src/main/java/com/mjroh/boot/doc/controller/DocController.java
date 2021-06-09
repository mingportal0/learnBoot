package com.mjroh.boot.doc.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.mjroh.boot.common.model.bean.WebMap;
import com.mjroh.boot.doc.model.entity.MDocument;
import com.mjroh.boot.doc.service.DocRepository;

@Controller
@RequestMapping(value = "/doc")
public class DocController {
	@Autowired
	private DocRepository docDao;
	
	@RequestMapping(value = "/saveDocAction")
	@ResponseBody
	public WebMap saveDocAction(@RequestBody MDocument doc){
		WebMap map = new WebMap();
		try {
			MDocument newDoc = docDao.save(doc);
			
			map.setData(newDoc);
			map.setResult(true);
			map.setMsg("등록이 완료되었습니다.");
			map.setUrl("closeAndReload");
			
		}catch(Exception e) {
			map.setResult(false);
			map.setMsg("등록이 실패하였습니다.\n" + e.getLocalizedMessage());
		}
		return map;
	}
	
	@RequestMapping(value = "/saveDoc")
	public ModelAndView saveDoc() {
		ModelAndView model = new ModelAndView();
		MDocument doc = new MDocument();
		model.addObject("doc", doc);
		model.setViewName("doc/saveDoc");
		return model;
	}
	
	@RequestMapping(value = "/listDoc")
	public ModelAndView listDoc() {
		ModelAndView model = new ModelAndView();
		List<MDocument> list = docDao.findAll();
		model.setViewName("doc/listDoc");
		model.addObject("list", list);
		return model;
	}
}
