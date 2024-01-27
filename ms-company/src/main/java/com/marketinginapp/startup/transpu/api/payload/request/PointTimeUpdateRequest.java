package com.marketinginapp.startup.transpu.api.payload.request;

import com.marketinginapp.startup.transpu.utils.Constant;
import jakarta.validation.constraints.NotNull;

public record PointTimeUpdateRequest(

        @NotNull(message = Constant.MUST_NOT_BE_NULL_OR_EMPTY)
        Long id,
        @NotNull(message = Constant.MUST_NOT_BE_NULL_OR_EMPTY)
        String time
) {}
