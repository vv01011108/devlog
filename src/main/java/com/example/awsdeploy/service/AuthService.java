package com.example.awsdeploy.service;

import com.example.awsdeploy.dto.LoginRequest;
import com.example.awsdeploy.dto.LoginResponse;
import com.example.awsdeploy.dto.SignupRequest;
import com.example.awsdeploy.entity.AppUser;
import com.example.awsdeploy.exception.DuplicateEmailException;
import com.example.awsdeploy.exception.InvaildLoginException;
import com.example.awsdeploy.repository.AppUserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AuthService {

    private final AppUserRepository appUserRepository;
    private final PasswordEncoder passwordEncoder;

    public AuthService(AppUserRepository appUserRepository, PasswordEncoder passwordEncoder) {
        this.appUserRepository = appUserRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional
    public AppUser signup(SignupRequest request) {
        if (appUserRepository.existsByEmail(request.email())) {
            throw new DuplicateEmailException();
        }

        String encodedPassword = passwordEncoder.encode(request.password());

        AppUser user = new AppUser(
                request.email(),
                encodedPassword,
                request.nickname()
        );

        return appUserRepository.save(user);
    }

    @Transactional(readOnly = true)
    public LoginResponse login(LoginRequest request) {
        AppUser user = appUserRepository.findByEmail(request.email())
                .orElseThrow(InvaildLoginException::new);

        if (!passwordEncoder.matches(request.password(), user.getPassword())) {
            throw new InvaildLoginException();
        }

        return LoginResponse.from(user);
    }
}
