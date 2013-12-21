package net.ostis.confman.services;

import java.io.File;

public interface ReportService {

    void registrationReport(File destination);

    void generateSortedMemberList();
}
