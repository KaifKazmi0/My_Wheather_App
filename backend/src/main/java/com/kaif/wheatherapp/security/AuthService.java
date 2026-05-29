package com.kaif.wheatherapp.security;

import com.kaif.wheatherapp.dto.LoginRequestDto;
import com.kaif.wheatherapp.dto.LoginResponseDto;
import com.kaif.wheatherapp.dto.SignupResponseDto;
import com.kaif.wheatherapp.entity.User;
import com.kaif.wheatherapp.repo.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final AuthUtil authUtil;
    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public LoginResponseDto login(LoginRequestDto loginRequestDto){

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken
                        (loginRequestDto.getUsername(),loginRequestDto.getPassword())
        );

        User user = (User) authentication.getPrincipal();

        String token = authUtil.generatedAccessToken(user);
        return new LoginResponseDto(token,user.getId());
    }

    public SignupResponseDto signup(LoginRequestDto signupRequestDto) {
        User user = userRepository.findByUsername(signupRequestDto.getUsername()).orElse(null);

        if(user!=null) throw new IllegalArgumentException("User already exists");

        User myUser = new User();
        myUser.setUsername(signupRequestDto.getUsername());
        myUser.setPassword(passwordEncoder.encode(signupRequestDto.getPassword()));

        User savedUser = userRepository.save(myUser);

        return new SignupResponseDto(
                savedUser.getId(),
                savedUser.getUsername()
        );
    }
}
