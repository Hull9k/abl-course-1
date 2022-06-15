package lesson3;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Lesson {
    static class IntPair implements Comparable<IntPair> {
        private final int first, second;

        public IntPair(Integer first, Integer second) {
            this.first = first;
            this.second = second;
        }

        public Integer getFirst() {
            return first;
        }

        public Integer getSecond() {
            return second;
        }

        public int compareTo(IntPair intPair) {
            return intPair.second - this.second;
        }
    }

    public static void main(String[] args) {
        Map<Integer, Map<String, String[]>> data = GeneratorExpertHomework.getData();
        ArrayList<IntPair> topFiveList = new ArrayList<>();
        HashSet<String> specLicPlatesSet = new HashSet<>();

        for (Map.Entry<Integer, Map<String, String[]>> set : data.entrySet()) {
            int region = set.getKey();
            int inputCarNubmer = set.getValue().get("input").length;
            topFiveList.add(new IntPair(region, inputCarNubmer));
            specLicPlatesSet.addAll(checkSpecNumber(set.getValue().get("input")));
            specLicPlatesSet.addAll(checkSpecNumber(set.getValue().get("out")));
        }
        Collections.sort(topFiveList);
        System.out.print("ТОП-5:");
        for (int i = 0; i < 5; i++) {
            System.out.print(" " + topFiveList.get(i).getFirst() + ",");
        }
        System.out.println("\b");

        for (int i = 0; i < 5; i++) {
            int region = topFiveList.get(i).getFirst();
            IntPair inputPair = getMaxInput(data.get(region).get("input"));
            System.out.println(region + " - больше всего въехало с номерами " + inputPair.getFirst() + ": " + inputPair.getSecond() + " машин(ы)");
        }

        System.out.println("Всего машин со спец номерами: " + specLicPlatesSet.size());
    }

    private static IntPair getMaxInput(String[] inputs) {
        Map<Integer, Integer> entered = new HashMap<>();
        Pattern pattern = Pattern.compile("\\p{L}{1}\\d{3}\\p{L}{2}(\\d{3})");
        for (String input : inputs) {
            Matcher matcher = pattern.matcher(input);
            if (matcher.find()) {
                int region = Integer.parseInt(matcher.group(1));
                entered.put(region, entered.getOrDefault(region, 0) + 1);
            }
        }
        ArrayList<IntPair> topList = new ArrayList<>();
        for (Map.Entry<Integer, Integer> entry : entered.entrySet()) {
            topList.add(new IntPair(entry.getKey(), entry.getValue()));
        }
        Collections.sort(topList);
        return topList.get(0);
    }

    private static HashSet<String> checkSpecNumber(String[] inputs) {
        HashSet<String> specLicPlate = new HashSet<>();
        Pattern pattern = Pattern.compile("^М{1}\\d{3}АВ\\d{3}$");
        for (String input : inputs) {
            Matcher matcher = pattern.matcher(input);
            if (matcher.find()) {
                specLicPlate.add(input);
            }
        }
        return specLicPlate;
    }
}