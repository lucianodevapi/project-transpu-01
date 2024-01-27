package com.marketinginapp.startup.transpu.api.payload.response;

public record DayResponse(
        long id,
        String name,
        String description,
        boolean status,
        Long company,
        Long route
) {}
