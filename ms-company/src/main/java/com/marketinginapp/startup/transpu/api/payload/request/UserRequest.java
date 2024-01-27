package com.marketinginapp.startup.transpu.api.payload.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.marketinginapp.startup.transpu.utils.Constant;
import com.marketinginapp.startup.transpu.utils.validator.TrimString;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.util.Set;

public record UserRequest(
        @TrimString
        @NotBlank(message = Constant.MUST_NOT_BE_NULL_OR_EMPTY)
        @Size(min = 3, max = 50,  message = Constant.MUST_BE_BETWEEN_3_AND_50_CHARACTERS)
        @JsonProperty("name")
        String username,

        @TrimString
        @NotBlank(message = Constant.MUST_NOT_BE_NULL_OR_EMPTY)
        @Size(max = 70, message = Constant.MAXIMUM_LENGTH_70_CHARACTERS)
        @Email(message = Constant.MUST_BE_ONE_VALID_EMAIL)
        String email,

        @TrimString
        @NotBlank(message = Constant.MUST_NOT_BE_NULL_OR_EMPTY)
        @Size(min = 6, max = 30,  message = Constant.MUST_BE_BETWEEN_6_AND_30_CHARACTERS)
        String password,

        String profile,

        @JsonProperty("roles")
        Set<String> role
) {
}
