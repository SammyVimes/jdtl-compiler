package com.danilov.jdtl.core.context;

import javax.annotation.Nonnull;
import java.io.IOException;
import java.io.InputStream;
import static  com.danilov.jdtl.core.util.TextUtils.format;

/**
 * Created by Semyon on 02.04.2016.
 */
public class Context {

    private FileHandler fileHandler;

    private final String projectPath;

    public Context(final String projectPath) {
        this.projectPath = projectPath;
        fileHandler = new DefaultOSFileHandler(projectPath);
    }

    @Nonnull
    public InputStream getFile(final String path) throws IOException {
        InputStream file = fileHandler.getFile(path);
        if (file == null) {
            throw new IOException(format("File {} not found", path));
        }
        return file;
    }

    @Nonnull
    public String getLocalPath(final String path) {
        return path;
    }

    public void setFileHandler(final FileHandler fileHandler) {
        this.fileHandler = fileHandler;
    }

}