package tally.com.tally_software.service;


import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import tally.com.tally_software.domain.UserRole;
import tally.com.tally_software.model.User;
import tally.com.tally_software.repository.RoleRepository;
import tally.com.tally_software.repository.UserRepository;

import java.util.List;
import java.util.Optional;
import java.util.Set;


@Service
public class UserService {

    private static final Logger LOG =  LoggerFactory.getLogger(UserService.class);

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;



    public List<User> getAll(){
        return userRepository.findAll();
    }

    public Optional<User> getUser(int id){
        return userRepository.findById(id);
    }

    public User createUser(User user, Set<UserRole> userRoles){
        User localUser = userRepository.findByUsername(user.getUsername());
        User localEmail = userRepository.findByEmail(user.getEmail());
        User anotherUser = user;
        if(localUser != null || localEmail != null){
            LOG.info("User with username or Email {} already exist. Nothing will done");

        }else{
            for(UserRole ur: userRoles){
                roleRepository.save(ur.getRole());
            }
            user.getUserRoles().addAll(userRoles);
            userRepository.save(user);
        }
        return localUser;
    }

    public User getUserByUsername(String username){
        return userRepository.findByUsername(username);
    }



    public User getUserByEmail(String email){
        return userRepository.findByEmail(email);
    }

    public void updateUser(User user){
        userRepository.save(user);
    }

    public void deleteUser(User user){
        userRepository.delete(user);
    }

//    public Optional findUserByUserEmail(String email) {
//        return userRepository.findByUserEmail(email);
//    }

//    public Optional findUserByResetToken(String resetToken) {
//        return userRepository.findByResetToken(resetToken);
//    }

    public void save(User user) {
        userRepository.save(user);
    }

    public void updatePassword(String password, int userId) {
        userRepository.updatePassword(password, userId);
    }
}
