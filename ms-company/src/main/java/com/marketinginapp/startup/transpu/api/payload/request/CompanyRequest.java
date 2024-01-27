package com.marketinginapp.startup.transpu.api.payload.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.marketinginapp.startup.transpu.utils.Constant;
import com.marketinginapp.startup.transpu.utils.validator.TrimString;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record CompanyRequest(

    @TrimString
    @NotBlank(message = Constant.MUST_NOT_BE_NULL_OR_EMPTY)
    @Size(min = 3, max = 50,  message = Constant.MUST_BE_BETWEEN_3_AND_50_CHARACTERS)
    @JsonProperty("name")
    String name,

    @TrimString
    @NotBlank(message = Constant.MUST_NOT_BE_NULL_OR_EMPTY)
    @Size(max = 40,  message = Constant.MAXIMUM_LENGTH_40_CHARACTERS)
    @JsonProperty("code")
    String code,

    String profile,

    @TrimString
    @Size(max = 250,  message = Constant.MAXIMUM_LENGTH_250_CHARACTERS)
    @JsonProperty("description")
    String description,

    @JsonProperty("type_service")
    String typeService,

    String status,

    @NotNull(message = Constant.USER_ID_MUST_NOT_BE_NULL )
    @JsonProperty("user_id")
    Long idUser
) {}
