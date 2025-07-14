package com.blog.converter;

import com.blog.dto.LoginRequest;
import com.blog.entity.User;
import com.blog.utils.Converter;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserDTOToUserConverter extends Converter<LoginRequest, User> {

    UserDTOToUserConverter INSTANCE =  Mappers.getMapper(UserDTOToUserConverter.class);
}
