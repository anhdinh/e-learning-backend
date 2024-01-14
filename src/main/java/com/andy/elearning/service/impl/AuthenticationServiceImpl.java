package com.andy.elearning.service.impl;

import com.andy.elearning.config.JwtUtils;
import com.andy.elearning.dto.RoleDto;
import com.andy.elearning.dto.payload.request.UserLoginRequest;
import com.andy.elearning.dto.payload.request.UserRegisterRequest;
import com.andy.elearning.dto.payload.response.UserLoginResponse;
import com.andy.elearning.dto.UserDto;
import com.andy.elearning.entity.Role;
import com.andy.elearning.entity.User;
import com.andy.elearning.entity.UserRole;
import com.andy.elearning.enums.RoleEnum;
import com.andy.elearning.exeptions.UsernameHasAlreadyExistedException;
import com.andy.elearning.repository.RoleRepository;
import com.andy.elearning.repository.UserRepository;
import com.andy.elearning.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
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
public class AuthenticationServiceImpl implements AuthenticationService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final ModelMapper modelMapper;
    private final AuthenticationManager authenticationManager;
    private final JwtUtils jwtUtils;

    @Override
    public UserLoginResponse login(UserLoginRequest loginRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        User userPrincipal = (User) authentication.getPrincipal();
        Map<String, Object> claims = new HashMap<>();
        SecurityContextHolder.getContext().setAuthentication(authentication);
        Set<String> roleDtoSet = userPrincipal.getAuthorities().stream().map(item ->item.getAuthority()).collect(Collectors.toSet());
        claims.put(JwtUtils.ROLES, roleDtoSet);
        String jwt = jwtUtils.generateToken(claims, userPrincipal.getUsername());
        UserLoginResponse response = UserLoginResponse.builder()
                .jwtToken(jwt).username(loginRequest.getUsername()).roles(roleDtoSet).refreshToken("non-set").build();
        return response;
    }

    @Transactional
    @Override
    public UserDto register(UserRegisterRequest registerRequest) {
        UserDto userDto = null;
        Optional<User> existedUser = userRepository.findByUsername(registerRequest.getUsername());
        if (!existedUser.isPresent()) {
            User newUser = new User();
            newUser.setName(registerRequest.getName());
            newUser.setUsername(registerRequest.getUsername());
            newUser.setPassword(passwordEncoder.encode(registerRequest.getPassword()));
            //TODO sending email and the active link to active the account
            newUser.setEnable(true);
            newUser.setAccountNonExpired(true);
            newUser.setAccountNonLocked(true);
            newUser.setCreateDate(LocalDateTime.now());
            newUser.setUpdateDate(LocalDateTime.now());

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

}
