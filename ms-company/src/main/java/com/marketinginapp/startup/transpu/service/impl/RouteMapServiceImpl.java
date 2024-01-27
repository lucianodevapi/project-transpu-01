package com.marketinginapp.startup.transpu.service.impl;

import com.marketinginapp.startup.transpu.api.payload.request.RouteMapRequest;
import com.marketinginapp.startup.transpu.api.payload.request.RouteMapUpdateRequest;
import com.marketinginapp.startup.transpu.api.payload.response.RouteMapResponse;
import com.marketinginapp.startup.transpu.domain.entity.Route;
import com.marketinginapp.startup.transpu.domain.entity.RouteMap;
import com.marketinginapp.startup.transpu.domain.repository.RouteMapRepository;
import com.marketinginapp.startup.transpu.domain.repository.RouteRepository;
import com.marketinginapp.startup.transpu.handler.exception.MessageException;
import com.marketinginapp.startup.transpu.handler.exception.ObjectNotFoundException;
import com.marketinginapp.startup.transpu.service.RouteMapService;
import com.marketinginapp.startup.transpu.utils.Constant;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor

@Service
public class RouteMapServiceImpl implements RouteMapService {

    private final RouteRepository routeRepository;
    private final RouteMapRepository routeMapRepository;

    @Override
    public RouteMapResponse save(RouteMapRequest request) {
        RouteMap entity = new RouteMap();
        try{
            entity = routeMapRepository.save(toEntity(request));
        } catch (Exception exception){
            throw new MessageException(String.format(Constant.EXCEPTION_ENTITY_NOT_REGISTERED, "route map", exception.getMessage()));
        }
        return toResponse(entity);
    }

    @Override
    public RouteMapResponse update(RouteMapUpdateRequest request) {
        RouteMap optionalEntity = routeMapRepository.findById(request.id())
                .orElseThrow(() -> new ObjectNotFoundException(
                        String.format(Constant.EXCEPTION_ID_NOT_FOUND, request.id()))
                );
        try {
            RouteMap routeSaved = new RouteMap();
            RouteMap entity = optionalEntity;
            if(entity.getLatitude() != request.latitude()){
                entity.setLatitude(request.latitude());
            }
            if(entity.getLongitude() != request.longitude()){
                entity.setLongitude(request.longitude());
            }
            entity.setGo(request.go());
            routeSaved = routeMapRepository.save(entity);
            return toResponse(routeSaved);
        } catch (Exception exception){
            throw new MessageException( String.format(Constant.EXCEPTION_ENTITY_NOT_UPDATED, "route map" ));
        }
    }

    @Override
    public RouteMapResponse findById(Long id) {
        RouteMap entity = routeMapRepository.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException(
                        String.format(Constant.EXCEPTION_ID_NOT_FOUND, id))
                );
        return toResponse(entity);
    }

    @Override
    public RouteMapResponse updateStatus(Long id) {
        RouteMap optionalEntity = routeMapRepository.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException(
                        String.format(Constant.EXCEPTION_ID_NOT_FOUND, id))
                );
        RouteMap routeSaved = new RouteMap();
        try{
            RouteMap entity = optionalEntity;
            entity.setStatus(!entity.isStatus());
            routeSaved = routeMapRepository.save(entity);
            return toResponse(routeSaved);
        } catch (Exception exception){
            throw new MessageException(Constant.EXCEPTION_STATUS_NOT_UPDATED);
        }
    }

    @Override
    public List<RouteMapResponse> findAllByRoute(Long route) {
        Route routeEntity = routeRepository.findById(route)
                .orElseThrow(() -> new ObjectNotFoundException(
                        String.format(Constant.EXCEPTION_ID_NOT_FOUND, route))
                );
        List<RouteMapResponse> responses = new ArrayList<RouteMapResponse>();
        try{
            List<RouteMap> routes = routeMapRepository.findAllByRouteId(route);
            routes.forEach(routeMap -> {responses.add(toResponse(routeMap));});
            return responses;
        } catch (Exception exception){
            throw new MessageException(String.format(Constant.EXCEPTION_MESSAGE, exception.getMessage()));
        }
    }

    private RouteMap toEntity(RouteMapRequest request){
        Route route = routeRepository.findById(request.route())
                .orElseThrow(() -> new ObjectNotFoundException(
                        String.format(Constant.EXCEPTION_ID_NOT_FOUND, request.company()))
                );
        return RouteMap.builder()
                .latitude(request.latitude())
                .longitude(request.longitude())
                .go(request.go())
                .status(true)
                .company(route.getCompany())
                .route(route)
                .build();
    }

    private RouteMapResponse toResponse(RouteMap entity){
        return new RouteMapResponse(
                entity.getId(),
                entity.getLatitude(),
                entity.getLongitude(),
                entity.isGo(),
                entity.isStatus(),
                entity.getCompany().getId(),
                entity.getRoute().getId());
    }
}
