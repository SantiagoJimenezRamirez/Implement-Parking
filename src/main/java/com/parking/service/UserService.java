package com.parking.service;

import com.parking.entity.ParkingEntity;

import java.util.List;

public interface UserService {
    public List<ParkingEntity> getAll();
    public ParkingEntity guardar(ParkingEntity cliente);
    public ParkingEntity getById(Long id);
    public void deleteById (Long id);
}
