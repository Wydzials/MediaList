package pl.wydzials.medialist.security;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import pl.wydzials.medialist.model.User;
import pl.wydzials.medialist.repository.UserRepository;

@Service
public class UserDetailsServiceImplementation implements UserDetailsService {
    private UserRepository userRepository;

    public UserDetailsServiceImplementation(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(s);
        return new UserDetailsImplementation(user);
    }
}
