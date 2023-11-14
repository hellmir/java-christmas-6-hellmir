package christmas.utility;

public class FormatConverter {
    public static String convertMoneyFormatForView(int money) {
        String moneyToChange = String.valueOf(money);
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < moneyToChange.length(); i++) {
            sb.append(moneyToChange.charAt(i));

            if ((moneyToChange.length() - 1 - i) % 3 == 0 && i != moneyToChange.length() - 1) {
                sb.append(",");
            }
        }

        sb.append("원");

        return sb.toString();
    }
}
