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

	@Autowired
	private CommandExecutionService commandExecutionService;

	public void createTemporaryFolder() {
		String command = prepareCommand();
		commandExecutionService.executeCommand(command);
		System.out.println(command);
	}

	private String prepareCommand() {
		int randomName = getRandomNumberForFolderName();
		return String.format("%s && %s && %s", getTempCreationCommand(randomName), getCopyPayloadCommand(randomName),
				getSettingTempPermissionCommand(randomName));
	}

	private int getRandomNumberForFolderName() {
		Random rnd = new Random();
		return 100000 + rnd.nextInt(900000);
	}

	private String getTempCreationCommand(int randomName) {
		return String.format("mkdir %s%s%s", path, folder, randomName);
	}

	private String getCopyPayloadCommand(int randomName) {
		return String.format("cp %sPayload/* %s%s%s", path, path, folder, randomName);
	}

	private String getSettingTempPermissionCommand(int randomName) {
		return String.format("chmod 777 %s%s%s", path, folder, randomName);
	}

	public CommandExecutionService getCommandExecutionService() {
		return commandExecutionService;
	}

	public void setCommandExecutionService(CommandExecutionService commandExecutionService) {
		this.commandExecutionService = commandExecutionService;
	}

}
