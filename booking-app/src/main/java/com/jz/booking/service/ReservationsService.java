package com.jz.booking.service;

import com.jz.booking.model.Reservations;
import com.jz.booking.model.ReservationsDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.util.List;

@Service
public class ReservationsService {

    @Autowired
    ReservationsDao reservationsDao;

    private NamedParameterJdbcTemplate jdbc;

    @Autowired
    public void setDataSource(DataSource jdbc) {
        this.jdbc = new NamedParameterJdbcTemplate(jdbc);
    }


    public List<Reservations> findByEmail(String email) {
        return reservationsDao.findByEmail(email);
    }

    public void save(Reservations reserve) {
        reservationsDao.save(reserve);

    }

    public void delete(Long id) {
        reservationsDao.delete(id);

    }

    public boolean create(Reservations reserve) {

        BeanPropertySqlParameterSource params = new BeanPropertySqlParameterSource(reserve);

        return jdbc.update("insert into reservations (bus_id, email) values (:busId, :email)", params) == 1;
    }

}
