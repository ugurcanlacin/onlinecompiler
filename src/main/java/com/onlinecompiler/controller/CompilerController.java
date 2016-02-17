package com.onlinecompiler.controller;

import java.io.IOException;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.onlinecompiler.service.CompileRequirements;
import com.onlinecompiler.service.CompilerService;
import com.onlinecompiler.service.ExecutedCodeResult;
import com.onlinecompiler.service.imp.CompilerServiceImp;

@RestController
public class CompilerController {
	
	@Autowired
	public CompilerService compilerService ;
	
	@RequestMapping(value="/output", method=RequestMethod.POST)
	public ExecutedCodeResult getOutput(@ModelAttribute CompileRequirements compileRequirements,Model model) throws IOException{
		ExecutedCodeResult codeResult = compilerService.executeGivenCode(compileRequirements);
		return codeResult;
	}

	public CompilerService getCompilerService() {
		return compilerService;
	}

	public void setCompilerService(CompilerService compilerService) {
		this.compilerService = compilerService;
	}
	
}
