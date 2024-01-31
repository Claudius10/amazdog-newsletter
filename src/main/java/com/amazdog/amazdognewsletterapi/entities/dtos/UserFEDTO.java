package com.amazdog.amazdognewsletterapi.entities.dtos;

import com.amazdog.amazdognewsletterapi.entities.user.Role;

import java.util.List;

public record UserFEDTO(Long id, String name, String email, List<Role> roles, boolean isEnabled) {
}
