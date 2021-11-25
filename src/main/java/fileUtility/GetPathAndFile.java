package fileUtility;

/*
 * Get path, folder name, file name
 * and file extension from different strings
 */
public class GetPathAndFile {
    String stringPath;
    String fullPath;
    char slashType;
    int lastSlashIndex;
    int firstSlashIndex;
    int extensionDotIndex;

    public GetPathAndFile(String stringPath) {
        this.stringPath = stringPath;
        this.slashType = this.getSlashType();
        this.lastSlashIndex = this.getLastIndexOfSlash(stringPath);
        this.firstSlashIndex = stringPath.indexOf(slashType);
        this.extensionDotIndex = stringPath.lastIndexOf(".");
        this.fullPath = this.getFullPath();
    }

    /*
     * Check if string contains different types of slash.
     * Necessary for Windows path or Linux path
     */
    char getSlashType() {
        if (stringPath.contains("/")) {
            return '/';
        } else if (stringPath.contains("\\")) {
            return '\\';
        } else return 0;
    }

    /*
     *Get last index of slash in a given string
     */
    public int getLastIndexOfSlash(String temporaryPath) {
        return temporaryPath.lastIndexOf(slashType);
    }

    /*
     *Get full path or relative path from given string
     */
    public String getFullPath() {
        return lastSlashIndex <= 0 ? "Relative path or root directory" : stringPath.substring(0, lastSlashIndex);
    }

    /*
     *Get folder name from given string
     */
    public String getFolderName() {
        return fullPath.equals("Relative path or root directory") ? fullPath : fullPath.substring(getLastIndexOfSlash(fullPath) + 1, lastSlashIndex);
    }

    /*
     *Get file name from given string
     */
    public String getFileName() {
        if (extensionDotIndex > -1) {
            return compareSlashIndexAndDotIndex() ? stringPath.substring(lastSlashIndex + 1) : stringPath.substring(lastSlashIndex + 1, extensionDotIndex);
        } else return stringPath.substring(lastSlashIndex + 1);
    }

    /*
     *Get file extension from given string
     */
    public String getFileExtension() {
        if (extensionDotIndex < 0 || compareSlashIndexAndDotIndex()) {
            return "";
        } else return stringPath.substring(extensionDotIndex + 1);
    }

    /*
     * Additional method to compare slash index and file extension dot index
     */
    public boolean compareSlashIndexAndDotIndex() {
        return lastSlashIndex >= 0 && lastSlashIndex + 1 == extensionDotIndex;
    }
}
