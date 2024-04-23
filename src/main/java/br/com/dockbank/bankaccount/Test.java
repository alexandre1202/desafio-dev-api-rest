package br.com.dockbank.bankaccount;

public class Test {
    public static void main(String[] args) {
        Test test = new Test();
        System.out.println("abc123 = " + test.calcString("abc123"));
        System.out.println("hello123 = " + test.calcString("hello123"));
    }

    private int calcString(String input) {
        int sum = 0;
        if (!input.isEmpty()) {
            for (int i = 0; i < input.length(); i++) {
                int charValue = (int)input.charAt(i);
                System.out.println("input.charAt = " + input.charAt(i) + " char value = " + charValue);
                if (charValue > 96 && charValue < 123) {
                    sum += charValue - 96;
                } else if (charValue > 47 && charValue < 58) {
                    sum += Integer.valueOf(input.substring(i, i+1));
                }
            }
        }
        return sum;
    }
}
