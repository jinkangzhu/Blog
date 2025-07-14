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

    /**
     * 为 false 则抛异常
     * @param bool
     */
    default void assertIsTrue(Boolean bool) {
        if (!bool) {
            throw newException();
        }
    }

    /**
     * 为 true 则抛异常
     * @param bool
     */
    default void assertIsFalse(Boolean bool) {
        if (bool) {
            throw newException();
        }
    }

}