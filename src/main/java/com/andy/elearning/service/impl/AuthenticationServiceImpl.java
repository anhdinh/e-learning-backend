package com.andy.elearning.service.impl;

import com.andy.elearning.config.JwtUtils;
import com.andy.elearning.dto.payload.request.UserLoginRequest;
import com.andy.elearning.dto.payload.request.UserRegisterRequest;
import com.andy.elearning.dto.payload.response.RefreshTokenResponse;
import com.andy.elearning.dto.payload.response.UserLoginResponse;
import com.andy.elearning.dto.UserDto;
import com.andy.elearning.entity.Role;
import com.andy.elearning.entity.User;
import com.andy.elearning.entity.UserRole;
import com.andy.elearning.enums.RoleEnum;
import com.andy.elearning.exeptions.InvalidJwtTokenException;
import com.andy.elearning.exeptions.UsernameHasAlreadyExistedException;
import com.andy.elearning.repository.RoleRepository;
import com.andy.elearning.repository.UserRepository;
import com.andy.elearning.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Log4j2
public class AuthenticationServiceImpl implements AuthenticationService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final ModelMapper modelMapper;
    private final AuthenticationManager authenticationManager;
    private final JwtUtils jwtUtils;

    @Override
    public UserLoginResponse login(UserLoginRequest loginRequest) {
        log.info("Login with username {} at {}",loginRequest.getUsername(),LocalDateTime.now());
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        User userPrincipal = (User) authentication.getPrincipal();
        Map<String, Object> claims = new HashMap<>();
        SecurityContextHolder.getContext().setAuthentication(authentication);
        Set<String> roleDtoSet = userPrincipal.getAuthorities().stream().map(item -> item.getAuthority()).collect(Collectors.toSet());
        claims.put(JwtUtils.ROLES, roleDtoSet);
        String jwt = jwtUtils.generateToken(claims, userPrincipal.getUsername());
        Date refreshTokenExpirationDate = createRefreshTokenExpirateDate();
        String refreshToken = jwtUtils.generateRefreshToken(userPrincipal.getUsername(), refreshTokenExpirationDate.getTime());
        UserLoginResponse response = UserLoginResponse.builder()
                .jwtToken(jwt).username(loginRequest.getUsername()).roles(roleDtoSet).refreshToken(refreshToken).build();
        return response;
    }

    private Date createRefreshTokenExpirateDate() {
        Calendar c = Calendar.getInstance();
        c.add(Calendar.DATE, 30);
        Date refreshTokenExpirationDate = c.getTime();
        return refreshTokenExpirationDate;
    }

    @Transactional
    @Override
    public UserDto register(UserRegisterRequest request) {
        UserDto userDto = null;
        Optional<User> existedUser = userRepository.findByUsername(request.getUsername());
        if (!existedUser.isPresent()) {
            User newUser = User.builder()
                    .name(request.getName())
                    .username(request.getUsername())
                    .password(passwordEncoder.encode(request.getPassword()))
                    .enable(true).accountNonExpired(true).accountNonLocked(true)
                    .createDate(LocalDateTime.now())
                    .updateDate(LocalDateTime.now())
                    .build();
            //TODO sending email and the active link to active the account
            Optional<Role> roleOptional = roleRepository.getRoleByName(RoleEnum.ROLE_USER.getValue());
            if (roleOptional.isPresent()) {
                UserRole userRole = new UserRole();
                userRole.setUser(newUser);
                userRole.setRole(roleOptional.get());
                newUser.setUserRoles(Collections.singleton(userRole));
            }
            newUser = userRepository.save(newUser);
            userDto = modelMapper.map(newUser, UserDto.class);
        } else {
            throw new UsernameHasAlreadyExistedException("Username already exists!");
        }
        return userDto;
    }

    @Transactional
    @Override
    public RefreshTokenResponse refreshToken(String refreshToken) {

        RefreshTokenResponse response = new RefreshTokenResponse();
        Map<String, Object> claims = new HashMap<>();

        if (!jwtUtils.validateJwtToken(refreshToken)) {
            throw new InvalidJwtTokenException("Invalid refresh token or refresh token expired");
        }

        String username = jwtUtils.extractUsername(refreshToken);
        Date eRefreshToken = jwtUtils.extractExpiration(refreshToken);
        Optional<User> userOptional = userRepository.findByUsername(username);

        if (userOptional.isPresent()) {
            User user = userOptional.get();
            Set<String> roles = user.getAuthorities().stream().map(item -> item.getAuthority()).collect(Collectors.toSet());
            claims.put(JwtUtils.ROLES, roles);
        }

        String jwt = jwtUtils.generateToken(claims, username);
        String newRefreshToken = jwtUtils.generateRefreshToken(username, eRefreshToken.getTime());
        response.setJwtToken(jwt);
        response.setRefreshToken(newRefreshToken);
        return response;
    }

}
