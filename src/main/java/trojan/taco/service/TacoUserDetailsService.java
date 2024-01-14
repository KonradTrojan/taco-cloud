package trojan.taco.service;


import trojan.taco.model.security.User;
import trojan.taco.repository.jpa.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
class TacoUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Autowired
    public TacoUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(final String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
        if (user != null){
            return user;
        } else {
            throw new UsernameNotFoundException("User " + username + " not found.");
        }
    }

}
