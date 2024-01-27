package com.marketinginapp.startup.transpu.service.impl;

import com.marketinginapp.startup.transpu.api.payload.request.RouteRequest;
import com.marketinginapp.startup.transpu.api.payload.request.RouteUpdateRequest;
import com.marketinginapp.startup.transpu.api.payload.request.StatusRequest;
import com.marketinginapp.startup.transpu.api.payload.response.RouteResponse;
import com.marketinginapp.startup.transpu.domain.entity.Company;
import com.marketinginapp.startup.transpu.domain.entity.Route;
import com.marketinginapp.startup.transpu.domain.repository.CompanyRepository;
import com.marketinginapp.startup.transpu.domain.repository.RouteRepository;
import com.marketinginapp.startup.transpu.handler.exception.DuplicatedKeyException;
import com.marketinginapp.startup.transpu.handler.exception.MessageException;
import com.marketinginapp.startup.transpu.handler.exception.ObjectNotFoundException;
import com.marketinginapp.startup.transpu.service.RouteService;
import com.marketinginapp.startup.transpu.utils.Constant;
import com.marketinginapp.startup.transpu.utils.ConverterEnum;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor

@Service
public class RouteServiceImpl implements RouteService {

    private final CompanyRepository companyRepository;
    private final RouteRepository routeRepository;

    @Override
    public RouteResponse save(RouteRequest request) {
        Optional<Route> entityOptional = routeRepository.findByName(request.name());
        if(entityOptional.isPresent()){
            throw new DuplicatedKeyException(String.format(Constant.EXCEPTION_NAME_ALREADY_REGISTERED, request.name()));
        }
        Route entity = new Route();
        try{
            entity = routeRepository.save(toEntity(request));
        } catch (Exception exception){
            throw new MessageException(String.format(Constant.EXCEPTION_ENTITY_NOT_REGISTERED, "route", exception.getMessage()));
        }
        return toResponse(entity);
    }

    @Override
    public RouteResponse update(RouteUpdateRequest request) {
        Route optionalEntity = routeRepository.findById(request.id())
                .orElseThrow(() -> new ObjectNotFoundException(
                        String.format(Constant.EXCEPTION_ID_NOT_FOUND, request.id()))
                );
        Route routeSaved = new Route();
        try {
            Route entity = optionalEntity;
            if(!request.name().isEmpty()){
                entity.setName(request.name());
            }
            if(!request.description().isEmpty()){
                entity.setDescription(request.description());
            }
            routeSaved = routeRepository.save(entity);
            return toResponse(routeSaved);
        } catch (Exception exception){
            throw new MessageException( String.format(Constant.EXCEPTION_ENTITY_NOT_UPDATED, "route"));
        }
    }

    @Override
    public RouteResponse findById(Long id) {
        Route entity = routeRepository.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException(
                        String.format(Constant.EXCEPTION_ID_NOT_FOUND, id))
                );
        return toResponse(entity);
    }

    @Override
    public RouteResponse updateStatus(StatusRequest request) {
        Route optionalEntity = routeRepository.findById(request.id())
                .orElseThrow(() -> new ObjectNotFoundException(
                        String.format(Constant.EXCEPTION_ID_NOT_FOUND, request.id()))
                );
        Route routeSaved = new Route();
        try{
            Route entity = optionalEntity;
            entity.setStatus(ConverterEnum.stringToECompanyStatus(request.status()));
            routeSaved = routeRepository.save(entity);
            return toResponse(routeSaved);
        } catch (Exception exception){
            throw new MessageException(Constant.EXCEPTION_STATUS_NOT_UPDATED);
        }
    }

    @Override
    public List<RouteResponse> findAllByCompanyId(Long id) {
        Company optional = companyRepository.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException(
                        String.format(Constant.EXCEPTION_ID_NOT_FOUND, id))
                );
        List<RouteResponse> responses = new ArrayList<RouteResponse>();
        try{
            List<Route> list = routeRepository.getAllByCompany(id);
            list.forEach(role -> {
                responses.add(toResponse(role));
            });
            return responses;
        } catch (Exception exception){
            throw new MessageException(String.format(Constant.EXCEPTION_MESSAGE, exception.getMessage()));
        }
    }

    private Route toEntity(RouteRequest request){
        Company companyEntity = companyRepository.findById(request.company())
                .orElseThrow(() -> new ObjectNotFoundException(
                        String.format(Constant.EXCEPTION_ENTITY_NOT_FOUND, request.company()))
                );
        return Route.builder()
                .name(request.name())
                .description(request.description())
                .status(ConverterEnum.stringToECompanyStatus(request.status()))
                .company(companyEntity)
                .build();
    }
    private RouteResponse toResponse(Route entity){
        return RouteResponse.builder()
                .id(entity.getId())
                .name(entity.getName())
                .description(entity.getDescription())
                .status(ConverterEnum.eCompanyStatusToString(entity.getStatus()))
                .company(entity.getId())
                .build();
    }
}
