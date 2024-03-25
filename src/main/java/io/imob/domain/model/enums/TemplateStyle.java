package io.imob.domain.model.enums;

import lombok.Getter;

@Getter
public enum TemplateStyle {

    BLACK("Black"),
    BLUE("Blue"),
    RED("Red"),
    TEAL_COLOR("Teal color"),
    GREEN_STRONG("Green strong");

    private final String descriptionEnum;

    TemplateStyle(String desc) {
        this.descriptionEnum = desc;
    }
}
