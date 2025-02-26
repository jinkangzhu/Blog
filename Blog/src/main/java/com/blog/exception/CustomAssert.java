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
     * @param t
     * @return
     */
    BusinessException newException(Throwable t);

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
     * @param t
     */
    default void assertNotNull(Object object, Throwable t) {
        if (ObjectUtils.isEmpty(object)) {
            throw newException(t);
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
     * @param t
     */
    default void assertNull(Object object, Throwable t) {
        if (!ObjectUtils.isEmpty(object)) {
            throw newException(t);
        }
    }

}