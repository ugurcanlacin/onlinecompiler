package compilersettings;

import java.util.HashMap;
import java.util.Map;

import compilersettings.languages.Php;
import compilersettings.languages.Python;


public class CompilerOrder {
	private Map<String, Language> compilerMap;
	
	public Language getLanguageInstance(String language){
		if(isCompilerMapNull())
			compilerMap = new HashMap<>();
		addExistLanguages();
		return compilerMap.get(language);
	}

	private void addExistLanguages() {
		compilerMap.put("python", new Python());
		compilerMap.put("php", new Php());
	}

	private boolean isCompilerMapNull() {
		return compilerMap == null;
	}
}
