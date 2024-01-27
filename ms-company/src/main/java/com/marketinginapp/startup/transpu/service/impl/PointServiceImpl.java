package com.marketinginapp.startup.transpu.service.impl;

import com.marketinginapp.startup.transpu.api.payload.request.PointIdParamRequest;
import com.marketinginapp.startup.transpu.api.payload.request.PointRequest;
import com.marketinginapp.startup.transpu.api.payload.request.PointUpdateRequest;
import com.marketinginapp.startup.transpu.api.payload.response.PointResponse;
import com.marketinginapp.startup.transpu.domain.entity.Path;
import com.marketinginapp.startup.transpu.domain.entity.Point;
import com.marketinginapp.startup.transpu.domain.entity.Route;
import com.marketinginapp.startup.transpu.domain.repository.PathRepository;
import com.marketinginapp.startup.transpu.domain.repository.PointRepository;
import com.marketinginapp.startup.transpu.domain.repository.RouteRepository;
import com.marketinginapp.startup.transpu.handler.exception.MessageException;
import com.marketinginapp.startup.transpu.handler.exception.ObjectNotFoundException;
import com.marketinginapp.startup.transpu.service.PointService;
import com.marketinginapp.startup.transpu.utils.Constant;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor

@Service
public class PointServiceImpl implements PointService {

    private final RouteRepository routeRepository;
    private final PathRepository pathRepository;
    private final PointRepository pointRepository;

    @Override
    public PointResponse save(PointRequest request) {
        Point entity = new Point();
        try{
            entity = pointRepository.save(toEntity(request));
        } catch (Exception exception){
            throw new MessageException(String.format(Constant.EXCEPTION_ENTITY_NOT_REGISTERED, "point", exception.getMessage()));
        }
        return toResponse(entity);
    }

    @Override
    public PointResponse update(PointUpdateRequest request) {
        Point point= pointRepository.findById(request.id())
                .orElseThrow(() -> new ObjectNotFoundException(
                        String.format(Constant.EXCEPTION_ID_NOT_FOUND, request.id()))
                );
        Point pointSaved = new Point();
        try {
            Point entity = point;
            if(entity.getLatitude() != request.latitude()){
                entity.setLatitude(request.latitude());
            }
            if(entity.getLongitude() != request.longitude()){
                entity.setLongitude(request.longitude());
            }
            pointSaved = pointRepository.save(entity);
        } catch (Exception exception){
            throw new MessageException(String.format( Constant.EXCEPTION_ENTITY_NOT_UPDATED, "point"));
        }

        return toResponse(pointSaved);
    }

    @Override
    public PointResponse findById(Long id) {
        Point entity = pointRepository.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException(
                        String.format(Constant.EXCEPTION_ID_NOT_FOUND, id))
                );
        return toResponse(entity);
    }

    @Override
    public PointResponse updateStatus(Long id) {
        Point point = pointRepository.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException(
                        String.format(Constant.EXCEPTION_ID_NOT_FOUND, id))
                );
        Point pointSaved = new Point();
        try{
            Point entity = point;
            entity.setStatus(!entity.isStatus());
            pointSaved = pointRepository.save(entity);
            return toResponse(pointSaved);
        } catch (Exception exception){
            throw new MessageException(Constant.EXCEPTION_STATUS_NOT_UPDATED);
        }
    }

    @Override
    public List<PointResponse> findAllByRoute(Long route) {
        routeRepository.findById(route)
                .orElseThrow(() -> new ObjectNotFoundException(
                String.format(Constant.EXCEPTION_ID_NOT_FOUND, route))
        );
        List<PointResponse> responses = new ArrayList<PointResponse>();
        try{
            pointRepository.findAllByRouteIdOrderByIdAsc(route)
                    .forEach(point -> {responses.add(toResponse(point));});
            return responses;
        } catch (Exception exception){
            throw new MessageException(Constant.EXCEPTION_STATUS_NOT_UPDATED);
        }
    }

    @Override
    public List<PointResponse> findAllByRouteAndDistrict(PointIdParamRequest request) {
        routeRepository.findById(request.id())
                .orElseThrow(() -> new ObjectNotFoundException(
                        String.format(Constant.EXCEPTION_ID_NOT_FOUND, request.id()))
                );
        List<PointResponse> responses = new ArrayList<PointResponse>();
        try{
            List<Point> points = pointRepository.findAllByRouteIdAndDistrictOrderByIdAsc(request.id(), request.param());
            points.forEach(point -> {responses.add(toResponse(point));});
            return responses;
        } catch (Exception exception){
            throw new MessageException(String.format(Constant.EXCEPTION_MESSAGE, exception.getMessage()));
        }
    }

    @Override
    public List<PointResponse> findAllByRouteAndAddress(PointIdParamRequest request) {
        routeRepository.findById(request.id())
                .orElseThrow(() -> new ObjectNotFoundException(
                        String.format(Constant.EXCEPTION_ID_NOT_FOUND, request.id()))
                );
        List<PointResponse> responses = new ArrayList<PointResponse>();
        try{
            List<Point> points = pointRepository.findAllByRouteIdAndAddressOrderByIdAsc(request.id(), request.param());
            points.forEach(point -> {responses.add(toResponse(point));});
            return responses;
        } catch (Exception exception){
            throw new MessageException(String.format(Constant.EXCEPTION_MESSAGE, exception.getMessage()));
        }
    }

    private Point toEntity(PointRequest request){
        Route route = routeRepository.findById(request.route())
                .orElseThrow(() -> new ObjectNotFoundException(
                        String.format(Constant.EXCEPTION_ID_NOT_FOUND, request.route()))
                );
        Optional<Path> path = pathRepository.findById(request.path());
        return Point.builder()
                .latitude(request.latitude())
                .longitude(request.longitude())
                .go(request.go())
                .company(route.getCompany())
                .route(route)
                .path(path.orElse(null))
                .build();
    }

    private PointResponse toResponse(Point entity){
        return new  PointResponse(
                entity.getId(),
                entity.getLatitude(),
                entity.getLongitude(),
                entity.isGo(),
                entity.isStatus(),
                entity.getCompany().getId(),
                entity.getRoute().getId(),
                entity.getPath() != null ? entity.getPath().getId() : 0 );
    }
}
