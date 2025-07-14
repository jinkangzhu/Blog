package com.blog.exception;

import java.text.MessageFormat;

public interface ExceptionAssert extends CustomAssert, BaseMethod {

    @Override
    default BusinessException newException() {
        return new BusinessException(this);
    }

    @Override
    default BusinessException newException(Object... objects) {
        String format = MessageFormat.format(this.getMessage(), objects);
        return new BusinessException(this,format);
    }
}
