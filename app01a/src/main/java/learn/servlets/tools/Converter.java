package learn.servlets.tools;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Converter {

    public static void main(String[] args) throws IOException {
        String path = "temp/test.txt";
        String deletePrefix = "writer.println(\"";
        String deleteSuffix = "\");";
        if (args.length == 3) {
            path = args[0];
            deletePrefix = args[1];
            deleteSuffix = args[2];
        }
        convertJoining(Path.of(path), deletePrefix, deleteSuffix);
        System.out.println("Finished");
    }

    private static void convertJoining(Path p, String deletePrefix, String deleteSuffix) throws IOException {
        final List<String> lines = Files.readAllLines(p);
        List<String> result = joinLines(lines);
        result = cleanLines(deletePrefix, deleteSuffix, result);
        Path resultPath = addSuffixToName(p, ".result");
        System.out.println("Writing result to " + resultPath);
        try (PrintWriter writer = new PrintWriter(Files.newBufferedWriter(resultPath))) {
            result.forEach(writer::println);
        }
    }

    private static List<String> joinLines(List<String> lines) {
        List<String> result = new ArrayList<>();
        boolean joinToPrevious = false;
        for (String s : lines) {
            String line = s.trim();
            if (line.startsWith("+")) {
                addToLast(result, line);
            } else if (joinToPrevious) {
                addToLast(result, line);
            } else {
                result.add(line);
            }
            joinToPrevious = line.endsWith("+");
        }
        return result;
    }

    private static List<String> cleanLines(String deletePrefix, String deleteSuffix, List<String> lines) {
        return lines.stream()
                .map(s -> {
                    s = s.replaceAll("\" \\+ \"", "");
                    if (s.startsWith(deletePrefix)) {
                        s = s.substring(deletePrefix.length());
                    }
                    if (s.endsWith(deleteSuffix)) {
                        s = "\"" + s.substring(0, s.indexOf(deleteSuffix)) + "\",";
                    }
                    return s;
                })
                .collect(Collectors.toList());
    }

    private static void addToLast(List<String> lines, String line) {
        int last = lines.size() - 1;
        String prevLine = lines.get(last);
        lines.set(last, prevLine + " " + line);
    }

    public static Path addSuffixToName(Path p, String suffix) {
        if (!suffix.startsWith(".")) {
            suffix = "." + suffix;
        }
        Path filename = p.getFileName();
        String name = filename.toString();
        int dotPos = name.lastIndexOf('.');
        if (dotPos == -1) {
            name = name + suffix;
        } else {
            String ext = name.substring(dotPos);
            name = name.replace(ext, suffix + ext);
        }
        return p.resolveSibling(name);
    }
}
