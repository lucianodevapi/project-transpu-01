package com.marketinginapp.startup.transpu.api.payload.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.marketinginapp.startup.transpu.utils.Constant;
import com.marketinginapp.startup.transpu.utils.validator.TrimString;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record CompanyUpdateRequest(

        @NotNull(message = Constant.MUST_NOT_BE_NULL_OR_EMPTY)
        Long id,

        @TrimString
        @NotBlank(message = Constant.MUST_NOT_BE_NULL_OR_EMPTY)
        @Size(min = 3, max = 50,  message = Constant.MUST_BE_BETWEEN_3_AND_50_CHARACTERS)
        @JsonProperty("name")
        String name,

        String code,

        String profile
) {}
