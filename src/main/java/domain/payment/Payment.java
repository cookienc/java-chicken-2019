package domain.payment;

import java.util.Arrays;

public enum Payment {
    CREDIT("신용 카드", 1),
    CASH("현금", 2);
    private final String name;
    private final int command;

    Payment(String name, int command) {
        this.name = name;
        this.command = command;
    }

    public static void validateCommand(int command) {
        Arrays.stream(Payment.values())
            .filter(payment -> payment.command == command)
            .findFirst()
            .orElseThrow(() -> new IllegalArgumentException("[ERROR] 올바른 결제 방식을 입력해주세요."));
    }
}
