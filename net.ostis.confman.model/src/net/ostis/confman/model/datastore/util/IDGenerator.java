package net.ostis.confman.model.datastore.util;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class IDGenerator {

    private static final String ID_DATA_FILE_LOCATION = "id-data";

    private static final int    ID_POSITION           = 0;

    private static IDGenerator  instance;

    private long                id;

    private IDGenerator() {

        super();
    }

    public static IDGenerator getInstance() {

        if (instance == null) {
            instance = new IDGenerator();
            instance.init();
        }
        return instance;
    }

    private void init() {

        final Path path = Paths.get(ID_DATA_FILE_LOCATION);
        List<String> lines;
        try {
            lines = Files.readAllLines(path, StandardCharsets.UTF_8);
            parseDataList(lines);
        } catch (final IOException e) {
            // TODO kfs: add log4j support
        }
    }

    private void parseDataList(final List<String> lines) {

        this.id = Integer.parseInt(lines.get(ID_POSITION));
    }

    public long nextId() {

        this.id++;
        saveChanges();
        return this.id;
    }

    private void saveChanges() {

        final List<String> lines = createDataList();
        final Path path = Paths.get(ID_DATA_FILE_LOCATION);
        try {
            Files.write(path, lines, StandardCharsets.UTF_8);
        } catch (final IOException e) {
            // TODO kfs: add log4j support
        }
    }

    private List<String> createDataList() {

        final List<String> lines = new ArrayList<String>();
        lines.add(Long.toString(this.id));
        return lines;
    }
}
