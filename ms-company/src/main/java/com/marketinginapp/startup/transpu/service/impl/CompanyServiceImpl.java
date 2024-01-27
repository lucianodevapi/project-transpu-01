package com.marketinginapp.startup.transpu.service.impl;

import com.marketinginapp.startup.transpu.api.payload.request.CompanyRequest;
import com.marketinginapp.startup.transpu.api.payload.request.CompanyUpdateRequest;
import com.marketinginapp.startup.transpu.api.payload.request.StatusRequest;
import com.marketinginapp.startup.transpu.api.payload.response.CompanyResponse;
import com.marketinginapp.startup.transpu.domain.entity.Company;
import com.marketinginapp.startup.transpu.domain.entity.User;
import com.marketinginapp.startup.transpu.domain.repository.CompanyRepository;
import com.marketinginapp.startup.transpu.domain.repository.UserRepository;
import com.marketinginapp.startup.transpu.handler.exception.MessageException;
import com.marketinginapp.startup.transpu.handler.exception.ObjectNotFoundException;
import com.marketinginapp.startup.transpu.service.CompanyService;
import com.marketinginapp.startup.transpu.utils.Constant;
import com.marketinginapp.startup.transpu.utils.ConverterEnum;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.springframework.data.jpa.domain.AbstractPersistable_.id;

@RequiredArgsConstructor

@Service
public class CompanyServiceImpl implements CompanyService {

    private final CompanyRepository companyRepository;
    private final UserRepository userRepository;

    @Override
    public CompanyResponse save(CompanyRequest request) {
        Optional<Company> entityOptional = companyRepository.findByName(request.name());
        if(entityOptional.isPresent()){
            throw new ObjectNotFoundException(String.format(Constant.EXCEPTION_NAME_ALREADY_REGISTERED, request.name()));
        }
        User userOptional = userRepository.findById(request.idUser())
                .orElseThrow(() -> new ObjectNotFoundException(
                        String.format(Constant.EXCEPTION_ENTITY_NOT_FOUND_BY_ID, "user", request.idUser()))
                );
        Company companyEntity = new Company();
        User userEntity = userOptional;
        try{
            companyEntity = companyRepository.save(toEntity(request));
            userEntity.setCompany(companyEntity);
            userRepository.save(userEntity);
        } catch (Exception exception){
            throw new MessageException(String.format(Constant.EXCEPTION_ENTITY_NOT_REGISTERED, "company", exception.getMessage()));
        }
        return toResponse(companyEntity);
    }

    @Override
    public CompanyResponse update(CompanyUpdateRequest request) {
        Company optionalEntity = companyRepository.findById(request.id())
                .orElseThrow(() -> new ObjectNotFoundException(
                        String.format(Constant.EXCEPTION_ID_NOT_FOUND, request.id()))
                );
        Company companySaved = new Company();
        try {
            Company entity = optionalEntity;
            if(!request.name().isEmpty()){
                entity.setName(request.name());
            }
            if(!request.profile().isEmpty()){
                entity.setProfile(request.profile());
            }
            if(!request.code().isEmpty()){
                entity.setCode(request.code());
            }
            entity.setUpdatedAt(LocalDateTime.now());
            companySaved = companyRepository.save(entity);

        } catch (Exception exception){
            throw new MessageException(String.format(Constant.EXCEPTION_ENTITY_NOT_REGISTERED, "company", exception.getMessage()));
        }

        return toResponse(companySaved);
    }

    @Override
    public CompanyResponse findById(Long id) {
        Company entity = companyRepository.findById(id).orElseThrow(() -> new ObjectNotFoundException(
                String.format(Constant.EXCEPTION_ID_NOT_FOUND, id))
        );
        return toResponse(entity);
    }

    @Override
    public CompanyResponse updateStatus(StatusRequest request) {
        Company optionalEntity = companyRepository.findById(request.id()).orElseThrow(() -> new ObjectNotFoundException(
                String.format(Constant.EXCEPTION_ID_NOT_FOUND, request.id()))
        );
        Company companySaved = new Company();
        try{
            Company entity = optionalEntity;
            entity.setStatus(ConverterEnum.stringToECompanyStatus(request.status()));
            entity.setUpdatedAt(LocalDateTime.now());
            companySaved = companyRepository.save(entity);
            return toResponse(companySaved);
        } catch (Exception exception){
            throw new MessageException(Constant.EXCEPTION_STATUS_NOT_UPDATED);
        }
    }

    private Company toEntity(CompanyRequest request){
        return Company.builder()
                .name(request.name())
                .code(request.code())
                .profile(request.profile())
                .description(request.description())
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .typeService(ConverterEnum.stringToETypeService(request.typeService()))
                .status(ConverterEnum.stringToECompanyStatus(request.status()))
                .build();
    }

    private CompanyResponse toResponse(Company entity){
        return new CompanyResponse(
                entity.getId(),
                entity.getName(),
                entity.getCode(),
                entity.getProfile(),
                entity.getDescription(),
                ConverterEnum.eTypeServiceToString(entity.getTypeService()),
                ConverterEnum.eCompanyStatusToString(entity.getStatus()));
    }
}
