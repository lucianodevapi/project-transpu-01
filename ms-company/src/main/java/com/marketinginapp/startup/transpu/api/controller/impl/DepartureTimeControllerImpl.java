package com.marketinginapp.startup.transpu.api.controller.impl;

import com.marketinginapp.startup.transpu.api.controller.DepartureTimeController;
import com.marketinginapp.startup.transpu.api.payload.request.DepartureTimeRequest;
import com.marketinginapp.startup.transpu.api.payload.response.DayResponse;
import com.marketinginapp.startup.transpu.api.payload.response.DepartureTimeResponse;
import com.marketinginapp.startup.transpu.service.DepartureTimeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor

@RestController
@RequestMapping(value = "/api/v1/departure_time")
public class DepartureTimeControllerImpl implements DepartureTimeController {

    private final DepartureTimeService service;

    @Override
    public ResponseEntity<?> save(DepartureTimeRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.save(request));
    }

    @Override
    public ResponseEntity<?> update(DepartureTimeRequest request, Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(service.update(request, id));
    }

    @Override
    public ResponseEntity<?> findById(Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(service.findById(id));
    }

//    @Override
//    public ResponseEntity<Boolean> copy(DepartureTimeCopyRequest request) {
//        service.copy(request);
//        return ResponseEntity.status(HttpStatus.OK).body(true);
//    }

    @Override
    public ResponseEntity<?> updateStatus(Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(service.updateStatus(id));
    }

    @Override
    public ResponseEntity<?> findAllByCompanyId(Long day) {
        List<DepartureTimeResponse> responses = service.findAllByDay(day);
        if(!responses.isEmpty()){
            return ResponseEntity.status(HttpStatus.OK).body(responses);
        }
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
