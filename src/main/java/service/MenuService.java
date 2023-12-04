package service;

import domain.Menu;
import domain.MenuRepository;

public class MenuService {

    public static Menu findMenuByNumber(int number) {
        return MenuRepository.menus().stream()
            .filter(menu -> menu.getNumber() == number)
            .findFirst()
            .orElseThrow(null);
    }
}
