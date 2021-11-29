package fileUtility;

import lombok.experimental.UtilityClass;

/**
 * Get path, folder name, file name
 * and file extension from different strings
 */
public final class PathAndFileUtility {
    private static String stringPath;

    public PathAndFileUtility(String stringPath) {
        PathAndFileUtility.stringPath = checkIsNullOrEmptyString(stringPath);
    }

    /**
     * Check if string is null.
     * If is null, return exception
     */
    private static String checkIsNullOrEmptyString(String stringToCheck) {
        if (stringToCheck == null){
            throw new NullPointerException("Null value is passed.");
        }
        if(stringToCheck.isEmpty()){
            throw new IllegalArgumentException("Empty value is passed.");
        }
        return stringToCheck;
    }

    /**
     * Check if string contains different types of slash.
     * Necessary for Windows path or Linux path
     */
    private static char getSlashType() {
        if (stringPath.contains("/")) {
            return '/';
        } else if (stringPath.contains("\\")) {
            return '\\';
        } else return 0;
    }

    /**
     * Get last index of slash in a given string
     */
    private static int getLastIndexOfSlash(String temporaryPath) {
        return temporaryPath.lastIndexOf(getSlashType());
    }

    /**
     * Get full path or relative path from given string
     */
    public static String getParentPath() {
       return getLastIndexOfSlash(stringPath) <= 0 ? "" : stringPath.substring(0, getLastIndexOfSlash(stringPath));
    }

    /**
     * Get folder name from given string
     */
    public static String getFolderName() {
        String parentPath = getParentPath();
        return parentPath.equals("") ? parentPath : parentPath.substring(getLastIndexOfSlash(parentPath) + 1, getLastIndexOfSlash(stringPath));
  }

    /**
     * Get file name from given string
     */
    public static String getFileName() {
        if (getExtensionDotIndex() > -1) {
            return compareSlashIndexAndDotIndex() ? stringPath.substring(getLastIndexOfSlash(stringPath) + 1) : stringPath.substring(getLastIndexOfSlash(stringPath) + 1, getExtensionDotIndex());
        } else return stringPath.substring(getLastIndexOfSlash(stringPath) + 1);
    }

    /**
     * Get file extension from given string
     */
    public static String getFileExtension() {
        if (getExtensionDotIndex() < 0 || compareSlashIndexAndDotIndex()) {
            return "";
        } else return stringPath.substring(getExtensionDotIndex() + 1);
    }

    /**
     * Additional method to compare slash index and file extension dot index
     */
    private static boolean compareSlashIndexAndDotIndex() {
        return getLastIndexOfSlash(stringPath) >= 0 && getLastIndexOfSlash(stringPath) + 1 == getExtensionDotIndex();
    }

    /**
     * Get last index of dot from given string
     */
    private static int getExtensionDotIndex(){
        return stringPath.lastIndexOf(".");
    }
}
