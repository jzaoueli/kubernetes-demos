package com.jz.booking.controllers;

import com.jz.booking.model.Bus;
import com.jz.booking.model.Reservations;
import com.jz.booking.service.BusService;
import com.jz.booking.service.ReservationsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;
import java.util.HashMap;
import java.util.List;

@Controller
public class ReservationsController {

    @Autowired
    private ReservationsService reservationsService;

    @Autowired
    private BusService busService;

    @RequestMapping(value = "/myreservations", method = RequestMethod.GET)
    ModelAndView myReservations(ModelAndView modelAndView,
                                Reservations reserve,
                                Bus bus,
                                BindingResult result,
                                Principal principal
    ) {

        modelAndView.setViewName("app.myreservations");

        List<Reservations> reservations = reservationsService.findByEmail(principal.getName());
        HashMap<Long, Bus> myMap = new HashMap<Long, Bus>();

        Bus trip = new Bus();
        for (int i = 0; i < reservations.size(); i++) {
            trip = busService.findById(reservations.get(i).getBusId());
            myMap.put(reservations.get(i).getId(), trip);
        }

        modelAndView.getModel().put("reservations", reservations);
        modelAndView.getModel().put("myMap", myMap);

        return modelAndView;
    }

    @RequestMapping(value = "/myreservations", method = RequestMethod.POST)
    ModelAndView myReservations(ModelAndView modelAndView, Reservations reserve) {
        reservationsService.delete(reserve.getId());
        modelAndView.setViewName("redirect:/myreservations");
        return modelAndView;
    }
}
