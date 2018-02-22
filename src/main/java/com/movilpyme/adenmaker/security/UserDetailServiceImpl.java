package com.movilpyme.adenmaker.security;

import com.movilpyme.adenmaker.domain.Login;
import com.movilpyme.adenmaker.domain.Usuarios;
import com.movilpyme.adenmaker.repository.LoginRepo;
import com.movilpyme.adenmaker.repository.UsuariosRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class UserDetailServiceImpl implements UserDetailsService {

    @Autowired
    private UsuariosRepo usuariosRepo;

    @Autowired
    private LoginRepo loginRepo;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        Usuarios user = usuariosRepo.findByUsername(s);
        if (user == null){
            throw new UsernameNotFoundException("Usuario Inválido");
        }
        Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
        grantedAuthorities.add(new SimpleGrantedAuthority("admin"));
        updateBeat(user);
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), grantedAuthorities);
    }

    public void updateBeat(Usuarios user){
        List<Login> sesiones = loginRepo.findAllByUsuario(user);
        for (Login login: sesiones){
            login.setBeat(new Date());
            loginRepo.save(login);
        }
    }
}
