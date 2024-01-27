package com.marketinginapp.startup.transpu.api.payload.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.marketinginapp.startup.transpu.utils.Constant;
import com.marketinginapp.startup.transpu.utils.validator.TrimString;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record PathUpdateRequest(

        @NotNull(message = Constant.MUST_NOT_BE_NULL_OR_EMPTY)
        Long id,

        @TrimString
        @NotBlank(message = Constant.MUST_NOT_BE_NULL_OR_EMPTY)
        @Size(max = 80,  message = Constant.MAXIMUM_LENGTH_80_CHARACTERS)
        @JsonProperty("city")
        String city,

        @TrimString
        @NotBlank(message = Constant.MUST_NOT_BE_NULL_OR_EMPTY)
        @Size(max = 80,  message = Constant.MAXIMUM_LENGTH_80_CHARACTERS)
        @JsonProperty("district")
        String district
) {
}
