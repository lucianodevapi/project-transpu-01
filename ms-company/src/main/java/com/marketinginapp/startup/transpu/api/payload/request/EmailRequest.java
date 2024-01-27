package com.marketinginapp.startup.transpu.api.payload.request;

import com.marketinginapp.startup.transpu.utils.Constant;
import com.marketinginapp.startup.transpu.utils.validator.TrimString;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record EmailRequest(

        @NotBlank(message = Constant.MUST_NOT_BE_NULL_OR_EMPTY)
        Long id,

        @TrimString
        @NotBlank(message = Constant.MUST_NOT_BE_NULL_OR_EMPTY)
        @Size(max = 70, message = Constant.MAXIMUM_LENGTH_70_CHARACTERS)
        @Email(message = Constant.MUST_BE_ONE_VALID_EMAIL)
        String email
) {}
