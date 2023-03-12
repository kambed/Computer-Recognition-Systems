package backend.reader;

public enum FileType {
    SGM(new SgmReader());
    private final FileReader fileReader;

    FileType(FileReader sgmReader) {
        this.fileReader = sgmReader;
    }

    public FileReader getFileReader() {
        return fileReader;
    }
}
