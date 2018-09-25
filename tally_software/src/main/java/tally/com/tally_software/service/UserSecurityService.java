package tally.com.tally_software.service;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import tally.com.tally_software.model.User;
import tally.com.tally_software.repository.CompanyRepository;
import tally.com.tally_software.repository.UserRepository;

import java.util.Optional;

@Service
public class UserSecurityService implements UserDetailsService {

    private static final Logger LOG = LoggerFactory.getLogger(UserSecurityService.class);

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CompanyRepository companyRepository;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(s);
//        User email = userRepository.findByEmail(s);

        if(user == null){
//            LOG.warn("Username not found", username);
            throw new UsernameNotFoundException("UserName " + s + "not found");
        }
        return  user;

    }


    public UserDetails loadUserByEmail(String e) {
//        User user = userRepository.findByUsername(s);
        User email = userRepository.findByEmail(e);

        if( email == null){
//            LOG.warn("Username not found", username);
            throw new UsernameNotFoundException("Email " + e + "not found");
        }
        return  email;

    }



//    public UserDetails loadUserByName(String c) {
////        User user = userRepository.findByUsername(s);
//         Company company = companyRepository.findByCompany_Name(c);
//
//        if( company == null){
////            LOG.warn("Username not found", username);
//            throw new UsernameNotFoundException("UserName " + c + "not found");
//        }
//        return (UserDetails) company;
//
//    }
}

