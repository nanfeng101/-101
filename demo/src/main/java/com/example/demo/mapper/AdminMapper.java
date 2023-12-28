package com.example.demo.mapper;

import com.example.demo.entity.UserEntity;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminMapper {
    UserEntity login(UserEntity userEntity);
}
