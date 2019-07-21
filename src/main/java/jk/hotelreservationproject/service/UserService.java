package jk.hotelreservationproject.service;

import jk.hotelreservationproject.model.User;
import jk.hotelreservationproject.repository.UserRepository;
import jk.hotelreservationproject.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    UserRepository userRepository;
    RoleRepository roleRepository;
    PasswordEncoder passwordEncoder;


    @Autowired
    public UserService(UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public void registerUser(User user){
        user.setRoleId(roleRepository.getOne(1L));
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
    }

    public boolean passwordCheck(String password, String password_confirm){
        if (password.equals(password_confirm)){
            return true;
        }
        return false;
    }
}
