package elizagn.values;

import elizagn.interfaces.IValue;
import elizagn.utils.StringUtils;

public class ValueFactory {
    public static IValue create(String value) {
        try {
            return new Const(Double.parseDouble(value));
        } catch (NumberFormatException ex) {
            if (StringUtils.isAlphaNumeric(value)) {
                return new Variable(value);
            }

            throw ex;
        }
    }
}
