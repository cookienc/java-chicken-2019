package service;

import domain.menu.Menu;
import domain.menu.MenuRepository;

public class MenuService {

    public static Menu findMenuByNumber(int number) {
        return MenuRepository.menus().stream()
            .filter(menu -> menu.getNumber() == number)
            .findFirst()
            .orElseThrow(null);
    }

    public static void validateMenuNumberExistsByMenuNumber(int menuNumber) {
        MenuRepository.menus().stream()
            .filter(menu -> menu.getNumber() == menuNumber)
            .findFirst()
            .orElseThrow(() -> new IllegalArgumentException("[ERROR] 존재하지 않는 메뉴 번호입니다."));
    }
}
