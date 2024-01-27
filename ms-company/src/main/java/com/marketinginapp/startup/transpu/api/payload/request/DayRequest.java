package com.marketinginapp.startup.transpu.api.payload.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.marketinginapp.startup.transpu.utils.Constant;
import com.marketinginapp.startup.transpu.utils.validator.TrimString;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record DayRequest(

        @TrimString
        @NotBlank(message = Constant.MUST_NOT_BE_NULL_OR_EMPTY)
        @Size(max = 10,  message = Constant.MAXIMUM_LENGTH_10_CHARACTERS)
        @JsonProperty("name")
        String name,

        @TrimString
        @Size(max = 70,  message = Constant.MAXIMUM_LENGTH_70_CHARACTERS)
        @JsonProperty("description")
        String description,

        @NotNull(message = Constant.COMPANY_ID_MUST_NOT_BE_NULL )
        Long company,

        @NotNull(message = Constant.ROUTE_ID_MUST_NOT_BE_NULL )
        Long route
) {}
