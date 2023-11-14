package christmas.dto;

import java.util.List;

public class OrderDto {
    private final List<OrderMenuDto> orderMenuDtos;

    public OrderDto(List<OrderMenuDto> orderMenuDtos) {
        this.orderMenuDtos = orderMenuDtos;
    }

    public List<OrderMenuDto> getOrderMenuDtos() {
        return orderMenuDtos;
    }

    public int size() {
        return orderMenuDtos.size();
    }
}
