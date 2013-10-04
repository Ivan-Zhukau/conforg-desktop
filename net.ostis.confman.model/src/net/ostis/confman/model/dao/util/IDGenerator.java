package net.ostis.confman.model.dao.util;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class IDGenerator {

    private static final String ID_DATA_FILE_LOCATION   = "id-data";

    private static final int    ARTICLE_ID_POSITION     = 0;

    private static final int    PARTICIPANT_ID_POSITION = 1;

    private static IDGenerator  instance;

    private long                articleID;

    private long                participantID;

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
            e.printStackTrace();
        }
    }

    private void parseDataList(final List<String> lines) {

        this.articleID = Integer.parseInt(lines.get(ARTICLE_ID_POSITION));
        this.participantID = Integer.parseInt(lines
                .get(PARTICIPANT_ID_POSITION));
    }

    public long nextArticleID() {

        this.articleID++;
        saveChanges();
        return this.articleID;
    }

    public long nextParticipantID() {

        this.participantID++;
        saveChanges();
        return this.participantID;
    }

    private void saveChanges() {

        final List<String> lines = createDataList();
        final Path path = Paths.get(ID_DATA_FILE_LOCATION);
        try {
            Files.write(path, lines, StandardCharsets.UTF_8);
        } catch (final IOException e) {
            e.printStackTrace();
        }
    }

    private List<String> createDataList() {

        final List<String> lines = new ArrayList<String>();
        lines.add(Long.toString(this.articleID));
        lines.add(Long.toString(this.participantID));
        return lines;
    }
}
