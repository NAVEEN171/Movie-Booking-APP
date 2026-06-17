package com.naveen.movieticketplatform.service.auth;

import com.naveen.movieticketplatform.dto.auth.AuthResponse;
import com.naveen.movieticketplatform.dto.auth.LoginRequest;
import com.naveen.movieticketplatform.dto.auth.SignUpRequest;
import com.naveen.movieticketplatform.entity.Role;
import com.naveen.movieticketplatform.entity.User;
import com.naveen.movieticketplatform.enums.RoleName;
import com.naveen.movieticketplatform.repository.RoleRepository;
import com.naveen.movieticketplatform.repository.UserRepository;
import com.naveen.movieticketplatform.security.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;
    private final AuthenticationManager authenticationManager;

    public AuthResponse login(LoginRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );

        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new NoSuchElementException("User not found"));

        String accessToken = jwtUtil.generateAccessToken(user.getEmail());
        String refreshToken = jwtUtil.generateRefreshToken(user.getEmail());

        List<String> roles = user.getRoles().stream()
                .map(r -> r.getName().toString())
                .collect(Collectors.toList());

        return new AuthResponse(accessToken, refreshToken, user.getEmail(), roles);
    }

    public AuthResponse signUp(SignUpRequest request) {
        if (userRepository.findByEmail(request.getEmail()).isPresent()) {
            throw new IllegalStateException("Email already exists: " + request.getEmail());
        }

        Role userRole = roleRepository.findByName(RoleName.USER)
                .orElseThrow(() -> new NoSuchElementException("Role USER not found"));

        User user = new User();
        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        user.setEmail(request.getEmail());
        user.setPhoneNumber(request.getPhoneNumber());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setIsActive(true);
        user.getRoles().add(userRole);

        userRepository.save(user);

        String accessToken = jwtUtil.generateAccessToken(user.getEmail());
        String refreshToken = jwtUtil.generateRefreshToken(user.getEmail());

        return new AuthResponse(accessToken, refreshToken, user.getEmail(), List.of("USER"));
    }

    public AuthResponse refreshToken(String refreshToken) {
        if (!jwtUtil.isRefreshTokenValid(refreshToken)) {
            throw new SecurityException("Invalid refresh token");
        }

        String email = jwtUtil.extractEmailFromRefreshToken(refreshToken);

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new NoSuchElementException("User not found"));

        String newAccessToken = jwtUtil.generateAccessToken(email);

        List<String> roles = user.getRoles().stream()
                .map(r -> r.getName().toString())
                .collect(Collectors.toList());

        return new AuthResponse(newAccessToken, refreshToken, email, roles);
    }
}
