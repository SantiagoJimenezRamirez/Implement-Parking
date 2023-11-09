package com.parking.service;

import com.parking.entity.ParkingEntity;
import com.parking.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClienteServiceImplements implements UserService{

    @Autowired
    UserRepository userRepository;
    @Override
    public List<ParkingEntity> getAll() {
        return userRepository.findAll();
    }

    @Override
    public ParkingEntity guardar(ParkingEntity cliente) {
        return userRepository.save(cliente);
    }

    @Override
    public ParkingEntity getById(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    @Override
    public void deleteById(Long id) {
        userRepository.deleteById(id);
    }
}
