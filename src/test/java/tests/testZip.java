package tests;

import com.codeborne.pdftest.PDF;
import com.codeborne.xlstest.XLS;
import com.opencsv.CSVReader;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipInputStream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.condition.Not.not;

public class testZip {


    @Test
    void pdfTest() throws Exception {
        ZipFile zipFile = new ZipFile("src/test/resources/files/files.zip");
        ZipEntry zipEntry = zipFile.getEntry("idea.pdf");
        InputStream inputStream = zipFile.getInputStream(zipEntry);
        PDF pdf = new PDF(inputStream);
        assertThat(pdf.text).contains("Smart code completion");
    }


    @Test
    void xlsTest() throws Exception {
        ZipFile zipFile = new ZipFile("src/test/resources/files/files.zip");
        ZipEntry zipEntry = zipFile.getEntry("list1.xlsx");
        InputStream inputStream = zipFile.getInputStream(zipEntry);
        XLS xls = new XLS(inputStream);
        assertThat(xls.excel
                .getSheetAt(0)
                .getRow(1)
                .getCell(1)
                .getStringCellValue()).contains("Тарасов");
    }

    @Test
    void csvTest() throws Exception {
        ZipFile zipFile = new ZipFile("src/test/resources/files/files.zip");
        ZipEntry zipEntry = zipFile.getEntry("list2.csv");
        try (InputStream inputStream = zipFile.getInputStream(zipEntry);
            CSVReader csv = new CSVReader(new InputStreamReader(inputStream))) {
            List<String[]> content = csv.readAll();
            assertThat(content.get(1)).contains("1985");
        }
    }
}
