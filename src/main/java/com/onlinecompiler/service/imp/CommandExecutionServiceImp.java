package com.onlinecompiler.service.imp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.onlinecompiler.service.CommandExecutionService;

@Service
public class CommandExecutionServiceImp implements CommandExecutionService{

	public void executeCommand(String command) {
		Runtime runtime = Runtime.getRuntime();
        ProcessBuilder pb = new ProcessBuilder("/bin/bash", "-c", command);
		try {
			Process process = pb.start();
			BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(process.getInputStream()));
			String line;
			while ((line = bufferedReader.readLine()) != null) {
				System.out.println(line);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
