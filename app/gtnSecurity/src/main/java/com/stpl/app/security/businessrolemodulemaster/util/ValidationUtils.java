package com.stpl.app.security.businessrolemodulemaster.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ValidationUtils {

    private static final Logger LOGGER = LoggerFactory.getLogger(ValidationUtils.class);

    private ValidationUtils() {
        LOGGER.debug("ValidationUtils");
    }

    public static final String SPECIAL_CHARACTER = "([0-9|a-z|A-Z|\\.|])*";
    public static final String SPECIAL_CHARACTER_MESSAGE = "value can contain only digits,alphabets";

}
