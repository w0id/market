//package ru.gb.market.user.configs;
//
//import lombok.RequiredArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.security.authentication.BadCredentialsException;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.authentication.dao.AbstractUserDetailsAuthenticationProvider;
//import org.springframework.security.core.AuthenticationException;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.stereotype.Component;
//
//@Slf4j
//@Component
//@RequiredArgsConstructor
//public class StandardAuthenticationProvider extends AbstractUserDetailsAuthenticationProvider {
//
//    private final UserDetailsService userDetailsService;
//
//    @Override
//    protected void additionalAuthenticationChecks(final UserDetails userDetails, final UsernamePasswordAuthenticationToken authentication) throws AuthenticationException {
//        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
//        if (!passwordEncoder.matches(authentication.getCredentials().toString(), userDetails.getPassword())) {
//            log.error("Bad credentials for {}", userDetails.getUsername());
//            throw new BadCredentialsException("Bad credentials");
//        }
//    }
//
//    @Override
//    protected UserDetails retrieveUser(final String username, final UsernamePasswordAuthenticationToken authentication) throws AuthenticationException {
//        return userDetailsService.loadUserByUsername(username);
//    }
//}