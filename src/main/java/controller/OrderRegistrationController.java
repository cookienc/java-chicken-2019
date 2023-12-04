package controller;

import domain.menu.Menu;
import domain.menu.MenuRepository;
import domain.table.Table;
import domain.table.TableRepository;
import java.util.List;
import service.MenuService;
import service.OrderService;
import service.TableService;
import util.Retry;
import view.InputView;
import view.OutputView;

public class OrderRegistrationController implements Controller {

    @Override
    public void run() {
        // 주문등록
        printTables();

        final int tableNumber = getTableNumber();

        printMenus();

        final int menuNumber = getMenuNumber();

        final int menuQuantity = InputView.inputMenuQuantity();

        OrderService.takeOrder(tableNumber, menuNumber, menuQuantity);
    }

    private static int getTableNumber() {
        return Retry.execute(() -> {
            final int tableNumber = InputView.inputTableNumber();
            TableService.validateTableExistsByTableNumber(tableNumber);
            return tableNumber;
        });
    }

    private static int getMenuNumber() {
        return Retry.execute(() -> {
            final int menuNumber = InputView.inputRegisterMenu();
            MenuService.validateMenuNumberExistsByMenuNumber(menuNumber);
            return menuNumber;
        });
    }

    private static void printTables() {
        final List<Table> tables = TableRepository.tables();
        OutputView.printTables(tables);
    }

    private static void printMenus() {
        final List<Menu> menus = MenuRepository.menus();
        OutputView.printMenus(menus);
    }
}
