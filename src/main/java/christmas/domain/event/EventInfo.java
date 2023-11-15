package christmas.domain.event;

import christmas.domain.order.MenuInformation;
import christmas.domain.order.Payment;
import christmas.dto.event.*;
import christmas.dto.order.PaymentDto;

import java.util.Objects;

import static christmas.configuration.EventConfig.GIVEAWAY_PRODUCT;

public class EventInfo {
    private Payment payment;
    private Giveaway giveaway;
    private ChristmasDiscount christmasDiscount;
    private WeekdayDiscount weekdayDiscount;
    private WeekendDiscount weekendDiscount;
    private Badge badge;

    public EventInfo(Payment payment) {
        this.payment = payment;
        giveaway = new Giveaway(MenuInformation.NONE);
        christmasDiscount = new ChristmasDiscount();
        weekdayDiscount = new WeekdayDiscount();
        weekendDiscount = new WeekendDiscount();
        badge = Badge.NONE;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EventInfo eventInfo = (EventInfo) o;
        return Objects.equals(payment, eventInfo.payment) && Objects.equals(giveaway, eventInfo.giveaway)
                && Objects.equals(christmasDiscount, eventInfo.christmasDiscount)
                && Objects.equals(weekdayDiscount, eventInfo.weekdayDiscount) &&
                Objects.equals(weekendDiscount, eventInfo.weekendDiscount) && badge == eventInfo.badge;
    }

    @Override
    public int hashCode() {
        return Objects.hash(payment, giveaway, christmasDiscount, weekdayDiscount, weekendDiscount, badge);
    }

    public EventInfoDto toDto() {
        PaymentDto paymentDto = payment.toDto();
        GiveawayDto giveawayDto = giveaway.toDto();
        ChristmasDiscountDto christmasDiscountDto = christmasDiscount.toDto();
        WeekdayDiscountDto weekdayDiscountDto = weekdayDiscount.toDto();
        WeekendDiscountDto weekendDiscountDto = weekendDiscount.toDto();

        return EventInfoDto.of(paymentDto, giveawayDto, christmasDiscountDto,
                weekdayDiscountDto, weekendDiscountDto, badge);
    }

    public void updateGiveawayApplication(Payment payment) {
        if (payment.isGiveawayApplied()) {
            giveaway = new Giveaway(GIVEAWAY_PRODUCT);
        }
    }

    public void updateChristmasDiscount(ChosenDate chosenDate) {
        christmasDiscount = ChristmasDiscount.from(chosenDate);
        payment.updateDiscountAmount(christmasDiscount);
    }

    }
}
