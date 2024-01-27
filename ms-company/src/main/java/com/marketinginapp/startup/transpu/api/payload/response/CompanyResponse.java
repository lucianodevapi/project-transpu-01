package com.marketinginapp.startup.transpu.api.payload.response;

import com.fasterxml.jackson.annotation.JsonProperty;

public record CompanyResponse(
        Long id,
        String name,
        String code,
        String profile,
        String description,
        @JsonProperty("type_service")
        String typeService,
        String status
) {}
