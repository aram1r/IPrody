package com.example.service;

import com.example.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailService implements UserDetailsService {

    @Autowired
    private UserDao userDao;



    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        var user = userDao.findByEmail(email);

        if (user == null) {
            throw new UsernameNotFoundException(email);
        }

        return User.builder().username(user.getEmail()).
                password(user.getPassword()).
                disabled(!user.isEnabled)
                .authorities(user.getAuthorities().stream().map(
                        Authority::getAuthority).toArray(new String[]));
    }
}
