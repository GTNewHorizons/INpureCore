package info.inpureprojects.core.API.Scripting;


public interface IScriptingManager {
    IScriptingCore create(SupportedLanguages paramSupportedLanguages);

    Object WrapScript(IScriptingCore paramIScriptingCore, Object paramObject, Class<?> paramClass);

    enum SupportedLanguages {
        JAVASCRIPT
    }
}
