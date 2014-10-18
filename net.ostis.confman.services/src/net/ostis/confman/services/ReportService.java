package net.ostis.confman.services;

import java.io.File;
import java.io.OutputStream;

public interface ReportService {

    void registrationReport(File destination);

    void generateSortedMemberList(OutputStream outputStream);

    void generateParticipantsCategoryList(OutputStream fileOutputStream);
}
