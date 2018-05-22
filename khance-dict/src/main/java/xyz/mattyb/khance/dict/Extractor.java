package xyz.mattyb.khance.dict;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.SeekableByteChannel;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import static java.util.stream.Collectors.groupingBy;

class Extractor {

    public static void main(String[] args) throws IOException {

//        Map<Integer, List<String>> sizeMap = new HashMap<>();
//        try (Stream<Path> paths = Files.walk(Paths.get("khance-dict/src/main/resources/en_US/"))) {
//            paths
//                .filter(Files::isRegularFile)
//                .forEach(path -> {
//                    try {
////                        sizeMap.putAll(
////                            Files.readAllLines(path)
////                                .stream()
////                                .collect(groupingBy(String::length))
//                        ByteBuffer buffer = ByteBuffer.allocate(10);
////                        );
//                        SeekableByteChannel channel = Files.newByteChannel(path, StandardOpenOption.READ);
//                        channel.position(100).read(buffer);
//                    } catch (IOException e) {
//                        e.printStackTrace();
//                    }
//                });
//        }

//        Dictionary dictionary = new Dictionary();

//        sizeMap.forEach((key, value) -> {
//            try {
//                Path newPath = Files.createFile(Paths.get("khance-dict/src/main/resources/en_US/" + key.toString()));
//                Files.write(newPath, value);
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        });
    }
}
