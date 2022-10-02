package com.example.orangehackathon.service;

import com.example.orangehackathon.dto.AdminDTO;
import com.example.orangehackathon.entity.Admin;
import com.example.orangehackathon.repository.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class AdminService implements UserDetailsService {
    @Autowired
    private AdminRepository adminRepository;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Admin admin = adminRepository.findByUsername(username);

        if (admin == null)
            throw new UsernameNotFoundException("Username: " + username + " Not found");

        return new org.springframework.security.core.userdetails.User(admin.getUsername(),
                admin.getPassword(),
                mapToGrantedAuthorities());
    }

    private static List<GrantedAuthority> mapToGrantedAuthorities() {
        List<GrantedAuthority> grantedAuthoritiesList = new ArrayList<>();
        return grantedAuthoritiesList;
    }

    public Admin saveDTO(AdminDTO adminDTO) {
        adminDTO.setPassword(bCryptPasswordEncoder.encode(adminDTO.getPassword()));
        return adminRepository.save(new Admin(adminDTO));
    }

    public boolean checkPassword(String password){
        Pattern p = Pattern.compile("^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{8,}$"); //Minimum eight characters, at least one letter and one number:
        Matcher m = p.matcher(password);
        return m.matches();
    }

    public ResponseEntity<?> createNewUser(AdminDTO adminDTO) {
        if(!adminRepository.existsByUsername(adminDTO.getUsername())) {
            if(checkPassword(adminDTO.getPassword())){
                Admin newAdmin = saveDTO(adminDTO);
                return new ResponseEntity<>(newAdmin, HttpStatus.OK);
            }
            else
                return new ResponseEntity<>("Password "+ adminDTO.getPassword()+" doesn't meet requirements.", HttpStatus.BAD_REQUEST);
        }
        else
            return new ResponseEntity<>("Username "+ adminDTO.getUsername()+" already exists.", HttpStatus.BAD_REQUEST);
    }
}
