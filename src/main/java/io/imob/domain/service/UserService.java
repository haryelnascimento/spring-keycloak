package io.imob.domain.service;

import io.imob.domain.exception.BusinessException;
import io.imob.domain.exception.EntityNotFoundException;
import io.imob.domain.model.Group;
import io.imob.domain.model.User;
import io.imob.domain.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserService {

    private static final String MESSAGE_USER_NOT_FOUND = "Não existe um cadastro de usuário com código %d";
    private static final String MESSAGE_PASSWORD_NOT_MATCHES = "Senha atual informada não coincide com a senha do usuário.";
    private static final String MESSAGE_EXISTS_USER_BY_EMAIL = "Já existe um usuário cadastrado com o e-mail %s";

    private final UserRepository userRepository;
    private final GroupService groupService;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public User save(User user) {
        userRepository.detach(user);
        Optional<User> userSave = userRepository.findByEmail(user.getEmail());

        if (userSave.isPresent() && !userSave.get().equals(user)) {
            throw new BusinessException(String.format(MESSAGE_EXISTS_USER_BY_EMAIL, user.getEmail()));
        }

        if (user.isNew()) {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
        }

        return userRepository.save(user);
    }

    @Transactional
    public void updatePassword(UUID id, String currentPassword, String newPassword) {
        User user = isExists(id);

        if (!passwordEncoder.matches(currentPassword, user.getPassword())) {
            throw new BusinessException(MESSAGE_PASSWORD_NOT_MATCHES);
        }

        user.setPassword(passwordEncoder.encode(newPassword));
    }

    @Transactional
    public void addGroup(UUID userId, UUID groupId) {
        User user = isExists(userId);
        Group group = groupService.isExists(groupId);

        user.addGroup(group);
    }

    @Transactional
    public void removerGroup(UUID userId, UUID groupId) {
        User user = isExists(userId);
        Group group = groupService.isExists(groupId);

        user.removeGroup(group);
    }

    public User isExists(UUID id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(String.format(MESSAGE_USER_NOT_FOUND, id)));
    }
}
