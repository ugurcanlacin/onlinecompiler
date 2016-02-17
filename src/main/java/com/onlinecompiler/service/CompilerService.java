package com.onlinecompiler.service;

import java.io.IOException;

public interface CompilerService {
	public void createTemporaryFolder() throws IOException;
	public void setCommandExecutionService(CommandExecutionService commandExecutionService);
	public ExecutedCodeResult executeGivenCode(CompileRequirements compileRequirements) throws IOException;
}
