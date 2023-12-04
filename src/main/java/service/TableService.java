package service;

import domain.table.TableRepository;

public class TableService {

    public static void validateTableExistsByTableNumber(int tableNumber) {
        TableRepository.tables().stream()
            .filter(table -> table.getNumber() == tableNumber)
            .findFirst()
            .orElseThrow(() -> new IllegalArgumentException("[ERROR] 존재하지 않는 테이블 번호입니다."));
    }
}
