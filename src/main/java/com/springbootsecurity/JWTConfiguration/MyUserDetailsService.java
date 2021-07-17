package com.springbootsecurity.JWTConfiguration;

import com.springbootsecurity.entitys.User;
import com.springbootsecurity.service.IUserService;
import com.springbootsecurity.service.UserDetailsImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MyUserDetailsService implements UserDetailsService {
    @Autowired
    private IUserService iUserService;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        Optional<User> _userOp = iUserService.findByUsername(userName);

        System.out.println(_userOp.get().toString());
        _userOp.orElseThrow( () -> new UsernameNotFoundException("Not found " + userName));
        return _userOp.map(UserDetailsImpl::new).get();
    }
}
