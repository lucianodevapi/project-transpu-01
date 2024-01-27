package com.marketinginapp.startup.transpu.service.impl;

import com.marketinginapp.startup.transpu.api.payload.request.CompanyAddRequest;
import com.marketinginapp.startup.transpu.api.payload.request.EmailRequest;
import com.marketinginapp.startup.transpu.api.payload.request.UserRequest;
import com.marketinginapp.startup.transpu.api.payload.request.UserUpdateRequest;
import com.marketinginapp.startup.transpu.api.payload.response.UserResponse;
import com.marketinginapp.startup.transpu.domain.entity.Company;
import com.marketinginapp.startup.transpu.domain.entity.Role;
import com.marketinginapp.startup.transpu.domain.entity.User;
import com.marketinginapp.startup.transpu.domain.repository.CompanyRepository;
import com.marketinginapp.startup.transpu.domain.repository.RoleRepository;
import com.marketinginapp.startup.transpu.domain.repository.UserRepository;
import com.marketinginapp.startup.transpu.handler.exception.DuplicatedKeyException;
import com.marketinginapp.startup.transpu.handler.exception.MessageException;
import com.marketinginapp.startup.transpu.handler.exception.ObjectNotFoundException;
import com.marketinginapp.startup.transpu.service.UserService;
import com.marketinginapp.startup.transpu.utils.Constant;
import com.marketinginapp.startup.transpu.utils.ConverterEnum;
import com.marketinginapp.startup.transpu.utils.enums.ERole;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@RequiredArgsConstructor

@Service
public class UserServiceImpl implements UserService {

    private final CompanyRepository companyRepository;
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    @Override
    public UserResponse save(UserRequest request) {
        if(userRepository.existsByEmail(request.email())){
            throw new DuplicatedKeyException(String.format(Constant.EXCEPTION_EMAIL_ALREADY_REGISTERED, request.email()));
        }
        User entity = new User();
        try{
            entity = userRepository.save(toEntity(request));
        } catch (Exception exception){
            throw new MessageException(String.format(Constant.EXCEPTION_ENTITY_NOT_REGISTERED, "user", exception.getMessage()));
        }
        return toResponse(entity);
    }

    @Override
    public UserResponse update(UserUpdateRequest request) {
        User userSaved = new User();
        User optionalEntity = userRepository.findById(request.id()).orElseThrow(() -> new ObjectNotFoundException(
                String.format(Constant.EXCEPTION_ID_NOT_FOUND, request.id()))
        );
        try{
            User entity = optionalEntity;
            if(!request.username().isEmpty()){
                entity.setUsername(request.username());
            }
            if(!request.profile().isEmpty()){
                entity.setProfile(request.profile());
            }
            entity.setUpdatedAt(LocalDateTime.now());
            userSaved = userRepository.save(entity);
        } catch (Exception exception){
            throw new MessageException(String.format(Constant.EXCEPTION_ENTITY_NOT_UPDATED_EXCEPTION, "user", exception.getMessage()));
        }
        return toResponse(userSaved);
    }

    @Override
    public UserResponse addCompany(CompanyAddRequest request) {
        User userEntity = userRepository.findById(request.idUser())
                .orElseThrow(() -> new ObjectNotFoundException(
                        String.format(Constant.EXCEPTION_ID_NOT_FOUND, request.idUser()))
                );
        Company companyEntity = companyRepository.findById(request.idCompany())
                .orElseThrow(() -> new ObjectNotFoundException(
                        String.format(Constant.EXCEPTION_ID_NOT_FOUND, request.idUser()))
                );
        User userSaved = new User();
        try{
            User entity = userEntity;
            entity.setCompany(companyEntity);
            entity.setUpdatedAt(LocalDateTime.now());
            userSaved = userRepository.save(entity);
        } catch (Exception exception){
            throw new MessageException(String.format(Constant.EXCEPTION_ENTITY_NOT_UPDATED, "user"));
        }
        return toResponse(userSaved);
    }

    @Override
    public UserResponse updateEmail(EmailRequest request) {
        return null;
    }

    @Override
    public UserResponse findById(Long id) {
        User entity = userRepository.findById(id).orElseThrow(() -> new ObjectNotFoundException(
                String.format(Constant.EXCEPTION_ID_NOT_FOUND, id))
        );
        return toResponse(entity);
    }

    @Override
    public UserResponse findByEmail(String email) {
        User entity = userRepository.findByEmail(email).orElseThrow(() -> new ObjectNotFoundException(
                String.format(Constant.EXCEPTION_EMAIL_NOT_FOUND, email))
        );
        return toResponse(entity);
    }

    @Override
    public UserResponse updateStatus(Long id) {
        User optionalEntity = userRepository.findById(id).orElseThrow(() -> new ObjectNotFoundException(
                String.format(Constant.EXCEPTION_ID_NOT_FOUND, id))
        );
        User userSaved = new User();
        try{
            User entity = optionalEntity;
            entity.setStatus(!entity.isStatus());
            userSaved=  userRepository.save(entity);
        }catch (Exception exception){
            throw new MessageException(String.format(Constant.EXCEPTION_ENTITY_NOT_UPDATED, "status"));
        }
        return toResponse(userSaved);
    }

    @Override
    public UserResponse updateUser(UserRequest request, Long id) {
        User optionalEntity = userRepository.findById(id).orElseThrow(() -> new ObjectNotFoundException(
                String.format(Constant.EXCEPTION_ID_NOT_FOUND, id))
        );
        User userSaved = new User();
        try{
            User entity = optionalEntity;
            if(!request.username().isEmpty()){
                entity.setUsername(request.username());
            }
            if(!request.profile().isEmpty()){
                entity.setProfile(request.profile());
            }
            entity.setUpdatedAt(LocalDateTime.now());
            userSaved = userRepository.save(entity);
        }catch (Exception exception){
            throw new MessageException(String.format(Constant.EXCEPTION_ENTITY_NOT_UPDATED, "user"));
        }
        return toResponse(userSaved);
    }

    private User toEntity(UserRequest request){
        return User.builder()
                .username(request.username())
                .email(request.email())
                .password(request.password())
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .profile(request.profile())
                .status(true)
                .roles(getRoles(request))
                .build();
    }

    private UserResponse toResponse(User entity){
        Set<String> roles = new HashSet<String>();
        entity.getRoles().forEach(role -> {
            roles.add(ConverterEnum.eRoleToString(role.getName()));
        });
        return new UserResponse(
                entity.getId(),
                entity.getUsername(),
                entity.getEmail(),
                entity.getProfile(),
                entity.isStatus(),
                roles
        );
    }

    private Set<Role> getRoles(UserRequest request) {
        Set<String> strRoles = request.role();
        Set<Role> roles = new HashSet<>();

        if (strRoles == null) {
            Role userRole = roleRepository.findByName(ERole.ROLE_USER)
                    .orElseThrow(() -> new MessageException(String.format(Constant.EXCEPTION_ENTITY_NOT_FOUND, "role")));
            roles.add(userRole);
        } else {
            strRoles.forEach(role -> {
                switch (role) {
                    case "admin":
                        Role adminRole = roleRepository.findByName(ERole.ROLE_ADMIN)
                                .orElseThrow(() -> new MessageException(String.format(Constant.EXCEPTION_ENTITY_NOT_FOUND, "role")));
                        roles.add(adminRole);
                        break;
                    case "mod":
                        Role modRole = roleRepository.findByName(ERole.ROLE_MODERATOR)
                                .orElseThrow(() -> new MessageException(String.format(Constant.EXCEPTION_ENTITY_NOT_FOUND, "role")));
                        roles.add(modRole);
                        break;
                    case "driver":
                        Role driverRole = roleRepository.findByName(ERole.ROLE_DRIVER)
                                .orElseThrow(() -> new MessageException(String.format(Constant.EXCEPTION_ENTITY_NOT_FOUND, "role")));
                        roles.add(driverRole);
                        break;
                    default:
                        Role userRole = roleRepository.findByName(ERole.ROLE_USER)
                                .orElseThrow(() -> new MessageException(String.format(Constant.EXCEPTION_ENTITY_NOT_FOUND, "role")));
                        roles.add(userRole);
                }
            });
        }
        return  roles;
    }
}
