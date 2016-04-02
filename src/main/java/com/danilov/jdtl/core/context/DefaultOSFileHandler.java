package com.danilov.jdtl.core.context;


import com.danilov.jdtl.core.util.TextUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

import static com.danilov.jdtl.core.util.TextUtils.format;

/**
 * Created by Semyon on 02.04.2016.
 */
public class DefaultOSFileHandler implements FileHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(DefaultOSFileHandler.class);

    private final String projectPath;

    public DefaultOSFileHandler(final String projectPath) {
        this.projectPath = projectPath;
    }

    @Override
    public InputStream getFile(final String path) {
        try {
            return new FileInputStream(projectPath + path);
        } catch (FileNotFoundException e) {
            LOGGER.error(format("Failed to read file {}", path), e);
            return null;
        }
    }

}
