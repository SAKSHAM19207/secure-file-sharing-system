package file;

import java.time.LocalDateTime;

public class FileMetadata {
    public String owner;
    public String filename;
    public LocalDateTime time;

    public FileMetadata(String owner, String filename) {
        this.owner = owner;
        this.filename = filename;
        this.time = LocalDateTime.now();
    }

    @Override
    public String toString() {
        return owner + "," + filename + "," + time;
    }
}
