package xyz.mattyb.khance.dict;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SeekableByteChannel;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Stream;

import static java.lang.String.format;

public class Dictionary {

    private static final String DEFAULT_LOCALE = "en_US";
    private static final String RESOURCE_DIR = "khance-dict/src/main/resources";

    private static final Map<String, Map<Integer, Long>> WORD_SIZE = new ConcurrentHashMap<>();

    static {
        try (Stream<Path> paths = Files.walk(Paths.get(RESOURCE_DIR))) {
            paths
                .filter(Files::isRegularFile)
                .forEach(path -> {
                    try {
                        String key = path.getParent().getFileName().toString();
                        Dictionary.WORD_SIZE.putIfAbsent(key, new ConcurrentHashMap<>());
                        Integer wordSize = Integer.parseInt(String.valueOf(path.getFileName()));
                        Dictionary.WORD_SIZE.get(key).put(wordSize, Files.lines(path).count());
                    } catch (Exception e) {

                    }
                });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String getWord(int length) throws IOException {
        return getWord(DEFAULT_LOCALE, length);
    }

    public static String getWord(String locale, int length) throws IOException {
        Path path = Paths.get(format("%s/%s/%s", RESOURCE_DIR, locale, length));
        SeekableByteChannel channel = Files.newByteChannel(path, StandardOpenOption.READ);
        long lineCount = WORD_SIZE.get(locale).get(length);
        long randomLine = new Random().nextInt(Integer.MAX_VALUE) % lineCount;
        ByteBuffer buffer = ByteBuffer.allocate(length);
        channel.position(length * randomLine + randomLine).read(buffer);
        return new String(buffer.array());
    }
}
