package com.spring.app.helper.csv;

import org.apache.commons.csv.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class CSVHelper {
    public final String TYPE = "text/csv";
    private MultipartFile file;
    private List<String> header = new ArrayList<>();

    public MultipartFile getFile() {
        return file;
    }

    public CSVHelper setFile(MultipartFile file) {
        this.file = file;
        return this;
    }

    public List<String> getHeader() {
        return header;
    }

    public CSVHelper setHeader(List<String> header) {
        this.header = header;
        return this;
    }

    public boolean hasCSVFormat() {
        return TYPE.equals(file.getContentType());
    }

    public List<CSVRecord> getCSVRecord() {
        if (hasCSVFormat()) {
            try (BufferedReader fileReader = new BufferedReader(
                    new InputStreamReader(
                            file.getInputStream(),
                            StandardCharsets.UTF_8)
            );
                 CSVParser csvParser = new CSVParser(fileReader,
                         CSVFormat.DEFAULT.withFirstRecordAsHeader().withIgnoreHeaderCase().withTrim())
            ) {
                setHeader(csvParser.getHeaderNames());
                return csvParser.getRecords();
            } catch (IOException e) {
                throw new RuntimeException("fail to parse CSV file: " + e.getMessage());
            }
        }

        return null;
    }

    public ByteArrayInputStream loadFile(Iterable<List<String>> data) {
        final CSVFormat format = CSVFormat.DEFAULT.withQuoteMode(QuoteMode.MINIMAL);

        try (ByteArrayOutputStream out = new ByteArrayOutputStream();
             CSVPrinter csvPrinter = new CSVPrinter(new PrintWriter(new OutputStreamWriter(out, "UTF-8")), format)
        ) {
            csvPrinter.printRecord(header);

            for (List<String> d : data) {
                csvPrinter.printRecord(d);
            }

            csvPrinter.flush();
            return new ByteArrayInputStream(out.toByteArray());
        } catch (IOException e) {
            throw new RuntimeException("fail to import data to CSV file: " + e.getMessage());
        }
    }
}
