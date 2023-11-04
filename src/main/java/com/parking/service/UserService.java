package com.parking.service;

import com.parking.entity.UserEntity;

import java.util.List;

public interface UserService {
    public List<UserEntity> getAll();
    public UserEntity guardar(UserEntity cliente);
    public UserEntity getById(Long id);
    public void deleteById (Long id);
}
