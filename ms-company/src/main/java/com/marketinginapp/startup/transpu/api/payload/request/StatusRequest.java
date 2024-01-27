package com.marketinginapp.startup.transpu.api.payload.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.marketinginapp.startup.transpu.utils.Constant;
import jakarta.validation.constraints.NotBlank;

public record StatusRequest(

    @NotBlank(message = Constant.MUST_NOT_BE_NULL_OR_EMPTY)
    @JsonProperty("id")
    Long id,

    @NotBlank(message = Constant.MUST_NOT_BE_NULL_OR_EMPTY)
    String status

) {}
