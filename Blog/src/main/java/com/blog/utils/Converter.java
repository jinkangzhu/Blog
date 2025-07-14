package com.blog.utils;

import java.util.List;

public interface Converter<S,T>{
    T convert(S src);

    List<T> convertList(List<S> scrList);
}
