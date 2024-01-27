package com.marketinginapp.startup.transpu.api.payload.request;

import com.marketinginapp.startup.transpu.utils.Constant;
import jakarta.validation.constraints.NotBlank;

public record CompanyAddRequest(
        @NotBlank(message = Constant.MUST_NOT_BE_NULL_OR_EMPTY)
        Long idUser,

        @NotBlank(message = Constant.MUST_NOT_BE_NULL_OR_EMPTY)
        Long idCompany
) {}
