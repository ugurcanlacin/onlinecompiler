package compilersettings.languages;

import compilersettings.DefaultCompilerParameters;
import compilersettings.Language;

public class Python extends DefaultCompilerParameters implements Language {
	public Python() {
		this.setCompilerName("python");
		this.setFileName("file.py");
		this.setLanguageName("Python");
	}
}
