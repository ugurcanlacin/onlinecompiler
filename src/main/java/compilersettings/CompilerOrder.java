package compilersettings;

import java.util.HashMap;
import java.util.Map;

import compilersettings.languages.Php;
import compilersettings.languages.Python;

public class CompilerOrder {
	private Map<String, Language> compilerMap;

	public Language getLanguageInstance(String language) {
		prepareInstance();
		return compilerMap.get(language);
	}

	private void prepareInstance() {
		if (isCompilerMapNull()) {
			compilerMap = new HashMap<>();
			addExistLanguages();
		}
	}

	private boolean isCompilerMapNull() {
		return compilerMap == null;
	}

	private void addExistLanguages() {
		compilerMap.put("python", new Python());
		compilerMap.put("php", new Php());
	}

}
