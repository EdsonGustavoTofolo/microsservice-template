package io.github.edsongustavotofolo.microservicetemplate.usecases.ports.output.exceptions.enums;

import java.nio.charset.StandardCharsets;
import java.util.Locale;
import java.util.ResourceBundle;

public enum ErrorType {

    EXPT001,
    EXPT002,
    EXPT003,
    EXPT004,
    EXPT005;

    public String getMessage(final Locale locale) {
        var bundle = ResourceBundle.getBundle("i18n/exceptions", locale);
        return new String(
                bundle.getString(name() + ".message").getBytes(StandardCharsets.UTF_8),
                StandardCharsets.UTF_8);
    }
}
