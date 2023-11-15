package christmas.domain.order;

import christmas.dto.order.OrderDto;
import christmas.dto.order.OrderMenuDto;
import christmas.validation.InputFormatValidator;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static christmas.configuration.OrderConfig.MAX_MENU_QUANTITY;
import static christmas.message.ErrorMessage.*;

public class Order {
    private List<OrderMenu> orderMenus;
    private int totalMenuQuantity;

    public Order() {
        orderMenus = new ArrayList<>();
    }

    public Order(List<OrderMenu> orderMenus) {
        this.orderMenus = orderMenus;
    }

    public static Order from(OrderDto orderDto) {
        List<OrderMenu> orderMenus = new ArrayList<>();

        for (int i = 0; i < orderDto.size(); i++) {
            OrderMenuDto orderMenuDto = orderDto.getOrderMenuDtos().get(i);
            OrderMenu orderMenu = OrderMenu.from(orderMenuDto);
            orderMenus.add(orderMenu);
        }

        return new Order(orderMenus);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return totalMenuQuantity == order.totalMenuQuantity && Objects.equals(orderMenus, order.orderMenus);
    }

    @Override
    public int hashCode() {
        return Objects.hash(orderMenus, totalMenuQuantity);
    }

    public OrderDto toDto() {
        List<OrderMenuDto> orderMenuDtos = new ArrayList<>();
        for (OrderMenu orderMenu : orderMenus) {
            orderMenuDtos.add(orderMenu.toDto());
        }

        return new OrderDto(orderMenuDtos);
    }

    public void addOrderMenu(String[] orderMenuNameAndQuantity) {
        String koreanMenuName = orderMenuNameAndQuantity[0];
        MenuInformation menuInformation = InputFormatValidator.validateMenuName(koreanMenuName);

        String menuQuantity = orderMenuNameAndQuantity[1];
        InputFormatValidator.validateMenuQuantityFormat(menuQuantity);

        OrderMenu orderMenu = new OrderMenu(menuInformation);
        InputFormatValidator.validateDuplicateOrderMenu(orderMenus, orderMenu);

        int parsedMenuQuantity = Integer.parseInt(menuQuantity);
        orderMenu.addMenuQuantity(parsedMenuQuantity);
        totalMenuQuantity += parsedMenuQuantity;

        orderMenus.add(orderMenu);
    }

    public void validateOrder() {
        validateTotalMenuQuantity();

        for (OrderMenu orderMenu : orderMenus) {
            boolean isBeverage = checkBeverage(orderMenu);
            if (!isBeverage) {
                return;
            }
        }

        throw new IllegalArgumentException(ERROR_MESSAGE_HEAD + ONLY_BEVERAGES_ORDERED_EXCEPTION);
    }

    public int computeTotalOrderPrice() {
        int totalPrice = 0;

        for (int i = 0; i < orderMenus.size(); i++) {
            OrderMenu orderMenu = orderMenus.get(i);
            totalPrice += orderMenu.computeMenuPrice();
        }

        return totalPrice;
    }

    private void validateTotalMenuQuantity() {
        if (totalMenuQuantity <= MAX_MENU_QUANTITY) {
            return;
        }

        throw new IllegalArgumentException(ERROR_MESSAGE_HEAD + MENU_QUANTITY_EXCEEDED_EXCEPTION);
    }

    private boolean checkBeverage(OrderMenu orderMenu) {
        if (orderMenu.getMenuInformation().isBeverage()) {
            return true;
        }

        return false;
    }
}
