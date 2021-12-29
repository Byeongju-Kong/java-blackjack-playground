package blackjack;

import controller.BlackJackController;
import view.input.InputDisplay;
import view.input.InputView;
import view.output.OutputDisplay;
import view.output.OutputView;

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
