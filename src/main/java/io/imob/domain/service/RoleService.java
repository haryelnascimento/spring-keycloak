package io.imob.domain.service;

import io.imob.domain.exception.BusinessException;
import io.imob.domain.model.Role;
import io.imob.domain.repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class RoleService {

    private final RoleRepository roleRepository;

    public Role isExists(UUID id) {
        return roleRepository.findById(id).orElseThrow(() -> new BusinessException("Permissão não encontrada"));
    }

}
