package view;

import java.util.Scanner;

public class InputDisplay implements InputView{
    private static final String DELIMITER = ",";
    private final Scanner scanner;

    public InputDisplay(final Scanner scanner) {
        this.scanner = scanner;
    }
    
    @Override
    public String[] inputPlayerNames() {
        return scanner.nextLine().split(DELIMITER);
    }

    @Override
    public int inputBettingMoneyOf(final String name) {
        System.out.println(name + "의 배팅 금액은?");
        return Integer.parseInt(scanner.nextLine());
    }

    @Override
    public char inputDrawingNewCard(final String name) {
        System.out.println(name + "는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)");
        return scanner.nextLine().charAt(0);
    }
}
