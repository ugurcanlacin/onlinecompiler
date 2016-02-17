package com.onlinecompiler.service.imp;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.onlinecompiler.service.CommandExecutionService;
import com.onlinecompiler.service.CompileRequirements;
import com.onlinecompiler.service.CompilerService;
import com.onlinecompiler.service.DockerParameterConstants;
import com.onlinecompiler.service.ExecutedCodeResult;

import compilersettings.CompilerOrder;
import compilersettings.Language;

@Service
public class CompilerServiceImp implements CompilerService {

	private static final String folder = DockerParameterConstants.folder;
	private static final String path = DockerParameterConstants.path;
	private static final String virtualMachine = DockerParameterConstants.virtualMachine;
	private static final String timeoutSecond = DockerParameterConstants.timeoutSecond;
	private int randomName;
	private Language lang;

	@Autowired
	public CommandExecutionService commandExecutionService;

	public ExecutedCodeResult executeGivenCode(CompileRequirements compileRequirements) throws IOException {
		createTemporaryFolder();
		lang = getRequestedLanguage(compileRequirements.getLanguageName());
		createFilesForExecution(compileRequirements);
		runDocker();
		String output = FileOperation.readContentGivenFile("/completed", randomName);
		String error = FileOperation.readContentGivenFile("/errors", randomName);
		removeTempFolder();
		return new ExecutedCodeResult(output, error);
	}

	public void createTemporaryFolder() throws IOException {
		String command = getPreparedCommand();
		commandExecutionService.executeCommand(command);
	}

	private String getPreparedCommand() {
		randomName = getRandomNumberForFolderName();
		return String.format("%s && %s && %s", getTempCreationCommand(), getCopyPayloadCommand(),
				getSettingTempPermissionCommand());
	}

	private String getTempCreationCommand() {
		return String.format("mkdir %s%s%s", path, folder, randomName);
	}

	private String getCopyPayloadCommand() {
		return String.format("cp %sPayload/* %s%s%s", path, path, folder, randomName);
	}

	private String getSettingTempPermissionCommand() {
		return String.format("chmod 777 %s%s%s", path, folder, randomName);
	}

	private int getRandomNumberForFolderName() {
		Random rnd = new Random();
		return 100000 + rnd.nextInt(900000);
	}

	private Language getRequestedLanguage(String languageName) {
		CompilerOrder compilerOrder = new CompilerOrder();
		return compilerOrder.getLanguageInstance(languageName);
	}

	private void createFilesForExecution(CompileRequirements compileRequirements) throws IOException {
		FileOperation.writeToFile(lang.getFileName(), compileRequirements.getCode(), randomName);
		setTempFilePermission();
		FileOperation.writeToFile("/inputFile", compileRequirements.getInput(), randomName);
	}

	private void runDocker() {
		String docker = String.format(
				"sudo %sDockerTimeout.sh %ss -u mysql -e \'NODE_PATH=/usr/local/lib/node_modules\' -i -t -v  \"%s%s%s\":/usercode %s /usercode/script.sh %s %s",
				path, timeoutSecond, path, folder, randomName, virtualMachine, lang.getCompilerName(),
				lang.getFileName());
		commandExecutionService.executeCommand(docker);
	}

	private void setTempFilePermission() {
		String filePath = String.format("%s%s%s/%s", path, folder, randomName, lang.getFileName());
		String setPermission = String.format("chmod 777 %s", filePath);
		commandExecutionService.executeCommand(setPermission);
	}

	private void removeTempFolder() {
		String removeTempFolder = String.format("rm -r %s%s%s", path, folder, randomName);
		commandExecutionService.executeCommand(removeTempFolder);
	}

	public CommandExecutionService getCommandExecutionService() {
		return commandExecutionService;
	}

	public void setCommandExecutionService(CommandExecutionService commandExecutionService) {
		this.commandExecutionService = commandExecutionService;
	}

}
