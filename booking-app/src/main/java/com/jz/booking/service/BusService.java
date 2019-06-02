package com.jz.booking.service;

import com.jz.booking.model.Bus;
import com.jz.booking.model.BusDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class BusService {

    @Autowired
    private BusDao busDao;

    public void save(Bus bus) {
        busDao.save(bus);
    }

    public List<Bus> findByDateLeave(Date date, String leaveFrom, String goingTo) {
        List<Bus> results = busDao.findByDateAndLeaveFromAndGoingTo(date, leaveFrom, goingTo);

        return results;
    }

    public Bus findById(Long id) {
        Bus bus = busDao.findOne(id);
        return bus;
    }

}
