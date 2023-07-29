package com.nisum.dtn.mapper;

import com.nisum.dtn.dto.UserDto;
import com.nisum.dtn.dto.UserRequest;
import com.nisum.dtn.model.User;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;


@Mapper(
        componentModel = "spring",
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        unmappedTargetPolicy = ReportingPolicy.IGNORE
)
public interface UserMapper {

    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    UserDto toDto(User user);
    User toModel(UserRequest request);
}
