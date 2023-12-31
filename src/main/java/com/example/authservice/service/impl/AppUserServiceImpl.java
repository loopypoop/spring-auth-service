package com.example.authservice.service.impl;

import com.example.authservice.domain.AppUserEntity;
import com.example.authservice.domain.dto.AppUserDTO;
import com.example.authservice.domain.mapper.AppUserMapper;
import com.example.authservice.repository.AppUserRepository;
import com.example.authservice.service.AppUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AppUserServiceImpl implements AppUserService {

    private final AppUserRepository appUserRepository;
    private final AppUserMapper appUserMapper;

    @Override
    public UserDetailsService userDetailsService() {
        return username -> appUserRepository.findByEmail(username).orElseThrow(
                () -> new IllegalArgumentException("User not found!")
        );
    }

    @Override
    public ResponseEntity<List<AppUserDTO>> getAll() {
        return ResponseEntity.ok(appUserMapper.toDto(appUserRepository.findAll()));
    }
}
