import fileUtility.GetPathAndFile;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
/*
 * Test class for different strings
 */
public class GetPathAndFileTest {

    GetPathAndFile pathForWindowsFile = new GetPathAndFile("D:\\FirstFolderName\\SecondFolderName\\.some_file.txt");
    GetPathAndFile pathForLinuxFile = new GetPathAndFile("/home/user/some_file.txt");
    GetPathAndFile relativePath = new GetPathAndFile(".test.txt");
    GetPathAndFile rootPath = new GetPathAndFile("/.test_1.txt");
    GetPathAndFile fileWithoutExtensionWindows = new GetPathAndFile("D:\\.test__2");

    @Test
    public void testGetFullPath(){
        String expectedForWindows = "D:\\FirstFolderName\\SecondFolderName";
        String expectedForLinux = "/home/user";
        String expectedForRelativePathOrRoot = "Relative path or root directory";
        String expectedForFileWithoutExtensionWindows = "D:";

        assertEquals(expectedForLinux, pathForLinuxFile.getFullPath());
        assertEquals(expectedForWindows, pathForWindowsFile.getFullPath());
        assertEquals(expectedForRelativePathOrRoot, relativePath.getFullPath());
        assertEquals(expectedForRelativePathOrRoot, rootPath.getFullPath());

        assertEquals(expectedForFileWithoutExtensionWindows, fileWithoutExtensionWindows.getFullPath());
    }

    @Test
    public void testGetFolderName() {
        String expectedForWindows = "SecondFolderName";
        String expectedForLinux = "user";
        String expectedForRelativePathOrRoot = "Relative path or root directory";
        String expectedForFileWithoutExtensionWindows = "D:";

        assertEquals(expectedForLinux, pathForLinuxFile.getFolderName());
        assertEquals(expectedForWindows, pathForWindowsFile.getFolderName());
        assertEquals(expectedForRelativePathOrRoot, relativePath.getFolderName());
        assertEquals(expectedForRelativePathOrRoot, rootPath.getFolderName());

        assertEquals(expectedForFileWithoutExtensionWindows, fileWithoutExtensionWindows.getFolderName());
    }

    @Test
    public void testGetFileName() {
        String expectedOnWindows = ".some_file";
        String expectedOnLinux = "some_file";
        String expectedForRelativePath = ".test";
        String expectedForRootPath = ".test_1";
        String expectedForFileWithoutExtension = ".test__2";

        assertEquals(expectedOnWindows, pathForWindowsFile.getFileName());
        assertEquals(expectedOnLinux, pathForLinuxFile.getFileName());
        assertEquals(expectedForRelativePath, relativePath.getFileName());
        assertEquals(expectedForRootPath, rootPath.getFileName());

        assertEquals(expectedForFileWithoutExtension, fileWithoutExtensionWindows.getFileName());
    }

    @Test
    public void testGetFileExtension() {
        String expectedForFileWithExtension = "txt";
        String expectedForFileWithoutExtension = "";

        assertEquals(expectedForFileWithExtension, pathForWindowsFile.getFileExtension());
        assertEquals(expectedForFileWithExtension, pathForLinuxFile.getFileExtension());
        assertEquals(expectedForFileWithExtension, relativePath.getFileExtension());
        assertEquals(expectedForFileWithExtension, rootPath.getFileExtension());

        assertEquals(expectedForFileWithoutExtension, fileWithoutExtensionWindows.getFileExtension());
    }
}