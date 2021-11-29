package fileUtility;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test class for different types of strings
 */
public class PathAndFileUtilityTest {

    @Test
    public void testNullPath() {
        assertThrowsExactly(NullPointerException.class, () -> new PathAndFileUtility(null));
    }

    @Test
    public void testEmptyPath() {
        assertThrowsExactly(IllegalArgumentException.class, () -> new PathAndFileUtility(""));
    }

    @Test
    public void testSpacePath() {
        PathAndFileUtility pathAndFile = new PathAndFileUtility(" ");
        assertEquals("", pathAndFile.getParentPath());
        assertEquals(" ", pathAndFile.getFileName());
        assertEquals("", pathAndFile.getFileExtension());
        assertEquals("", pathAndFile.getFolderName());
   }

    @Test
    public void testRelativePath() {
        PathAndFileUtility pathAndFile = new PathAndFileUtility("temp.txt");
        assertEquals("", pathAndFile.getParentPath());
        assertEquals("temp", pathAndFile.getFileName());
        assertEquals("txt", pathAndFile.getFileExtension());
        assertEquals("", pathAndFile.getFolderName());
    }

    @Test
    public void testRelativeCurrentPath() {
        PathAndFileUtility pathAndFile = new PathAndFileUtility("./temp.txt");
        assertEquals(".", pathAndFile.getParentPath());
        assertEquals("temp", pathAndFile.getFileName());
        assertEquals("txt", pathAndFile.getFileExtension());
        assertEquals(".", pathAndFile.getFolderName());
    }

    @Test
    public void testRelativeCurrentPathForWindows() {
        PathAndFileUtility pathAndFile = new PathAndFileUtility("FolderName\\test_file.txt");
        assertEquals("FolderName", pathAndFile.getParentPath());
        assertEquals("test_file", pathAndFile.getFileName());
        assertEquals("txt", pathAndFile.getFileExtension());
        assertEquals("FolderName", pathAndFile.getFolderName());
    }

    @Test
    public void testRelativeParentPath() {
        PathAndFileUtility pathAndFile = new PathAndFileUtility("../temp.txt");
        assertEquals("..", pathAndFile.getParentPath());
        assertEquals("temp", pathAndFile.getFileName());
        assertEquals("txt", pathAndFile.getFileExtension());
        assertEquals("..", pathAndFile.getFolderName());
    }

    @Test
    public void testRelativeParentPathForWindows() {
        PathAndFileUtility pathAndFile = new PathAndFileUtility("..\\FolderName\\testFile.pdf");
        assertEquals("..\\FolderName", pathAndFile.getParentPath());
        assertEquals("testFile", pathAndFile.getFileName());
        assertEquals("pdf", pathAndFile.getFileExtension());
        assertEquals("FolderName", pathAndFile.getFolderName());
    }

    @Test
    public void testPeriodInFileName() {
        PathAndFileUtility pathAndFile = new PathAndFileUtility("/home/root/tmp/test.text.file.txt");
        assertEquals("/home/root/tmp", pathAndFile.getParentPath());
        assertEquals("test.text.file", pathAndFile.getFileName());
        assertEquals("txt", pathAndFile.getFileExtension());
        assertEquals("tmp", pathAndFile.getFolderName());
    }

    @Test
    public void testPeriodInFileNameForWindows() {
        PathAndFileUtility pathAndFile = new PathAndFileUtility("D:\\FirstFolderName\\SecondFolderName\\test.text.file.txt");
        assertEquals("D:\\FirstFolderName\\SecondFolderName", pathAndFile.getParentPath());
        assertEquals("test.text.file", pathAndFile.getFileName());
        assertEquals("txt", pathAndFile.getFileExtension());
        assertEquals("SecondFolderName", pathAndFile.getFolderName());
    }

    @Test
    public void testPeriodInFolderName() {
        PathAndFileUtility pathAndFile = new PathAndFileUtility("/home/root/tmp.1/test.txt");
        assertEquals("/home/root/tmp.1", pathAndFile.getParentPath());
        assertEquals("test", pathAndFile.getFileName());
        assertEquals("txt", pathAndFile.getFileExtension());
        assertEquals("tmp.1", pathAndFile.getFolderName());
    }

    @Test
    public void testPeriodInFolderNameForWindows() {
        PathAndFileUtility pathAndFile = new PathAndFileUtility("D:\\FirstFolder.Name\\Second.FolderName\\test.txt");
        assertEquals("D:\\FirstFolder.Name\\Second.FolderName", pathAndFile.getParentPath());
        assertEquals("test", pathAndFile.getFileName());
        assertEquals("txt", pathAndFile.getFileExtension());
        assertEquals("Second.FolderName", pathAndFile.getFolderName());
    }

    @Test
    public void testFileWithoutExtension() {
        PathAndFileUtility pathAndFile = new PathAndFileUtility("/etc/hosts");
        assertEquals("/etc", pathAndFile.getParentPath());
        assertEquals("hosts", pathAndFile.getFileName());
        assertEquals("", pathAndFile.getFileExtension());
        assertEquals("etc", pathAndFile.getFolderName());
    }

    @Test
    public void testFileWithoutExtensionForWindows() {
        PathAndFileUtility pathAndFile = new PathAndFileUtility("D:\\SecondFolderName\\test");
        assertEquals("D:\\SecondFolderName", pathAndFile.getParentPath());
        assertEquals("test", pathAndFile.getFileName());
        assertEquals("", pathAndFile.getFileExtension());
        assertEquals("SecondFolderName", pathAndFile.getFolderName());
    }

    @Test
    public void testHiddenFile() {
        PathAndFileUtility pathAndFile = new PathAndFileUtility("/home/root/.bash_history");
        assertEquals("/home/root", pathAndFile.getParentPath());
        assertEquals(".bash_history", pathAndFile.getFileName());
        assertEquals("", pathAndFile.getFileExtension());
        assertEquals("root", pathAndFile.getFolderName());
    }
}