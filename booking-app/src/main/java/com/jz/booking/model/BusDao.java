package com.jz.booking.model;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;


@Repository
public interface BusDao extends PagingAndSortingRepository<Bus, Long> {

    List<Bus> findByDateAndLeaveFromAndGoingTo(Date date, String leaveFrom, String goingTo);

    List<Bus> findById(Long id);

}
