package com.marketinginapp.startup.transpu.service.impl;

import com.marketinginapp.startup.transpu.api.payload.request.PathRequest;
import com.marketinginapp.startup.transpu.api.payload.request.PathUpdateRequest;
import com.marketinginapp.startup.transpu.api.payload.response.PathResponse;
import com.marketinginapp.startup.transpu.domain.entity.Path;
import com.marketinginapp.startup.transpu.domain.entity.Route;
import com.marketinginapp.startup.transpu.domain.repository.PathRepository;
import com.marketinginapp.startup.transpu.domain.repository.RouteRepository;
import com.marketinginapp.startup.transpu.handler.exception.MessageException;
import com.marketinginapp.startup.transpu.handler.exception.ObjectNotFoundException;
import com.marketinginapp.startup.transpu.service.PathService;
import com.marketinginapp.startup.transpu.utils.Constant;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor

@Service
public class PathServiceImpl implements PathService {

    private final RouteRepository routeRepository;
    private final PathRepository pathRepository;

    @Override
    public PathResponse save(PathRequest request) {
        try{
            Path entity = new Path();
            if(pathNotExists(request)){
                entity = pathRepository.save(toEntity(request));
            }
            return toResponse(entity);
        } catch (Exception exception){
            throw new MessageException(String.format(Constant.EXCEPTION_ENTITY_NOT_REGISTERED, "path", exception.getMessage()));
        }
    }

    @Override
    public PathResponse update(PathUpdateRequest request) {
        Path path = pathRepository.findById(request.id())
                .orElseThrow(() -> new ObjectNotFoundException(
                        String.format(Constant.EXCEPTION_ID_NOT_FOUND, request.id()))
                );
        Path pathSaved = new Path();
        try {
            Path entity = path;
            if(!path.getCity().equals(request.city())){
                path.setCity(request.city());
            }
            if(!path.getDistrict().equals(request.district())){
                path.setDistrict(request.district());
            }
            pathSaved = pathRepository.save(entity);
        } catch (Exception exception){
            throw new MessageException(String.format(Constant.EXCEPTION_ENTITY_NOT_UPDATED, "path"));
        }
        return toResponse(pathSaved);
    }

    @Override
    public PathResponse findById(Long id) {
        Path entity = pathRepository.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException(
                        String.format(Constant.EXCEPTION_ID_NOT_FOUND, id))
                );
        return toResponse(entity);
    }

    @Override
    public PathResponse updateStatus(Long id) {
        Path path = pathRepository.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException(
                        String.format(Constant.EXCEPTION_ID_NOT_FOUND, id))
                );
        Path pathSaved = new Path();
        try{
            Path entity = path;
            entity.setStatus(!entity.isStatus());
            pathSaved = pathRepository.save(entity);
            return toResponse(pathSaved);
        } catch (Exception exception){
            throw new MessageException(Constant.EXCEPTION_STATUS_NOT_UPDATED);
        }
    }

    @Override
    public List<PathResponse> findAllByRoute(Long route) {
        Route routeEntity = routeRepository.findById(route).orElseThrow(
                () -> new ObjectNotFoundException(
                String.format(Constant.EXCEPTION_ID_NOT_FOUND, route))
        );
        List<PathResponse> responses = new ArrayList<PathResponse>();
        try{
            pathRepository.findAllByRouteIdOrderByIdAsc(route).forEach( path -> responses.add(toResponse(path)));
            return responses;
        } catch (Exception exception){
            throw new MessageException(String.format(Constant.EXCEPTION_MESSAGE, exception.getMessage()));
        }
    }

    private boolean pathNotExists(PathRequest request){
        try{
            List<Path> findAllByAddress = pathRepository.findAllByAddress(
                    request.company(),
                    request.route(),
                    request.city(),
                    request.district(),
                    request.address());
            return findAllByAddress.isEmpty();
        }catch (Exception exception){
            throw new MessageException(String.format(Constant.EXCEPTION_ENTITY_NOT_REGISTERED, "path", exception.getMessage()));
        }
    }

    private Path toEntity(PathRequest request){
        Route route= routeRepository.findById(request.route())
                .orElseThrow(() -> new ObjectNotFoundException(
                        String.format(Constant.EXCEPTION_ID_NOT_FOUND, request.route()))
                );
        return Path.builder()
                .city(request.city())
                .district(request.district())
                .address(request.address())
                .status(request.status())
                .company(route.getCompany())
                .route(route)
                .build();
    }

    private PathResponse toResponse(Path entity){
        return new PathResponse(
                entity.getId(),
                entity.getCity(),
                entity.getDistrict(),
                entity.getAddress(),
                entity.isStatus(),
                entity.getCompany().getId(),
                entity.getRoute().getId());
    }
}
