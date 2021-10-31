package view;

public interface InputView {
    String[] inputPlayerNames();

    int inputBettingMoneyOf(final String name);

    char inputDrawingNewCard(final String name);
}
