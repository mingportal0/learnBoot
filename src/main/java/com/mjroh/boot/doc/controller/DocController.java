package com.mjroh.boot.doc.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.mjroh.boot.doc.model.entity.MDocument;
import com.mjroh.boot.doc.service.DocDao;

@Controller
@RequestMapping(value = "/doc")
public class DocController {
	@Autowired
	private DocDao docDao;
	
	@RequestMapping(value = "/saveDocAction")
	@ResponseBody
	public List<MDocument> saveDocAction(MDocument doc){
		System.out.println(doc);
		docDao.save(doc);
		
		return docDao.findAll();
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
