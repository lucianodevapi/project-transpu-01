package com.marketinginapp.startup.transpu.api.payload.response;

public record PathResponse(
        Long id,
        String city,
        String district,
        String address,
        boolean status,
        Long company,
        Long route
) {}
