package net.ostis.confman.model.registrationform.wordparser.format;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import net.ostis.confman.model.registrationform.wordparser.RegistrationFormConstant;

import org.apache.poi.hwpf.HWPFDocument;
import org.apache.poi.hwpf.usermodel.Paragraph;
import org.apache.poi.hwpf.usermodel.Range;
import org.apache.poi.hwpf.usermodel.Table;
import org.apache.poi.hwpf.usermodel.TableCell;
import org.apache.poi.hwpf.usermodel.TableRow;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;

public class DocParser {

    private List<String> infoList;

    public DocParser() {

        this.infoList = new ArrayList<String>();
    }

    public List<String> parse(final FileInputStream inputStream) {

        POIFSFileSystem fileSystem = null;
        HWPFDocument doc = null;
        try {
            fileSystem = new POIFSFileSystem(inputStream);
            doc = new HWPFDocument(fileSystem);
        } catch (final IOException e) {
            e.printStackTrace();
        }
        readDoc(doc);
        return this.infoList;
    }

    private void readDoc(final HWPFDocument doc) {

        final Range range = doc.getRange();
        final Paragraph tablePar = range.getParagraph(0);
        if (tablePar.isInTable()) {
            final Table table = range.getTable(tablePar);
            for (int rowIdx = 0; rowIdx < table.numRows(); rowIdx++) {
                final TableRow row = table.getRow(rowIdx);
                for (int colIdx = 0; colIdx < row.numCells(); colIdx++) {
                    if (rowIdx != 0
                            && colIdx == RegistrationFormConstant.INFORMATION_COLUNM) {
                        final TableCell cell = row.getCell(colIdx);
                        this.infoList.add(cell.getParagraph(0).text());
                    }
                }
            }
        }
    }
}
