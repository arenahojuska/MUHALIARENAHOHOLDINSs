package RMA;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Random;

public class SAnewIdGen {

    private static final Random random = new Random();

    /**
     * Generates a valid South African ID number for a person aged 30-80 (inclusive).
     * @return the generated 13-digit ID number as a String
     */
    public static String generateIdForAgeRange() {
        LocalDate today = LocalDate.now();

        // Pick a random age between 30 and 80
        int age = 30 + random.nextInt(51); // 30 to 80 inclusive

        // Pick a random birth date that results in that age today
        LocalDate birthDate = today.minusYears(age)
                .minusDays(random.nextInt(365)); // randomize day within the year

        // Re-check the age hasn't drifted below 30 due to day subtraction, adjust if needed
        long actualAge = ChronoUnit.YEARS.between(birthDate, today);
        if (actualAge < 30) {
            birthDate = birthDate.minusYears(1);
        } else if (actualAge > 80) {
            birthDate = birthDate.plusYears(1);
        }

        String yymmdd = String.format("%02d%02d%02d",
                birthDate.getYear() % 100,
                birthDate.getMonthValue(),
                birthDate.getDayOfMonth());

        // Gender sequence: 0000-9999 (doesn't matter for this use case, so fully random)
        String genderSequence = String.format("%04d", random.nextInt(10000));

        // Citizenship: 0 = SA citizen
        String citizenship = "0";

        // Legacy digit: always 8 on modern IDs
        String legacyDigit = "8";

        String first12 = yymmdd + genderSequence + citizenship + legacyDigit;

        int checkDigit = calculateLuhnCheckDigit(first12);

        String idNumber = first12 + checkDigit;

        System.out.println("Generated SA ID: " + idNumber + " (Age: " + actualAge + ", DOB: " + birthDate + ")");

        return idNumber;
    }

    /**
     * Calculates the Luhn (ISO/IEC 7812) check digit for the given 12-digit string.
     */
    private static int calculateLuhnCheckDigit(String digits) {
        int sum = 0;
        boolean doubleDigit = true; // rightmost of the 12 digits gets doubled first

        for (int i = digits.length() - 1; i >= 0; i--) {
            int digit = Character.getNumericValue(digits.charAt(i));

            if (doubleDigit) {
                digit *= 2;
                if (digit > 9) {
                    digit -= 9;
                }
            }

            sum += digit;
            doubleDigit = !doubleDigit;
        }

        return (10 - (sum % 10)) % 10;
    }

    public static void main(String[] args) {
        String id = generateIdForAgeRange();
        System.out.println("Returned ID: " + id);
    }
}