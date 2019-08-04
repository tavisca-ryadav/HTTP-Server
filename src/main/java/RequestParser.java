public class RequestParser {
    public String extractFilePath(String recievedURL) {
        String filePath="";
        String[] split = recievedURL.split(" ");
        filePath = split[1];
        return filePath;
    }

    public String extractTypeOfFile(String fileName) {
        String fileType = "";
        String[] splits = fileName.split("\\.");
        fileType = splits[1];
        return fileType;
    }
}
