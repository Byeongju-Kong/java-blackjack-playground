package blackjack;

import controller.BlackJackController;
import view.InputDisplay;
import view.InputView;
import view.OutputDisplay;
import view.OutputView;

import java.util.Scanner;

public class Application {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        InputView inputView = new InputDisplay(scanner);
        OutputView outputView = new OutputDisplay();
        BlackJackController blackJackController = new BlackJackController(inputView, outputView);
        blackJackController.run();
    }
}
