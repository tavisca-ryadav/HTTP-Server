public class URLParser {
    public String extractFileName(String recievedURL) {
        String[] split = recievedURL.split(" ");
        return split[1].substring(1);
    }

    public String extractTypeOfFile(String fileName) {
        String fileType = "";
        String[] splits = fileName.split("\\.");
        fileType = splits[1];
        //System.out.println(fileType);
        return fileType;
    }
}
