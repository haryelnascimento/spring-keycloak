package io.imob.domain.service;

import io.imob.domain.exception.EntityInUseException;
import io.imob.domain.exception.EntityNotFoundException;
import io.imob.domain.model.Group;
import io.imob.domain.model.Role;
import io.imob.domain.repository.GroupRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class GroupService {

    private static final String MESSAGE_GROUP_NOT_FOUND = "Não existe um cadastro de grupo com código %d";
    private static final String MESSAGE_GROUP_IN_USE = "Grupo de código %d não pode ser removido, pois está em uso";

    private final GroupRepository groupRepository;
    private final RoleService roleService;

    @Transactional
    public Group save(Group group) {
        return groupRepository.save(group);
    }

    @Transactional
    public void delete(UUID id) {
        try {
            groupRepository.deleteById(id);
            groupRepository.flush();

        } catch (EmptyResultDataAccessException e) {
            throw new EntityNotFoundException(String.format(MESSAGE_GROUP_NOT_FOUND, id));

        } catch (DataIntegrityViolationException e) {
            throw new EntityInUseException(String.format(MESSAGE_GROUP_IN_USE, id));
        }
    }

    @Transactional
    public void addRole(UUID groupId, UUID roleId) {
        Group group = isExists(groupId);
        Role role = roleService.isExists(roleId);

        group.addRole(role);
    }

    @Transactional
    public void removeRole(UUID groupId, UUID roleId) {
        Group group = isExists(groupId);
        Role role = roleService.isExists(roleId);

        group.removeRole(role);
    }

    public Group isExists(UUID id) {
        return groupRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(String.format(MESSAGE_GROUP_NOT_FOUND, id)));
    }

}
