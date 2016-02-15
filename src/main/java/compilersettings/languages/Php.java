package compilersettings.languages;

import compilersettings.DefaultCompilerParameters;
import compilersettings.Language;

public class Php extends DefaultCompilerParameters implements Language {
	public Php() {
		this.setCompilerName("php");
		this.setFileName("file.php");
		this.setLanguageName("Php");
	}
}
