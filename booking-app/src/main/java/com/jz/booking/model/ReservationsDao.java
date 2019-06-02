package com.jz.booking.model;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReservationsDao extends JpaRepository<Reservations, Long> {
    List<Reservations> findByEmail(String email);

}
