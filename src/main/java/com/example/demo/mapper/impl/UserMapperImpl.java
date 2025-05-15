package com.example.demo.mapper.impl;

import com.example.demo.domain.dto.UserDto;
import com.example.demo.domain.entity.UserEntity;
import com.example.demo.mapper.Mapper;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserMapperImpl implements Mapper<UserEntity, UserDto> {

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public UserDto mapTo(UserEntity userEntity) {
        return modelMapper.map(userEntity, UserDto.class);
    }

    @Override
    public UserEntity mapFrom(UserDto userDto) {
        return modelMapper.map(userDto, UserEntity.class);
    }
}
