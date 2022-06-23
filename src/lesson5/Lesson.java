package lesson5;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.ZoneId;
import java.util.*;
import java.util.regex.Pattern;
import java.time.LocalDate;

public class Lesson {
    private static final Pattern fileNamePattern = Pattern.compile("report_\\d{2}_\\d{4}.txt");
    private static final String fileHeader = "магазин;доход;расход;дата";
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

    public static void main(String[] args) throws IOException, ParseException {
        ArrayList<ReportRecord> records = new ArrayList<>(readDirectory());
        getReports(records);
    }

    private static void getReports(ArrayList<ReportRecord> records) throws ParseException {
        LinkedHashMap<String, Double> allExpenses = new LinkedHashMap<>();
        LinkedHashMap<String, Double> pyterochkaProfits = new LinkedHashMap<>();
        for (ReportRecord record : records) {
            if (record.getShop().equals("pyterochka")) {
                LocalDate localDate = dateFormat.parse(record.getDate()).toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
                String period = "" + localDate.getYear() + new DecimalFormat("00").format(localDate.getMonthValue());
                pyterochkaProfits.put(period, pyterochkaProfits.getOrDefault(period, (double) 0) + (record.getIncome() - record.getOutcome()));
            }
            allExpenses.put(record.getShop(), allExpenses.getOrDefault(record.getShop(), (double) 0) + record.getOutcome());
        }
        System.out.println("Прибыль по магазину pyterochka по месяцам:");
        for (Map.Entry<String, Double> entry : pyterochkaProfits.entrySet()) {
            System.out.printf("%s.%s: %10.2f%n",
                    entry.getKey().substring(4),
                    entry.getKey().substring(0, 4),
                    entry.getValue());
        }
        System.out.println();
        for (Map.Entry<String, Double> entry : allExpenses.entrySet()) {
            System.out.printf("Расходы %11s за весь период: %,10.2f%n",
                    entry.getKey(),
                    entry.getValue());
        }
    }

    private static ArrayList<ReportRecord> readDirectory() throws IOException {
        Path resource = Paths.get("resource");
        if (!Files.exists(resource)) {
            throw new RuntimeException("Нет папки resource");
        }
        File[] listOfFiles = new File(resource.toFile().getAbsolutePath()).listFiles();
        assert listOfFiles != null;
        if (listOfFiles.length == 0) {
            throw new RuntimeException("В папке resource нет файлов");
        }
        ArrayList<ReportRecord> records = new ArrayList<>();
        for (File file : listOfFiles) {
            String fileName = Paths.get(file.getAbsolutePath()).getFileName().toString();
            if (fileNamePattern.matcher(fileName).matches()) {
                records.addAll(readFile(file));
            }
        }
        return records;
    }

    private static ArrayList<ReportRecord> readFile(File file) throws IOException {
        ArrayList<ReportRecord> records = new ArrayList<>();
        BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
        while (bufferedReader.ready()) {
            String line = bufferedReader.readLine();
            if (!line.equals(fileHeader)) {
                String[] parts = line.split(";");
                records.add(new ReportRecord(parts[0], Double.parseDouble(parts[1]), Double.parseDouble(parts[2]), parts[3]));
            }
        }
        bufferedReader.close();
        return records;
    }

}
