package com.marketinginapp.startup.transpu.api.payload.request;

import com.marketinginapp.startup.transpu.utils.Constant;
import com.marketinginapp.startup.transpu.utils.validator.TrimString;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record RouteRequest(

        @TrimString
        @NotBlank(message = Constant.MUST_NOT_BE_NULL_OR_EMPTY)
        @Size(max = 10,  message = Constant.MAXIMUM_LENGTH_10_CHARACTERS)
        String name,

        @TrimString
        @NotBlank(message = Constant.MUST_NOT_BE_NULL_OR_EMPTY)
        @Size(max = 50,  message = Constant.MAXIMUM_LENGTH_50_CHARACTERS)
        String description,

        String status,
        @NotNull(message = Constant.COMPANY_ID_MUST_NOT_BE_NULL)
        Long company

) {
}
