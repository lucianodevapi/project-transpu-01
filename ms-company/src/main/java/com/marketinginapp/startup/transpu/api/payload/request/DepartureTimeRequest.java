package com.marketinginapp.startup.transpu.api.payload.request;

import com.marketinginapp.startup.transpu.utils.Constant;
import jakarta.validation.constraints.NotNull;

public record DepartureTimeRequest(

        @NotNull(message = Constant.MUST_NOT_BE_NULL_OR_EMPTY )
        String time,

        boolean status,
        @NotNull(message = Constant.COMPANY_ID_MUST_NOT_BE_NULL )
        Long company,

        @NotNull(message = Constant.DAY_ID_MUST_NOT_BE_NULL )
        Long day
) {}
