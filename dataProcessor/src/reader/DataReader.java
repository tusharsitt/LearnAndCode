package reader;

import java.util.List;

public interface DataReader {
    List<String> readLines(String filePath);
}