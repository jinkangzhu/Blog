package com.blog.exception;

import java.text.MessageFormat;

public interface TestAssert extends CustomAssert,BaseMethod{

    @Override
    public default BusinessException newException() {
        String format = MessageFormat.format(this.getMessage(),null);
        return new BusinessException(format);
    }

    @Override
    public default BusinessException newException(Object... objects) {
        String format = MessageFormat.format(this.getMessage(), objects);
        return new BusinessException(format);
    }
}
