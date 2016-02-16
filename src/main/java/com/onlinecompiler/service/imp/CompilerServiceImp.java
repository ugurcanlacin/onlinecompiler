package com.onlinecompiler.service.imp;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;

import com.onlinecompiler.service.CommandExecutionService;
import com.onlinecompiler.service.CompilerService;

public class CompilerServiceImp implements CompilerService {

	private static final String folder = "temp/";
	private static final String path = System.getProperty("user.home") + "/Documents/";
	private static final String virtualMachine = "virtual_machine";
	private static final String timeoutSecond = "20";
	private int randomName;
	
	@Autowired
	private CommandExecutionService commandExecutionService;
	

	public void createTemporaryFolder() {
		String command = getPreparedCommand();
		commandExecutionService.executeCommand(command);
		System.out.println(command);
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
