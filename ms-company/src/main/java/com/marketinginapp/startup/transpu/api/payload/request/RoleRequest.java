package com.marketinginapp.startup.transpu.api.payload.request;

import com.marketinginapp.startup.transpu.utils.Constant;
import com.marketinginapp.startup.transpu.utils.validator.TrimString;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record RoleRequest(

        @TrimString
        @NotBlank(message = Constant.MUST_NOT_BE_NULL_OR_EMPTY)
        @Size(min = 3, max = 20,  message = Constant.MUST_BE_BETWEEN_3_AND_20_CHARACTERS)
        String name
) {}
