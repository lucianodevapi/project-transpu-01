package com.marketinginapp.startup.transpu.api.payload.request;

import com.marketinginapp.startup.transpu.utils.Constant;
import com.marketinginapp.startup.transpu.utils.validator.TrimString;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record RouteByNameRequest(

        @NotBlank(message = Constant.MUST_NOT_BE_NULL_OR_EMPTY)
        Long idCompany,

        @TrimString
        @NotBlank(message = Constant.MUST_NOT_BE_NULL_OR_EMPTY)
        @Size(message = Constant.MAXIMUM_LENGTH_10_CHARACTERS)
        String name
) {}
