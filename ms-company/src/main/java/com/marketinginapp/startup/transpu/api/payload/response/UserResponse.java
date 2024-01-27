package com.marketinginapp.startup.transpu.api.payload.response;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.Set;

public record UserResponse(
        Long id,

        String username,

        String email,

        @JsonInclude(JsonInclude.Include.NON_NULL)
        String profile,

        boolean status,

        Set<String> role
) {}
