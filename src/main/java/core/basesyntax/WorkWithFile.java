package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final int NAME_POSITION = 0;
    private static final int NUMBER_POSITION = 1;
    private static final String SEPARATOR = ",";
    private static final String SUPPLY = "supply";

    public void getStatistic(String fromFileName, String toFileName) {
        int[] data = readFile(fromFileName);
        String report = generateReport(data);
        writeReport(toFileName, report);
    }

    private int[] readFile(String fromFileName) {
        int[] values = new int[2];
        try (BufferedReader reader = new BufferedReader(new FileReader(fromFileName))) {
            String line = reader.readLine();
            while (line != null) {
                String[] separate = line.split(SEPARATOR);
                if (separate[NAME_POSITION].equals(SUPPLY)) {
                    values[NAME_POSITION] += Integer.parseInt(separate[NUMBER_POSITION]);
                } else {
                    values[NUMBER_POSITION] += Integer.parseInt(separate[NUMBER_POSITION]);
                }
                line = reader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't read " + fromFileName, e);
        }
        return values;
    }

    private String generateReport(int[] data) {
        int result = data[NAME_POSITION] - data[NUMBER_POSITION];
        return "supply," + data[NAME_POSITION] + System.lineSeparator()
                + "buy," + data[NUMBER_POSITION] + System.lineSeparator()
                + "result," + result + System.lineSeparator();
    }

    private void writeReport(String toFileName, String report) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(toFileName))) {
            bufferedWriter.write(report);
        } catch (IOException e) {
            throw new RuntimeException("Can't write file ", e);
        }
    }
}
