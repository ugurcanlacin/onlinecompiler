package com.onlinecompiler.service;

import static org.junit.Assert.*;

import java.util.Random;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.onlinecompiler.service.imp.CommandExecutionServiceImp;
import com.onlinecompiler.service.imp.CompilerServiceImp;

public class CompilerServiceTest {

	CommandExecutionService commandExecutionService = new CommandExecutionServiceImp();
	CompilerService compilerService = new CompilerServiceImp();

	@Test
	public void testCreateTemporaryFolder() throws Exception {
		compilerService.setCommandExecutionService(commandExecutionService);
		CompileRequirements requirements = new CompileRequirements();
		requirements.setCode("<?php echo \"selam\";?>");
		requirements.setInput("hello");
		requirements.setLanguageName("php");
		compilerService.executeGivenCode(requirements);
	}

}
