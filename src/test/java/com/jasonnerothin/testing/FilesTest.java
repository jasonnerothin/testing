package com.jasonnerothin.testing;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.io.File;

import static org.junit.Assert.*;
import static org.mockito.Matchers.isA;
import static org.mockito.Mockito.when;

/**
 * Created by IntelliJ IDEA.
 * User: jason
 * Date: 11/11/14
 * Time: 11:23 AM
 */
@RunWith(MockitoJUnitRunner.class)
public class FilesTest {

    private Files testInstance;
    private File tempDir;

    @Mock
    private Strings mockStrings;

    @Before
    public void setup() {
        testInstance = new Files();
        tempDir = new File(System.getProperty("java.io.tmpdir"));
    }

    @Test(expected = IllegalStateException.class)
    public void testTempFileFailsEventually() throws Exception {

        final int howManyDefinesEventually = Files.RANDOM_FILENAME_ATTEMPT_LIMIT + 1;

        // create temp files that will exist in the "random" location chosen by the createTemp file algorithm
        Strings strings = new Strings();
        for (int i = 0; i < howManyDefinesEventually; i++) {
            String str = strings.alphabetic();
            String fileName = str + Files.TEMP_FILENAME_SUFFIX;
            when(mockStrings.alphabetic(isA(Integer.class))).thenReturn(str);
            if (!new File(tempDir, fileName).createNewFile()) {
                throw new IllegalStateException(String.format("Invalid test. Need file '%s' to be created for test to be valid.", fileName));
            }
        }
        testInstance.strings = mockStrings;

        testInstance.tempFile();

        fail("Should have failed with an IllegalStateException by now.");

    }

    @Test
    public void testCreateTempFileExistsAfterCreation() throws Exception{
        assertTrue(testInstance.createTempFile().exists());
    }

    @Test
    public void testCreateTempFileCreatesAFileThatDidNotExistToStartWith() throws Exception{

        Strings testStrings = new Strings();
        String str = testStrings.alphabetic();
        when(mockStrings.alphabetic(isA(Integer.class))).thenReturn(str);
        testInstance.strings = mockStrings;

        File expected = new File(tempDir, str + Files.TEMP_FILENAME_SUFFIX );
        assertFalse(expected.exists());

        assertTrue(testInstance.createTempFile().exists());

    }

    @Test
    public void testCreateDirectoryInTempExistsAfterCreation() throws Exception{
        assertTrue(testInstance.createDirectoryInTemp().exists());
    }

    @Test
    public void testCreateDirectoryInTempCreatesADirectoryThatDidNotExistToStartWith() throws Exception{

        Strings testStrings = new Strings();
        String str = testStrings.alphabetic();
        when(mockStrings.alphabetic(isA(Integer.class))).thenReturn(str);
        testInstance.strings = mockStrings;

        File expected = new File(tempDir, str + Files.TEMP_DIRNAME_SUFFIX );
        assertFalse(expected.exists());

        assertTrue(testInstance.createDirectoryInTemp().exists());

    }

    @Test
    public void testFileInCwdDoesNotOverwriteExisting() throws Exception{

        Strings testStrings = new Strings();
        String str = testStrings.alphabetic();
        when(mockStrings.alphabetic(isA(Integer.class))).thenReturn(str).thenReturn(testStrings.alphabetic());
        testInstance.strings = mockStrings;

        File interferingFile = new File(new File(Files.CWD_SYMBOLIC_PATH).getAbsolutePath(), str + Files.TEMP_FILENAME_SUFFIX );
        if( !interferingFile.createNewFile() )
            throw new IllegalStateException(String.format("Need interfering '%s' file to conflict with the one testInstance wants to create for this test to work.", interferingFile.getAbsolutePath()));
        interferingFile.deleteOnExit();

        File actual = testInstance.fileInCwd();

        assertNotEquals(actual.getAbsolutePath(), interferingFile.getAbsolutePath());
    }

    @Test
    public void testFileInCwdIsAbstract() throws Exception{

        File actual = testInstance.fileInCwd();
        assertFalse("Generated file reference should be abstract, not actual.", actual.exists());

    }

    @Test
    public void testCreateFileInCwdCreatesAFile(){

        assertTrue(testInstance.createFileInCwd().exists());

    }


}
