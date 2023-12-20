import java.util.*;

class CustomMathException extends Exception {
    public CustomMathException(String text) {
        System.out.println(text);
    }
}
public class Main {
    public static String hiddenAnagram(String sentence, String anagram) {
        String cleanedSentence = sentence.replaceAll("[^a-zA-Z]", "").toLowerCase();
        String cleanedAnagram = anagram.replaceAll("[^a-zA-Z]", "").toLowerCase();
        Map<Character, Integer> anagramMap = new HashMap<>();
        for (char c : cleanedAnagram.toCharArray()) {
            if(anagramMap.containsKey(c)){
                anagramMap.put(c, anagramMap.get(c)+1);
            }else{
                anagramMap.put(c, 1);
            }
        }
        int start = 0;
        for(int end = 0; end < cleanedSentence.length(); end++) {
            char c = cleanedSentence.charAt(end);

            if (anagramMap.containsKey(c) && anagramMap.get(c)>0) {
                anagramMap.put(c, anagramMap.get(c)-1);
                boolean flag=true;
                for (char character: anagramMap.keySet()){
                    if(anagramMap.get(character) != 0){
                        flag = false;
                    }
                }
                if (flag) {
                    return cleanedSentence.substring(start, end+1);
                }
            } else {
                if(anagramMap.containsKey(c)){
                    int i = 0;
                    while(true){
                        char b = cleanedSentence.charAt(i);
                        if(c == b){
                            start = i + 1;
                            break;
                        }
                        if (anagramMap.containsKey(b)){
                            anagramMap.put(b, anagramMap.get(b)+1);
                        }
                        i++;
                    }
                }else{
                    start=end+1;
                }
            }
        }
        return "notfound";
    }
    public static String[] collect(String word, int n) {
        String[] result = new String[word.length()/n];
        String str = "";
        for(int i = 0; i < word.length(); i++) {
            str += word.charAt(i);
            if (str.length() == n) {
                result[i / n] = str;
                str = "";
            }
        }
        Arrays.sort(result);
        return result;
    }
    public static String[] collec(String word, int n) {
        int len = word.length()%n==0? word.length()/n : (word.length()/n)+1;
        String[] result = new String[len];
        String str = "";
        for(int i = 0; i < word.length(); i++) {
            str += word.charAt(i);
            if (str.length() == n) {
                result[i / n] = str;
                str = "";
            }
        }
        if(word.length()%n!=0){
            int count = n-str.length();
            for(int i=0; i<=count;i++){
                str += " ";
            }
            result[len-1] = str;
        }
        return result;
    }
    public static String nicoCipher(String message, String  key){
        char[] key_arr = key.toCharArray();
        Arrays.sort(key_arr);
        int[] key_int = new int[key.length()];
        for(int i = 0; i < key.length(); i++){
            key_int[i] = key.indexOf(key_arr[i]);
            key = key.substring(0, key.indexOf(key_arr[i])) + ' ' + key.substring(key.indexOf(key_arr[i])+1);
        }
        String[] message_arr = collec(message, key.length());
        String result = "";
        for(String str: message_arr){
            for(int i : key_int){
                result += str.charAt(i);
            }
        }
        return result;
    }
    public static int[] twoProduct(int[] nums, int n) {
        ArrayList<Integer> leftNums = new ArrayList<>();
        for (int i = 0; i < nums.length; i++) {
            for (int j = 0; j < leftNums.size(); j++) {
                if (leftNums.get(j) * nums[i] == n) {
                    return new int[]{leftNums.get(j), nums[i]};
                }
            }
            leftNums.add(nums[i]);
        }
        return new int[0];
    }
    public static int[] isExact(int number) {
        int dynamicNumber = number;
        if (number == 1) return new int[]{1, 1};
        if (number == 2) return new int[]{2, 2};
        for (int i = 2; i <= number; i++) {
            if (dynamicNumber % i != 0) {
                if (i == 2) {
                    return new int[0];
                }
                else {
                    int[] temp = isExact(number / (i - 1));
                    if (temp.length == 0) {
                        return new int[0];
                    }
                    else {
                        if (i - 1 == temp[1]) {
                            return new int[0];
                        }
                        return new int[]{number, (i-1)};
                    }
                }
            }
            dynamicNumber /= i;
        }
        return new int[0];
    }
    public static String fractions(String num){
        num=num.replaceAll("\\(", " ").replaceAll("\\)"," ").replaceAll("\\.", " ");
        String[] num_arr = num.split(" ");
        int chisl = Integer.parseInt(num_arr[0]+num_arr[1]+num_arr[2])-Integer.parseInt(num_arr[0]+num_arr[1]);
        int znam = pow(10, (num_arr[1]+num_arr[2]).length())-pow(10, (num_arr[1]).length());
        int dell = gcd(chisl, znam);
        return (chisl/dell)+"/"+(znam/dell);
    }
    public static int gcd(int a, int b){
        int max, min, otv = 0;
        max = a > b ? a : b;
        min = a < b ? a : b;
        for(int i = min; i != 0; i--){
            if(max % i == 0 && min % i == 0){
                otv = i;
                break;
            }
        }
        return otv;
    }
    public static int pow(int value, int powValue) {
        int result = 1;
        for (int i = 1; i <= powValue; i++) {
            result = result * value;
        }
        return result;
    }
    public static String pilish_string(String str) {
        if (str.isEmpty()) return "";

        double pi = 3.14159265358979;
        int[] piNums = new int[15];
        int i = 0;
        for (char number: Double.toString(pi).toCharArray()) {
            if (number != '.') {
                piNums[i] = Character.getNumericValue(number);
                i++;
            }
        }

        StringBuilder answer = new StringBuilder();
        int nowSymbols = 0;
        i = 0;
        while (nowSymbols < str.length()) {
            if (i == 15) break;
            if (nowSymbols + piNums[i] > str.length()) {
                answer.append(str, nowSymbols, str.length());
            }
            else {
                answer.append(str, nowSymbols, nowSymbols + piNums[i]);
            }
            nowSymbols += piNums[i];
            i++;
            if (nowSymbols < str.length()) answer.append(" ");
        }
        if (nowSymbols > str.length()) {
            while (nowSymbols != str.length()) {
                answer.append(str.substring(str.length() - 1));
                nowSymbols -= 1;
            }
        }
        return answer.toString();
    }

    public static int generateNonconsecutive(String str) throws CustomMathException {
        String operations = "+-/*";
        str = str.replaceAll("\\(", "( ");
        str = str.replaceAll("\\)", " )");
        String[] strArray = str.split("\\s+");
        boolean lastNum = false;
        String now;
        int bracketCounter = 0;
        int openBrackets = 0;

        // Обработка ислючений
        for (int i = 0; i < strArray.length; i++) {
            now = strArray[i];
            if (operations.contains(now)) {
                if ((i == 0 || i == strArray.length - 1)) {
                    throw new CustomMathException("Неверный формат ввода (начинается или заканичивается операцией))");
                }
                if (!lastNum) {
                    throw new CustomMathException("Неверный формат ввода (две операции подряд или ошибка у скобок)");
                }
                lastNum = false;
            }
            else if (now.equals("(")) {
                openBrackets += 1;
                bracketCounter += 1;
            }
            else if (now.equals(")")) {
                openBrackets -= 1;
                if (openBrackets < 0) {
                    throw new CustomMathException("Неверный формат ввода (неправильный порядок скобок)");
                }
            }
            else if (now.matches("-?\\d+")) {
                if (lastNum) {
                    throw new CustomMathException("Неверный формат ввода (два числа подряд или ошибка у скобок)");
                }
                lastNum = true;
            }
            else {
                throw new CustomMathException("Неверный формат ввода (встречается неизвестная подстрока)");
            }
        }
        if (openBrackets != 0) {
            throw new CustomMathException("Неверный формат ввода (неправильный порядок скобок)");
        }

        // Простой случай операции
        if (strArray.length == 3) {
            switch (strArray[1]) {
                case "+" -> {
                    return Integer.parseInt(strArray[0]) + Integer.parseInt(strArray[2]);
                }
                case "-" -> {
                    return Integer.parseInt(strArray[0]) - Integer.parseInt(strArray[2]);
                }
                case "*" -> {
                    return Integer.parseInt(strArray[0]) * Integer.parseInt(strArray[2]);
                }
                case "/" -> {
                    try {
                        return Integer.parseInt(strArray[0]) / Integer.parseInt(strArray[2]);
                    }
                    catch (ArithmeticException e) {
                        throw new CustomMathException("Ошибка: В строке происходит деление на ноль");
                    }
                }
            }
        }
        // Разбиение на простые случаи
        else {
            // Учитывание скобок
            if (bracketCounter != 0) {
                int firstBracket = -1;
                int secondBracket = -1;
                // Ищем скобки
                for (int i = 0; i < strArray.length; i++) {
                    now = strArray[i];
                    if (now.equals("(") && firstBracket == -1) firstBracket = i;
                    if (now.equals(")")) secondBracket = i;
                }

                // Создаем новую строку, учитывая скобки
                String newStr = "";
                if (firstBracket != 0) {
                    newStr += String.join(" ", Arrays.copyOfRange(strArray, 0, firstBracket)) + " ";
                }
                newStr += (generateNonconsecutive(
                        String.join(" ",
                                Arrays.copyOfRange(strArray, firstBracket + 1, secondBracket))
                ));
                if (secondBracket != strArray.length) {
                    newStr += " " +
                            String.join(" ",
                                    Arrays.copyOfRange(strArray, secondBracket + 1, strArray.length));
                }
                return generateNonconsecutive(newStr);
            }
            // Сокращение через базовые операции
            else {
                int operationIndex = -1;
                boolean higher = false;
                for (int i = 0; i < strArray.length; i++) {
                    now = strArray[i];
                    if ("+-".contains(now) && operationIndex == -1) operationIndex = i;
                    if ("*/".contains(now) && !higher) {
                        operationIndex = i;
                        higher = true;
                    }
                }
                // Построение новой строки
                String newStr = "";
                if (operationIndex != 1) {
                    newStr += String.join(" ",
                            Arrays.copyOfRange(strArray, 0, operationIndex - 1)) + " ";
                }
                newStr += (generateNonconsecutive(
                        String.join(" ",
                                Arrays.copyOfRange(strArray, operationIndex - 1, operationIndex + 2))
                ));
                if (openBrackets != strArray.length - 1) {
                    newStr += " " +
                            String.join(" ",
                                    Arrays.copyOfRange(strArray, operationIndex + 2, strArray.length));
                }
                return generateNonconsecutive(newStr);
            }
        }
        // Вывод 0, чтобы компилятор пропустил
        return 0;
    }

    public static String isValid(String str) {
        Map<Character, Integer> dictionary = new HashMap<>();
        int unique = 0;
        for (char symbol: str.toCharArray()) {
            if (dictionary.containsKey(symbol)) {
                dictionary.put(symbol, dictionary.get(symbol) + 1);
            }
            else {
                dictionary.put(symbol, 1);
                unique += 1;
            }
        }

        int[] values = new int[unique];
        int i = 0;
        for(int value: dictionary.values()) {
            values[i] = value;
            i++;
        }

        Arrays.sort(values);
        if (values[0] == values[values.length - 1]) {
            return "YES";
        }
        else if (values[1] == values[values.length - 1] && values[0] == 1) {
            return "YES";
        }
        else if (values[0] == values[values.length - 2] && values[values.length - 1] - 1 == values[0]) {
            return "YES";
        }
        return "NO";
    }

    public static String findLCS(String str1, String str2) {
        int[][] matrix = new int[str1.length() + 1][str2.length() + 1];
        for (int i = 0; i <= str1.length(); i++) {
            for (int j = 0; j <= str2.length(); j++) {
                if (i == 0 || j == 0) {
                    matrix[i][j] = 0;
                }
                else if (str1.charAt(i - 1) == str2.charAt(j - 1)) {
                    matrix[i][j] = matrix[i - 1][j - 1] + 1;
                }
                else {
                    matrix[i][j] = Math.max(matrix[i][j - 1], matrix[i - 1][j]);
                }
            }
        }
        ArrayList<Character> chars = new ArrayList<>();
        int temp_i = str1.length() - 1;
        int temp_j = str2.length() - 1;
        while (temp_i >= 0 && temp_j >= 0) {
            if (str1.charAt(temp_i) == str2.charAt(temp_j)) {
                chars.add(str1.charAt(temp_i));
                temp_i -= 1;
                temp_j -= 1;
            }
            else if (matrix[temp_i][temp_j + 1] >= matrix[temp_i + 1][temp_j]) {
                temp_i -= 1;
            }
            else {
                temp_j -= 1;
            }
        }
        Collections.reverse(chars);
        StringBuilder answer = new StringBuilder();
        for (char c: chars) {
            answer.append(c);
        }
        return answer.toString();
    }
    public static void main(String[] args) {
        System.out.println("----------1------------");
        System.out.println(hiddenAnagram("My world evolves in a beautiful space called Tesh.", "sworn love lived"));
        System.out.println(hiddenAnagram("An old west action hero actor", "Clint Eastwood"));
        System.out.println(hiddenAnagram("Mr. Mojo Rising could be a song title", "Jim Morrison"));
        System.out.println(hiddenAnagram("Banana? margaritas", "ANAGRAM"));
        System.out.println(hiddenAnagram("D  e b90it->?$ (c)a r...d,,#~", "bad credit"));
        System.out.println(hiddenAnagram("Bright is the moon", "Bongo mirth"));
        System.out.println("----------2------------");
        System.out.println(Arrays.toString(collect("intercontinentalisationalism", 6)));
        System.out.println(Arrays.toString(collect("strengths", 3)));
        System.out.println(Arrays.toString(collect("pneumonoultramicroscopicsilicovolcanoconiosis", 15)));
        System.out.println("----------3------------");
        System.out.println( nicoCipher("myworldevolvesinhers", "tesh"));
        System.out.println( nicoCipher("andiloveherso", "tesha"));
        System.out.println( nicoCipher("mubashirhassan", "crazy"));
        System.out.println( nicoCipher("edabitisamazing", "matt"));
        System.out.println( nicoCipher("iloveher", "612345"));
        System.out.println("----------4------------");
        System.out.println(Arrays.toString(twoProduct(new int[]{1, 2, 3, 9, 4, 5, 15}, 45)));
        System.out.println(Arrays.toString(twoProduct(new int[]{1, 2, 3, 9, 4, 15, 3, 5}, 45)));
        System.out.println(Arrays.toString(twoProduct(new int[]{1,  2, -1,  4,  5,  6,  10, 7}, 20)));
        System.out.println(Arrays.toString(twoProduct(new int[]{1, 2, 3, 4, 5,  6, 7, 8, 9, 10}, 10)));
        System.out.println(Arrays.toString(twoProduct(new int[]{100, 12, 4, 1, 2}, 15)));
        System.out.println("----------5------------");
        System.out.println(Arrays.toString(isExact(6) ));
        System.out.println(Arrays.toString(isExact(24) ));
        System.out.println(Arrays.toString(isExact(125) ));
        System.out.println(Arrays.toString(isExact(720) ));
        System.out.println(Arrays.toString(isExact(1024) ));
        System.out.println(Arrays.toString(isExact(40320) ));
        System.out.println("----------6------------");
        System.out.println(fractions("0.(6)"));
        System.out.println(fractions("1.(1)") );
        System.out.println(fractions("3.(142857)") );
        System.out.println(fractions("0.19(2367)") );
        System.out.println(fractions("0.1097(3)") );
        System.out.println("----------7------------");
        System.out.println(pilish_string("33314444"));
        System.out.println(pilish_string("TOP"));
        System.out.println(pilish_string("X"));
        System.out.println(pilish_string(""));
        System.out.println(pilish_string("CANIMAKEAGUESSNOW"));
        System.out.println(pilish_string("FORALOOP"));
        System.out.println(pilish_string("HOWINEEDADRINKALCOHOLICINNATUREAFTERTHEHEAVYLECTURESINVOLVINGQUANTUMMECHANICSANDALLTHESECRETSOFTHEUNIVERSE"));
        System.out.println("----------8------------");
        try {
            System.out.println(generateNonconsecutive("3 + 5 * (2 - 6 )"));
            System.out.println(generateNonconsecutive("6 - 18 / (-1 + 4)"));
        }catch (CustomMathException e){
            System.out.println(e.getMessage());
        }
        System.out.println("----------9------------");
        System.out.println(isValid("aabbcd"));
        System.out.println(isValid("aabbccddeefghi"));
        System.out.println(isValid("abcdefghhgfedecba"));
        System.out.println("----------10------------");
        System.out.println(findLCS("abcd", "bd"));
        System.out.println(findLCS("aggtab", "gxtxamb") );
    }
}