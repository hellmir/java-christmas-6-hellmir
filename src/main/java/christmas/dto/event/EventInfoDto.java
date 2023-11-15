package christmas.dto.event;

import christmas.domain.event.Badge;
import christmas.dto.order.PaymentDto;

public class EventInfoDto {
    private final PaymentDto paymentDto;
    private final GiveawayDto giveawayDto;
    private final ChristmasDiscountDto christmasDiscountDto;
    private final WeekdayDiscountDto weekdayDiscountDto;
    private final WeekendDiscountDto weekendDiscountDto;
    private final Badge badge;

    private EventInfoDto(PaymentDto paymentDto, GiveawayDto giveawayDto,
                         ChristmasDiscountDto christmasDiscountDto, WeekdayDiscountDto weekdayDiscountDto,
                         WeekendDiscountDto weekendDiscountDto, Badge badge) {
        this.paymentDto = paymentDto;
        this.giveawayDto = giveawayDto;
        this.christmasDiscountDto = christmasDiscountDto;
        this.weekdayDiscountDto = weekdayDiscountDto;
        this.weekendDiscountDto = weekendDiscountDto;
        this.badge = badge;
    }

    public static EventInfoDto of(PaymentDto paymentDto, GiveawayDto giveawayDto,
                                  ChristmasDiscountDto christmasDiscountDto, WeekdayDiscountDto weekdayDiscountDto,
                                  WeekendDiscountDto weekendDiscountDto, Badge badge) {
        return new EventInfoDto(paymentDto, giveawayDto, christmasDiscountDto,
                weekdayDiscountDto, weekendDiscountDto, badge);
    }

    public PaymentDto getPaymentDto() {
        return paymentDto;
    }

    public GiveawayDto getGiveawayDto() {
        return giveawayDto;
    }

    public ChristmasDiscountDto getChristmasDiscountDto() {
        return christmasDiscountDto;
    }

    public WeekdayDiscountDto getWeekdayDiscountDto() {
        return weekdayDiscountDto;
    }

    public WeekendDiscountDto getWeekendDiscountDto() {
        return weekendDiscountDto;
    }

    public Badge getBadge() {
        return badge;
    }
}
