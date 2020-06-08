package xyz.mattyb.khance.dict;

import java.io.IOException;
import java.net.URI;
import java.nio.ByteBuffer;
import java.nio.channels.SeekableByteChannel;
import java.nio.file.*;
import java.util.Collections;
import java.util.Iterator;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Stream;

import static java.lang.String.format;

public class Dictionary {

    private static final String DEFAULT_LOCALE = "en_US";

    private static final Map<String, Map<Integer, Long>> WORD_SIZE = new ConcurrentHashMap<>();

    static {
        try {
            Path myPath = getPath("/words");
            Stream<Path> paths = Files.walk(myPath, 10);
            paths
                .filter(Files::isRegularFile)
                .forEach(path -> {
                    try {
                        String key = path.getParent().getFileName().toString().replace("/words", "");
                        Dictionary.WORD_SIZE.putIfAbsent(key, new ConcurrentHashMap<>());
                        Integer wordSize = Integer.parseInt(String.valueOf(path.getFileName()));
                        Dictionary.WORD_SIZE.get(key).put(wordSize, Files.lines(path).count());
                    } catch (Exception e) {

                    }
                });
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static String getWord(int length) throws IOException {
        return getWord(DEFAULT_LOCALE, length);
    }

    public static String getWord(String locale, int length) throws IOException {
        Path path = getPath(format("/words/%s/%s", locale, length));
        SeekableByteChannel channel = Files.newByteChannel(path, StandardOpenOption.READ);
        long lineCount = WORD_SIZE.get(locale).get(length);
        long randomLine = new Random().nextInt(Integer.MAX_VALUE) % lineCount;
        ByteBuffer buffer = ByteBuffer.allocate(length);
        channel.position(length * randomLine + randomLine).read(buffer);
        return new String(buffer.array());
    }

    private static Path getPath(String fileName) {
        try {
            URI uri = Dictionary.class.getResource(fileName).toURI();
            Path myPath;
            if (uri.getScheme().equals("jar")) {
                FileSystem fileSystem = getFileSystem(uri);
                myPath = fileSystem.getPath(fileName);
            } else {
                myPath = Paths.get(uri);
            }
            return myPath;
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    private static FileSystem getFileSystem(URI uri) throws IOException {
        try {
            return FileSystems.newFileSystem(uri, Collections.<String, Object> emptyMap());
        } catch (FileSystemAlreadyExistsException e) {
            return FileSystems.getFileSystem(uri);
        }
    }
}
