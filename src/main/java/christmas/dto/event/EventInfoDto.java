package christmas.dto.event;

import christmas.domain.event.Badge;
import christmas.dto.order.PaymentDto;

public class EventInfoDto {
    private final PaymentDto paymentDto;
    private final GiveawayDto giveawayDto;
    private final ChristmasDiscountDto christmasDiscountDto;
    private final WeekdayDiscountDto weekdayDiscountDto;
    private final WeekendDiscountDto weekendDiscountDto;
    private final SpecialDiscountDto specialDiscountDto;
    private final BenefitDto benefitDto;
    private final Badge badge;

    private EventInfoDto(PaymentDto paymentDto, GiveawayDto giveawayDto, ChristmasDiscountDto christmasDiscountDto,
                         WeekdayDiscountDto weekdayDiscountDto, WeekendDiscountDto weekendDiscountDto,
                         SpecialDiscountDto specialDiscountDto, BenefitDto benefitDto, Badge badge) {
        this.paymentDto = paymentDto;
        this.giveawayDto = giveawayDto;
        this.christmasDiscountDto = christmasDiscountDto;
        this.weekdayDiscountDto = weekdayDiscountDto;
        this.weekendDiscountDto = weekendDiscountDto;
        this.specialDiscountDto = specialDiscountDto;
        this.benefitDto = benefitDto;
        this.badge = badge;
    }

    public static EventInfoDto of(PaymentDto paymentDto, GiveawayDto giveawayDto,
                                  ChristmasDiscountDto christmasDiscountDto, WeekdayDiscountDto weekdayDiscountDto,
                                  WeekendDiscountDto weekendDiscountDto, SpecialDiscountDto specialDiscountDto,
                                  BenefitDto benefitDto, Badge badge) {
        return new EventInfoDto(paymentDto, giveawayDto, christmasDiscountDto,
                weekdayDiscountDto, weekendDiscountDto, specialDiscountDto, benefitDto, badge);
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

    public SpecialDiscountDto getSpecialDiscountDto() {
        return specialDiscountDto;
    }

    public BenefitDto getBenefitDto() {
        return benefitDto;
    }

    public Badge getBadge() {
        return badge;
    }
}
