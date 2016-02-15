package compilersettings;

public class DefaultCompilerParameters {
	private String compilerName = "";
	private String fileName = "";
	private String outputCommand = "";
	private String languageName = "";
	private String extraArguments = "";
	
	public String getCompilerName() {
		return compilerName;
	}
	public void setCompilerName(String compilerName) {
		this.compilerName = compilerName;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public String getOutputCommand() {
		return outputCommand;
	}
	public void setOutputCommand(String outputCommand) {
		this.outputCommand = outputCommand;
	}
	public String getLanguageName() {
		return languageName;
	}
	public void setLanguageName(String languageName) {
		this.languageName = languageName;
	}
	public String getExtraArguments() {
		return extraArguments;
	}
	public void setExtraArguments(String extraArguments) {
		this.extraArguments = extraArguments;
	}
	
}
