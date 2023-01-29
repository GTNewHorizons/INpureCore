package info.inpureprojects.core.API.Scripting;

public class ExposedObject {

    private final String identifier;
    private final Object obj;

    public ExposedObject(String identifier, Object obj) {
        this.identifier = identifier;
        this.obj = obj;
    }

    public String getIdentifier() {
        return this.identifier;
    }

    public Object getObj() {
        return this.obj;
    }
}
