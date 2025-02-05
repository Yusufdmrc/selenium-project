package util;

import com.github.javafaker.Faker;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.Random;
import java.util.regex.Pattern;

public class DataProcess {
    private static final String[] EMAIL_DOMAINS = {"gmail.com", "yahoo.com", "hotmail.com","icloud.com"};
    private static final String ALLOWED_SPECIAL_CHARS = "!@#$/&*()-+[]:;?=<>%_";
    private static final String INVALID_CHARACTERS = "!'^+%&/()=?><£#$½§{[]}\\|";
    private static final String[] FIRST_NAMES = {"Test","Ahmet", "Mehmet", "Ömer", "Ayşe", "Fatma", "Zeynep", "Hüseyin", "Ali", "Hakan", "Buse", "Emine", "Merve", "Kerem", "Yusuf", "Bahar", "Çağlar", "İrem", "Deniz", "Kadir"};
    private static final String[] LAST_NAMES = {"Test","Yılmaz", "Demir", "Kaya", "Çelik", "Şahin", "Arslan", "Yıldırım", "Kılıç", "Aydın", "Polat", "Doğan", "Koç", "Erdoğan", "Bulut", "Aksoy", "Aslan"};
    private static final Random random = new Random();
    private static final String TURKISH_ALPHABET = "ABCÇDEFGĞHIİJKLMNOÖPRSŞTUÜVYZ";
    public static String generateFirstName(int length) {
        StringBuilder name = new StringBuilder();
        for (int i = 0; i < length; i++) {
            int index = random.nextInt(TURKISH_ALPHABET.length());
            name.append(TURKISH_ALPHABET.charAt(index));
        }
        return capitalize(name.toString());
    }
    public static String generateLastName(int length) {
        StringBuilder soyisim = new StringBuilder();
        for (int i = 0; i < length; i++) {
            int index = random.nextInt(TURKISH_ALPHABET.length());
            soyisim.append(TURKISH_ALPHABET.charAt(index));
        }
        return capitalize(soyisim.toString());
    }

    private static String capitalize(String str) {
        if (str == null || str.isEmpty()) return str;
        return str.substring(0, 1).toUpperCase() + str.substring(1).toLowerCase();
    }

    public static String generateRandomBirthDate(int minAge,int maxAge) {
        LocalDate currentDate = LocalDate.now();
        int currentYear = currentDate.getYear();

        int age = random.nextInt(maxAge - minAge + 1) + minAge;
        int birthYear = currentYear - age;

        int month = random.nextInt(12) + 1;
        int day;

        switch (month) {
            case 2:
                day = random.nextInt(28) + 1;
                break;
            case 4:
            case 6:
            case 9:
            case 11:
                day = random.nextInt(30) + 1;
                break;
            default:
                day = random.nextInt(31) + 1;
                break;
        }

        LocalDate birthDate = LocalDate.of(birthYear, month, day);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        return birthDate.format(formatter);
    }

    public static String generateEmail(String firstName, String lastName) {
        String email = firstName.toLowerCase() + "." + lastName.toLowerCase();
        email += random.nextInt(1000);
        email = email.replaceAll("[" + Pattern.quote(INVALID_CHARACTERS) + "]", "");
        String domain = EMAIL_DOMAINS[random.nextInt(EMAIL_DOMAINS.length)];
        return email + "@" + domain;
    }

    public static String generatePhoneNumber() {
        StringBuilder phoneNumber = new StringBuilder("5");
        for (int i = 0; i < 9; i++) {
            phoneNumber.append(random.nextInt(10));
        }
        return phoneNumber.toString();
    }

    public static String generateTurkishIdentityNumber() {
        int[] tcNumber = new int[11];

        tcNumber[0] = random.nextInt(9) + 1;
        for (int i = 1; i < 9; i++) {
            tcNumber[i] = random.nextInt(10);
        }

        int sumOdd = tcNumber[0] + tcNumber[2] + tcNumber[4] + tcNumber[6] + tcNumber[8];
        int sumEven = tcNumber[1] + tcNumber[3] + tcNumber[5] + tcNumber[7];

        tcNumber[9] = (7 * sumOdd - sumEven) % 10;

        int sumAll = 0;
        for (int i = 0; i < 10; i++) {
            sumAll += tcNumber[i];
        }
        tcNumber[10] = sumAll % 10;

        StringBuilder identityNumber = new StringBuilder();
        for (int i : tcNumber) {
            identityNumber.append(i);
        }
        return identityNumber.toString();
    }

    public static String generatePassword(String firstName, String lastName, String birthDate) {
        StringBuilder password = new StringBuilder();
        boolean hasUppercase = false;
        boolean hasLowercase = false;
        boolean hasDigit = false;
        boolean hasSpecial = false;

        while (password.length() < random.nextInt(5) + 8) {
            char nextChar;
            int charType = random.nextInt(3);
            if (charType == 0) {
                if (random.nextBoolean()) {
                    nextChar = (char) ('A' + random.nextInt(26));
                    hasUppercase = true;
                } else {
                    nextChar = (char) ('a' + random.nextInt(26));
                    hasLowercase = true;
                }
            } else if (charType == 1) {
                nextChar = (char) ('0' + random.nextInt(10));
                hasDigit = true;
            } else {
                nextChar = ALLOWED_SPECIAL_CHARS.charAt(random.nextInt(ALLOWED_SPECIAL_CHARS.length()));
                hasSpecial = true;
            }

            if (password.length() > 0 && password.charAt(password.length() - 1) == nextChar) {
                continue;
            }

            if (nextChar == ' ') {
                continue;
            }

            if (firstName.toLowerCase().contains(String.valueOf(nextChar).toLowerCase()) ||
                    lastName.toLowerCase().contains(String.valueOf(nextChar).toLowerCase()) ||
                    birthDate.contains(String.valueOf(nextChar))) {
                continue;
            }
            password.append(nextChar);
        }
        if (!hasUppercase || !hasLowercase || !hasDigit || !hasSpecial) {
            return generatePassword(firstName, lastName, birthDate);
        }

        return password.toString();
    }

}
