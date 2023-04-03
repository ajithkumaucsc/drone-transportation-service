package com.drone.transportation.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.drone.transportation.entity.EventEntity;

public interface EventRepository extends JpaRepository<EventEntity, Long>{

}
