package com.jz.booking.service;

import com.jz.booking.model.SiteUser;
import com.jz.booking.model.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    private UserDao userDao;


    public void register(SiteUser user) {
        user.setRole("ROLE_USER");
        userDao.save(user);

    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        SiteUser user = userDao.findByEmail(email);
        if (user == null) {
            return null;
        }

        List<GrantedAuthority> auth = AuthorityUtils.commaSeparatedStringToAuthorityList(user.getRole());
        String password = user.getPassword();
        return new User(email, password, auth);
    }


}
