package udemy.spring.angular.vendas.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import udemy.spring.angular.vendas.model.entity.User;
import udemy.spring.angular.vendas.respository.UserRepository;

@Service
public class UserService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
         User user = userRepository.findByUsername(s)
                .orElseThrow(
                        () -> new UsernameNotFoundException("Login Não Encontrado")
                );
        return org.springframework.security.core.userdetails.User.builder()
                .username(user.getUsername())
                .password(user.getPassword())
                .roles("USER")
                .build();
    }

    public User cadastro(User user){
        return userRepository.save(user);
    }


}
