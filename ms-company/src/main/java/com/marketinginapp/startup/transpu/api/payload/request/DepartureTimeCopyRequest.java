package com.marketinginapp.startup.transpu.api.payload.request;

import com.marketinginapp.startup.transpu.utils.Constant;
import jakarta.validation.constraints.NotBlank;

public record DepartureTimeCopyRequest(

        @NotBlank(message = Constant.MUST_NOT_BE_NULL_OR_EMPTY)
        Long dayOriginId,

        @NotBlank(message = Constant.MUST_NOT_BE_NULL_OR_EMPTY)
        Long dayDestineId
) {}
