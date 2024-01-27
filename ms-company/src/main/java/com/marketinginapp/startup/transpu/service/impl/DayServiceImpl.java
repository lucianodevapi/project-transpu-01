package com.marketinginapp.startup.transpu.service.impl;

import com.marketinginapp.startup.transpu.api.payload.request.DayRequest;
import com.marketinginapp.startup.transpu.api.payload.request.DayUpdateRequest;
import com.marketinginapp.startup.transpu.api.payload.response.DayResponse;
import com.marketinginapp.startup.transpu.domain.entity.Day;
import com.marketinginapp.startup.transpu.domain.entity.Route;
import com.marketinginapp.startup.transpu.domain.repository.CompanyRepository;
import com.marketinginapp.startup.transpu.domain.repository.DayRepository;
import com.marketinginapp.startup.transpu.domain.repository.RouteRepository;
import com.marketinginapp.startup.transpu.handler.exception.DuplicatedKeyException;
import com.marketinginapp.startup.transpu.handler.exception.MessageException;
import com.marketinginapp.startup.transpu.handler.exception.ObjectNotFoundException;
import com.marketinginapp.startup.transpu.service.DayService;
import com.marketinginapp.startup.transpu.utils.Constant;
import com.marketinginapp.startup.transpu.utils.ConverterEnum;
import com.marketinginapp.startup.transpu.utils.enums.EDay;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor

@Service
public class DayServiceImpl implements DayService {

    private final CompanyRepository companyRepository;
    private final RouteRepository routeRepository;
    private final DayRepository dayRepository;

    @Override
    public DayResponse save(DayRequest request) {
        EDay day = ConverterEnum.stringToEDay(request.name());
        Optional<Day> entityOptional = dayRepository.getByNameAndRouteAndCompany(day, request.route(), request.company());
        if(entityOptional.isPresent()){
            throw new DuplicatedKeyException(String.format(Constant.EXCEPTION_NAME_ALREADY_REGISTERED, request.name()));
        }
        Day entity = new Day();
        try{
            entity = dayRepository.save(toEntity(request));
        } catch (Exception exception){
            throw new MessageException(String.format(Constant.EXCEPTION_ENTITY_NOT_REGISTERED, "day", exception.getMessage()));
        }
        return toResponse(entity);
    }

    @Override
    public DayResponse update(DayUpdateRequest request) {
        Day entityExists = dayRepository.findById(request.id())
                .orElseThrow(() -> new ObjectNotFoundException(
                        String.format(Constant.EXCEPTION_ID_NOT_FOUND, request.id()))
                );
        Day routeSaved = new Day();
        try {
            Day entity = entityExists;
            if(!entity.getDescription().equals(request.description())){
                entity.setDescription(request.description());
            }
            routeSaved = dayRepository.save(entity);
        } catch (Exception exception){
            throw new MessageException( String.format(Constant.EXCEPTION_ENTITY_NOT_UPDATED, "day"));
        }
        return toResponse(routeSaved);
    }

    @Override
    public DayResponse findById(Long id) {
        Day entity = dayRepository.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException(
                        String.format(Constant.EXCEPTION_ID_NOT_FOUND, id))
                );
        return toResponse(entity);
    }

    @Override
    public DayResponse updateStatus(Long id) {
        Day entityExists = dayRepository.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException(
                        String.format(Constant.EXCEPTION_ID_NOT_FOUND, id))
                );
        Day routeSaved = new Day();
        try{
            Day entity = entityExists;
            entity.setStatus(!entity.isStatus());
            routeSaved = dayRepository.save(entity);
            return toResponse(routeSaved);
        } catch (Exception exception){
            throw new MessageException(Constant.EXCEPTION_STATUS_NOT_UPDATED);
        }
    }

    @Override
    public List<DayResponse> findAllByRouteId(Long route) {
        Route routeEntity = routeRepository.findById(route)
                .orElseThrow(() -> new ObjectNotFoundException(
                        String.format(Constant.EXCEPTION_ID_NOT_FOUND, route))
                );
        List<DayResponse> responses = new ArrayList<DayResponse>();
        try{
            dayRepository.findAllByRouteId(route)
                    .forEach(day -> { responses.add(toResponse(day)); });
            return responses;
        } catch (Exception exception){
            throw new MessageException(String.format(Constant.EXCEPTION_MESSAGE, exception.getMessage()));
        }
    }

    private Day toEntity(DayRequest request){
        Route route = routeRepository.findById(request.route())
                .orElseThrow(() -> new ObjectNotFoundException(
                        String.format(Constant.EXCEPTION_ID_NOT_FOUND, request.company()))
                );
        return Day.builder()
                .name(ConverterEnum.stringToEDay(request.name()))
                .description(request.description())
                .status(true)
                .company(route.getCompany())
                .route(route)
                .build();
    }

    private DayResponse toResponse(Day entity){
        return new DayResponse(
                entity.getId(),
                ConverterEnum.eDayoString(entity.getName()),
                entity.getDescription(),
                entity.isStatus(),
                entity.getCompany().getId(),
                entity.getRoute().getId()
                );
    }
}
