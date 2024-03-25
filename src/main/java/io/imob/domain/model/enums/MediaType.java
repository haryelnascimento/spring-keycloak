package io.imob.domain.model.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum MediaType {

    IMAGE(true),
    VIDEO(true),
    DOCUMENT(false);

    private final boolean publicAccess;

}
