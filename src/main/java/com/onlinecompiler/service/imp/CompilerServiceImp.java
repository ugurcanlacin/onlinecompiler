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
import com.onlinecompiler.service.ExecutedCodeResult;

import compilersettings.CompilerOrder;
import compilersettings.Language;

@Service
public class CompilerServiceImp implements CompilerService {

	private static final String folder = "temp/";
	private static final String path = System.getProperty("user.home") + "/Downloads/compilebox-master/API/";
	private static final String virtualMachine = "virtual_machine";
	private static final String timeoutSecond = "20";
	private int randomName;
	private String tempDirectory = path + folder + randomName;
	private Language lang;
	
	@Autowired
	public CommandExecutionService commandExecutionService;

	
	public ExecutedCodeResult executeGivenCode(CompileRequirements compileRequirements) throws IOException{
		createTemporaryFolder();

		lang = getRequestedLanguage(compileRequirements.getLanguageName());
		
		writeToFile(lang.getFileName(), compileRequirements.getCode());
		
		setTempFilePermission();
		
		writeToFile("/inputFile", compileRequirements.getInput());
		runDocker();

		String output = readContentGivenFile("/completed");
		String error = readContentGivenFile("/errors");
		
		removeTempFolder();
		
		ExecutedCodeResult codeResult = new ExecutedCodeResult();
		codeResult.setError(error);
		codeResult.setOutput(output);
		
		return codeResult;
	}

	private void writeToFile(String fileName,String content) throws IOException{
		String filePath = path + folder + randomName + "/" + fileName;
		FileWriter fileWriter = new FileWriter(filePath);
		fileWriter.write(content);
		fileWriter.close();
	}
	
	private String readContentGivenFile(String fileName) throws FileNotFoundException, IOException {
		FileReader fr = new FileReader(path + folder + randomName + fileName);
		BufferedReader br = new BufferedReader(fr);
		String s = br.readLine();
		fr.close();
		return s;
	}

	private void runDocker() {
		String docker = "sudo " + path + "DockerTimeout.sh " + timeoutSecond
				+ "s -u mysql -e \'NODE_PATH=/usr/local/lib/node_modules\' -i -t -v  \"" + path + folder + randomName
				+ "\":/usercode " + virtualMachine + " /usercode/script.sh " + lang.getCompilerName() + " "
				+ lang.getFileName();
		commandExecutionService.executeCommand(docker);
	}

	private void setTempFilePermission() {
		String filePath = path + folder + randomName + "/" + lang.getFileName();
		String setPermission = "chmod 777 " + filePath;
		commandExecutionService.executeCommand(setPermission);
	}

	private void removeTempFolder() {
		String removeTempFolder = "rm -r " + path + folder + randomName;
		commandExecutionService.executeCommand(removeTempFolder);
	}
	
	public void createTemporaryFolder() throws IOException {
		String command = getPreparedCommand();
		commandExecutionService.executeCommand(command);
	}

	private Language getRequestedLanguage(String languageName) {
		CompilerOrder compilerOrder = new CompilerOrder();
		return compilerOrder.getLanguageInstance(languageName);
	}

	private String getPreparedCommand() {
		randomName = getRandomNumberForFolderName();
		return String.format("%s && %s && %s", getTempCreationCommand(), getCopyPayloadCommand(),
				getSettingTempPermissionCommand());
	}

	private int getRandomNumberForFolderName() {
		Random rnd = new Random();
		return 100000 + rnd.nextInt(900000);
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

	public CommandExecutionService getCommandExecutionService() {
		return commandExecutionService;
	}

	public void setCommandExecutionService(CommandExecutionService commandExecutionService) {
		this.commandExecutionService = commandExecutionService;
	}

}
