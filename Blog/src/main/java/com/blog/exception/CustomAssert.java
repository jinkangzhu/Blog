package com.blog.exception;

import org.springframework.util.ObjectUtils;

public interface CustomAssert{
    /**
     * 创建新的异常
     *
     * @return
     */
    BusinessException newException();

    /**
     * 根据抛出异常类和ErrorCode创建新的异常
     *
     * @param message message
     * @return
     */
    BusinessException newException(Object... message);

    /**
     * 对象为空则抛出异常
     *
     * @param object
     */
    default void assertNotNull(Object object) {
        if (ObjectUtils.isEmpty(object)) {
            throw newException();
        }
    }

    /**
     * 对象为空则抛出异常
     *
     * @param object
     * @param message
     */
    default void assertNotNull(Object object, String message) {
        if (ObjectUtils.isEmpty(object)) {
            throw newException(message);
        }
    }

    /**
     * 对象不为空则抛出异常
     *
     * @param object
     */
    default void assertNull(Object object) {
        if (!ObjectUtils.isEmpty(object)) {
            throw newException();
        }
    }

    /**
     * 对象不为空则抛出异常
     *
     * @param object
     * @param message
     */
    default void assertNull(Object object, String message) {
        if (!ObjectUtils.isEmpty(object)) {
            throw newException(message);
        }
    }

    default void assertIsTrue(Boolean isTrue) {
        if (isTrue) {
            BusinessException exception = newException();
            throw exception;
        }
    }
    default void assertIsFalse(Boolean isFalse) {
        if (!isFalse) {
            throw newException();
        }
    }

}