package com.danilov.jdtl.core.context;

import java.io.InputStream;

/**
 * Created by Semyon on 02.04.2016.
 */
public interface FileHandler {

    InputStream getFile(final String path);

}
