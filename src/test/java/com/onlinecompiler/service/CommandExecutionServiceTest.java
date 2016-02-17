package com.onlinecompiler.service;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.onlinecompiler.service.imp.CommandExecutionServiceImp;

public class CommandExecutionServiceTest {

	CommandExecutionService commandExecService = new CommandExecutionServiceImp();

	@Test
	public void testExecuteCommand() throws Exception {
//		String command1 = "mkdir /home/ugur/Downloads/compilebox-master/API/temp/8dfa35505ca67ef5b4bf && cp /home/ugur/Downloads/compilebox-master/API//Payload/* /home/ugur/Downloads/compilebox-master/API/temp/8dfa35505ca67ef5b4bf&& chmod 777 /home/ugur/Downloads/compilebox-master/API/temp/8dfa35505ca67ef5b4bf";
//		commandExecService.executeCommand(command1);
//		System.out.println(command1);

//		String command2 = "chmod 777 '/home/ugur/Downloads/compilebox-master/API/temp/8dfa35505ca67ef5b4bf/file.py'";
//		commandExecService.executeCommand(command2);
//		System.out.println(command2);
		
//		String command3 = "sudo /home/ugur/Downloads/compilebox-master/API/DockerTimeout.sh 20s -u mysql -e 'NODE_PATH=/usr/local/lib/node_modules' -i -t -v  \"/home/ugur/Downloads/compilebox-master/API/temp/8dfa35505ca67ef5b4bf\":/usercode virtual_machine /usercode/script.sh python file.py";
//		commandExecService.executeCommand(command3);
//		System.out.println(command3);

		
		System.out.println(System.getProperty("user.home"));

	}
}
