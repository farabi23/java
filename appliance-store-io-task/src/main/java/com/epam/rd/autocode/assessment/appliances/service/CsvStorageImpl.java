package com.epam.rd.autocode.assessment.appliances.service;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

public class CsvStorageImpl implements CsvStorage {

    private String encoding = "UTF-8";
    private String quoteCharacter = "\"";
    private String valuesDelimiter = ",";
    private boolean headerLine = true;

    // Default constructor with default properties
    public CsvStorageImpl() {
    }

    // Constructor with custom properties
    public CsvStorageImpl(Map<String, String> props) {
        if (props.containsKey("encoding")) {
            this.encoding = props.get("encoding");
        }
        if (props.containsKey("quoteCharacter")) {
            this.quoteCharacter = props.get("quoteCharacter");
        }
        if (props.containsKey("valuesDelimiter")) {
            this.valuesDelimiter = props.get("valuesDelimiter");
        }
        if (props.containsKey("headerLine")) {
            this.headerLine = Boolean.parseBoolean(props.get("headerLine"));
        }
    }

    @Override
    public <T> List<T> read(InputStream source, Function<String[], T> mapper) throws IOException {
        List<T> result = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(source, Charset.forName(encoding)))) {
            String line;
            if (headerLine) {
                reader.readLine(); // Skip header line if necessary
            }
            while ((line = reader.readLine()) != null) {
                String[] values = parseCsvLine(line);
                result.add(mapper.apply(values));
            }
        }
        return result;
    }

    @Override
    public <T> void write(OutputStream dest, List<T> values, Function<T, String[]> mapper) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(dest, Charset.forName(encoding)))) {
            for (T item : values) {
                String[] fields = mapper.apply(item);
                String csvLine = createCsvLine(fields);
                writer.write(csvLine);
                writer.newLine();
            }
        }
    }

    private String[] parseCsvLine(String line) {
        List<String> tokens = new ArrayList<>();
        StringBuilder currentToken = new StringBuilder();
        boolean insideQuotes = false;
        for (int i = 0; i < line.length(); i++) {
            char currentChar = line.charAt(i);
            if (currentChar == valuesDelimiter.charAt(0)) {
                if (insideQuotes) {
                    currentToken.append(currentChar);
                } else {
                    tokens.add(currentToken.toString());
                    currentToken = new StringBuilder();
                }
            } else if (currentChar == quoteCharacter.charAt(0)) {
                if (insideQuotes && i < line.length() - 1 && line.charAt(i + 1) == quoteCharacter.charAt(0)) {
                    currentToken.append(currentChar);
                    i++;
                } else {
                    insideQuotes = !insideQuotes;
                }
            } else {
                currentToken.append(currentChar);
            }
        }
        tokens.add(currentToken.toString());
        return tokens.toArray(new String[0]);
    }

    private String createCsvLine(String[] fields) {
        StringBuilder csvLine = new StringBuilder();
        for (int i = 0; i < fields.length; i++) {
            if (fields[i] == null) {
                fields[i] = ""; // Convert null to empty string
            }
            if (fields[i].contains(valuesDelimiter) || fields[i].equals("") || fields[i].contains(quoteCharacter)) {
                fields[i] = quoteCharacter + fields[i].replace(quoteCharacter, quoteCharacter + quoteCharacter) + quoteCharacter;
            }
            csvLine.append(fields[i]);
            if (i < fields.length - 1) {
                csvLine.append(valuesDelimiter);
            }
        }
        return csvLine.toString();
    }
}
