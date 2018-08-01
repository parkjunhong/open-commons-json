package code.org.codehaus.jettison;

import code.org.codehaus.jettison.json.JSONException;

public class JSONSequenceTooLargeException extends JSONException {

    private static final long serialVersionUID = -8281225263069171596L;

    public JSONSequenceTooLargeException(String message) {
        super(message);
    }
}
