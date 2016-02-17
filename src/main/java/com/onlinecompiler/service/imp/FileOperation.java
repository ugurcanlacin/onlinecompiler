package com.onlinecompiler.service.imp;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import com.onlinecompiler.service.CommandExecutionService;
import com.onlinecompiler.service.DockerParameterConstants;

import compilersettings.Language;

public class FileOperation {

	public static void writeToFile(String fileName, String content, int randomName) throws IOException {
		String filePath = DockerParameterConstants.path + DockerParameterConstants.folder + randomName + "/" + fileName;
		FileWriter fileWriter = new FileWriter(filePath);
		fileWriter.write(content);
		fileWriter.close();
	}

	public static String readContentGivenFile(String fileName, int randomName)
			throws FileNotFoundException, IOException {
		FileReader fr = new FileReader(DockerParameterConstants.path + DockerParameterConstants.folder  + randomName + fileName);
		BufferedReader br = new BufferedReader(fr);
		String s = br.readLine();
		fr.close();
		return s;
	}
}