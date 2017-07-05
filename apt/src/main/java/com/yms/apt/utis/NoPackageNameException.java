package com.yms.apt.utis;

import javax.lang.model.element.TypeElement;

/**
 * Created by LDD on 17/4/21.
 */

public class NoPackageNameException extends Exception {

    public NoPackageNameException(TypeElement typeElement) {
        super("The package of " + typeElement.getSimpleName() + " has no name");
    }
}