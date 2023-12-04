package domain;

import controller.Controller;
import controller.OrderRegistrationController;
import controller.PaymentController;
import java.util.Arrays;

public enum Command {
    REGISTER(1, new OrderRegistrationController()),
    PAYMENT(2, new PaymentController()),
    EXIT(3, null);

    private final int command;
    private final Controller action;

    Command(int command, Controller action) {
        this.command = command;
        this.action = action;
    }

    public static void validateCommand(int inputCommand) {
        Arrays.stream(Command.values())
            .filter(command -> command.command == inputCommand)
            .findFirst()
            .orElseThrow(() -> new IllegalArgumentException("[ERROR] 올바른 기능을 입력해주세요."));
    }

    public void execute() {
        if (action != null) {
            action.run();
        } else {
            System.exit(0);
        }
    }

    public static Command valueOfCommand(int inputCommand) {
        return Arrays.stream(Command.values())
            .filter(cmd -> cmd.command == inputCommand)
            .findFirst()
            .orElseThrow(() -> new IllegalArgumentException("[ERROR] 올바른 기능을 입력해주세요."));
    }
}
