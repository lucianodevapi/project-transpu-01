package com.marketinginapp.startup.transpu.api.payload.response;

public record DepartureTimeResponse(

        Long id,

        String time,

        boolean status,

        Long day
) { }
