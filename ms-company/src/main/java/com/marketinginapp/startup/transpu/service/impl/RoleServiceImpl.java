package com.marketinginapp.startup.transpu.service.impl;

import com.marketinginapp.startup.transpu.handler.exception.DuplicatedKeyException;
import com.marketinginapp.startup.transpu.handler.exception.MessageException;
import com.marketinginapp.startup.transpu.utils.Constant;
import com.marketinginapp.startup.transpu.api.payload.request.RoleRequest;
import com.marketinginapp.startup.transpu.api.payload.response.RoleResponse;
import com.marketinginapp.startup.transpu.domain.entity.Role;
import com.marketinginapp.startup.transpu.domain.repository.RoleRepository;
import com.marketinginapp.startup.transpu.handler.exception.ObjectNotFoundException;
import com.marketinginapp.startup.transpu.service.RoleService;
import com.marketinginapp.startup.transpu.utils.ConverterEnum;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@RequiredArgsConstructor

@Service
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;

    @Override
    public RoleResponse save(RoleRequest request) {
        Optional<Role> roleEntity = roleRepository.findByName(ConverterEnum.stringToERole(request.name()));
        if(roleEntity.isPresent()){
            throw new DuplicatedKeyException(String.format(Constant.EXCEPTION_NAME_ALREADY_REGISTERED, request.name()));
        } else {
            Role entity = new Role();
            try{
                entity = roleRepository.save(toEntity(request));
            } catch (Exception exception){
                throw new MessageException(String.format(Constant.EXCEPTION_ENTITY_NOT_REGISTERED_BY_NAME, "role", exception.getMessage()));
            }
            return toResponse(entity);
        }
    }

    @Override
    public RoleResponse update(RoleRequest request, Long id) {
        Role roleSaved = new Role();
        Role optionalEntity = roleRepository.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException(
                        String.format(Constant.EXCEPTION_ID_NOT_FOUND, id))
                );
        try{
            Role entity = optionalEntity;
            if(!request.name().isEmpty()){
                entity.setName(ConverterEnum.stringToERole(request.name()));
            }
            roleSaved = roleRepository.save(entity);
        } catch (Exception exception){
            throw new MessageException(String.format(Constant.EXCEPTION_ENTITY_NOT_UPDATED, "role"));
        }
        return toResponse(roleSaved);
    }

    @Override
    public RoleResponse findById(Long id) {
        Role entity = roleRepository.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException(String.format(Constant.EXCEPTION_ID_NOT_FOUND, id)));
        return toResponse(entity);
    }

    private Role toEntity(RoleRequest request){
        return Role.builder()
                .name(ConverterEnum.stringToERole(request.name()))
                .build();
    }
    private RoleResponse toResponse(Role entity){
        return new RoleResponse(entity.getId(), ConverterEnum.eRoleToString(entity.getName()));
    }
}
