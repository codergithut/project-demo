package webSource.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import org.springframework.stereotype.Component;
import webSource.jpa.repository.JpaRepositoryBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/12/7.
 */
@Component
public class UserService implements UserDetailsService {

    @Autowired
    JpaRepositoryBean testRepository;

    @Override
    public UserDetails loadUserByUsername(String username){
        webSource.jpa.entry.User user=testRepository.findByUsername(username);
        if(user!=null){
            List<GrantedAuthority> authorities=new ArrayList<GrantedAuthority>();
            authorities.add(new SimpleGrantedAuthority("USERS"));
            return new User(user.getName(),user.getPassword(),authorities);
        }
        throw new UsernameNotFoundException("User "+username +" not found.");
    }
}
