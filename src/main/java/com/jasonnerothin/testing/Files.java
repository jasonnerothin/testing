package com.jasonnerothin.testing;

import java.io.File;
import java.io.IOException;

/**
 * Created by IntelliJ IDEA.
 * User: jason
 * Date: 11/11/14
 * Time: 10:54 AM
 */
public class Files {

    private static final int RANDOM_FILENAME_LENGTH = 16;

    private static final String COULD_NOT_CREATE_TEMP_FILE_ERROR
            = "Error creating temp file '%s'.";
    private static final String TEMP_DIRECTORY_DOES_NOT_EXIST_ERROR
            = "The temp directory used by this JVM does not exist: '%s'";
    private static final String TMP_DIR_PROPERTY_NAME = "java.io.tmpdir";
    private static final String INVALID_CWD_ERROR = "Invalid current working directory ('%s').";

    static final int RANDOM_FILENAME_ATTEMPT_LIMIT = 4;
    private static final String TOO_MANY_RANDOM_CWD_FILENAME_ATTEMPTS_ERROR
            = String.format("Error allocating randomly-named current working directory file. The %d random file names generated against '%s' all corresponded to existing files!", RANDOM_FILENAME_ATTEMPT_LIMIT, TMP_DIR_PROPERTY_NAME);
    static final String CWD_SYMBOLIC_PATH = "";
    private static final String TOO_MANY_RANDOM_TMP_FILENAME_ATTEMPTS_ERROR
            = String.format("Error allocating randomly-named temp file. The %d random file names generated against '%s' all corresponded to existing files!", RANDOM_FILENAME_ATTEMPT_LIMIT, CWD_SYMBOLIC_PATH);
    static final String TEMP_FILENAME_SUFFIX = ".com.jasonnerothin.testing.tmpfile";
    static final String TEMP_DIRNAME_SUFFIX = ".com.jasonnerothin.testing.tmpdir";
    private static final String COULD_NOT_CREATE_CWD_FILE_ERROR = "Error creating new test file '%s' in current working directory.";

    Strings strings = new Strings();

    private File tempDirectory;
    private File currentWorkingDirectory;

    public Files() {
        String tmpDirName = System.getProperty(TMP_DIR_PROPERTY_NAME);
        this.tempDirectory = new File(tmpDirName);
        assert tempDirectory.exists() : String.format(TEMP_DIRECTORY_DOES_NOT_EXIST_ERROR, tmpDirName);
        this.currentWorkingDirectory = new File(CWD_SYMBOLIC_PATH);
//        assert this.currentWorkingDirectory.isDirectory() && this.currentWorkingDirectory.exists() : String.format(INVALID_CWD_ERROR, this.currentWorkingDirectory.getAbsolutePath());
    }

    /**
     * @return an *abstract* temp file.
     */
    public File tempFile() {
        File tempFile = abstractTempFile();
        int attemptCount = 1;
        while (tempFile.exists() && attemptCount++ < RANDOM_FILENAME_ATTEMPT_LIMIT)
            tempFile = abstractTempFile();
        if (tempFile.exists()) {
            throw new IllegalStateException(TOO_MANY_RANDOM_TMP_FILENAME_ATTEMPTS_ERROR);
        }
        return tempFile;
    }

    private File abstractTempFile() {
        return randomFileInTemp(TEMP_FILENAME_SUFFIX);
    }

    private File randomFileInTemp(String filenameSuffix) {
        return new File(tempDirectory.getAbsoluteFile(), strings.alphabetic(RANDOM_FILENAME_LENGTH) + filenameSuffix);
    }

    /**
     * @return a newly-created temporary file in 'java.io.tmpdir' directory
     */
    public File createTempFile() {
        File tempFile = tempFile();
        String errorMsg = String.format(COULD_NOT_CREATE_TEMP_FILE_ERROR, tempFile.getName());
        IllegalStateException exception = null;
        boolean created = false;
        try {
            created = tempFile.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
            exception = new IllegalStateException(errorMsg, e);
        }
        if (!created) exception = new IllegalStateException(errorMsg);
        if (exception != null) throw exception;
        tempFile.deleteOnExit();
        return tempFile;
    }

    /**
     * @return a newly-created directory in 'java.io.tmpdir'
     */
    public File createDirectoryInTemp() {
        File directoryInTemp = directoryInTemp();
        if (!directoryInTemp.mkdir())
            throw new IllegalStateException(String.format(COULD_NOT_CREATE_TEMP_FILE_ERROR, directoryInTemp.getName()));
        directoryInTemp.deleteOnExit();
        return directoryInTemp;
    }

    /**
     * @return an *abstract* directory in 'java.io.tmpdir'
     */
    public File directoryInTemp() {
        return randomFileInTemp(strings.alphabetic(RANDOM_FILENAME_LENGTH) + TEMP_DIRNAME_SUFFIX);
    }

    /**
     * @return an *abstract* file in the current working directory
     */
    public File fileInCwd() {
        File cwdFile = abstractFileInCwd();
        int attemptCount = 1;
        while (cwdFile.exists() && attemptCount++ < RANDOM_FILENAME_ATTEMPT_LIMIT)
            cwdFile = abstractFileInCwd();
        if (cwdFile.exists()) {
            throw new IllegalStateException(TOO_MANY_RANDOM_CWD_FILENAME_ATTEMPTS_ERROR);
        }
        return cwdFile;
    }

    /**
     * @return a newly-created file in current working directory
     */
    public File createFileInCwd(){
        File cwdFile = fileInCwd();
        String errMsg = String.format(COULD_NOT_CREATE_CWD_FILE_ERROR, cwdFile.getAbsolutePath());
        IllegalStateException ise = null;
        try {
            if( !cwdFile.createNewFile() ) ise = new IllegalStateException(errMsg);
        } catch (IOException e) {
            e.printStackTrace();
            ise = new IllegalStateException(errMsg, e);
        }
        if( ise != null ) throw ise;
        cwdFile.deleteOnExit();
        return cwdFile;
    }

    private File abstractFileInCwd(){
        return new File(currentWorkingDirectory.getAbsoluteFile(), strings.alphabetic(RANDOM_FILENAME_LENGTH) + TEMP_FILENAME_SUFFIX);
    }
}
