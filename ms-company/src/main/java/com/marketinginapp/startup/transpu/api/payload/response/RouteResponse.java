package com.marketinginapp.startup.transpu.api.payload.response;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RouteResponse {

    private Long id;
    private String name;
    private String description;
    private String status;
    private Long company;
}
