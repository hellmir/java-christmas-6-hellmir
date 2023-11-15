package christmas.domain.event;

import christmas.domain.order.MenuInformation;
import christmas.domain.order.Order;
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
    private SpecialDiscount specialDiscount;
    private Badge badge;

    public EventInfo(Payment payment) {
        this.payment = payment;
        giveaway = new Giveaway(MenuInformation.NONE);
        christmasDiscount = new ChristmasDiscount();
        weekdayDiscount = new WeekdayDiscount();
        weekendDiscount = new WeekendDiscount();
        specialDiscount = new SpecialDiscount();
        badge = Badge.NONE;
    }

    private EventInfo(Payment payment, Giveaway giveaway, ChristmasDiscount christmasDiscount,
                      WeekdayDiscount weekdayDiscount, WeekendDiscount weekendDiscount,
                      SpecialDiscount specialDiscount, Badge badge) {
        this.payment = payment;
        this.giveaway = giveaway;
        this.christmasDiscount = christmasDiscount;
        this.weekdayDiscount = weekdayDiscount;
        this.weekendDiscount = weekendDiscount;
        this.specialDiscount = specialDiscount;
        this.badge = badge;
    }

    public static EventInfo from(EventInfoDto eventInfoDto) {
        return new EventInfo(
                Payment.from(eventInfoDto.getPaymentDto()),
                Giveaway.from(eventInfoDto.getGiveawayDto()),
                ChristmasDiscount.from(eventInfoDto.getChristmasDiscountDto()),
                WeekdayDiscount.from(eventInfoDto.getWeekdayDiscountDto()),
                WeekendDiscount.from(eventInfoDto.getWeekendDiscountDto()),
                SpecialDiscount.from(eventInfoDto.getSpecialDiscountDto()),
                eventInfoDto.getBadge()
        );
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EventInfo eventInfo = (EventInfo) o;
        return Objects.equals(payment, eventInfo.payment) && Objects.equals(giveaway, eventInfo.giveaway)
                && Objects.equals(christmasDiscount, eventInfo.christmasDiscount)
                && Objects.equals(weekdayDiscount, eventInfo.weekdayDiscount)
                && Objects.equals(weekendDiscount, eventInfo.weekendDiscount)
                && Objects.equals(specialDiscount, eventInfo.specialDiscount) && badge == eventInfo.badge;
    }

    @Override
    public int hashCode() {
        return Objects.hash(payment, giveaway, christmasDiscount, weekdayDiscount, weekendDiscount, specialDiscount, badge);
    }

    public EventInfoDto toDto() {
        PaymentDto paymentDto = payment.toDto();
        GiveawayDto giveawayDto = giveaway.toDto();
        ChristmasDiscountDto christmasDiscountDto = christmasDiscount.toDto();
        WeekdayDiscountDto weekdayDiscountDto = weekdayDiscount.toDto();
        WeekendDiscountDto weekendDiscountDto = weekendDiscount.toDto();
        SpecialDiscountDto specialDiscountDto = specialDiscount.toDto();

        return EventInfoDto.of(paymentDto, giveawayDto, christmasDiscountDto,
                weekdayDiscountDto, weekendDiscountDto, specialDiscountDto, badge);
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

    public void updateDayOfWeekDiscount(ChosenDate chosenDate, Order order) {
        boolean isWeekday = chosenDate.isWeekday();

        if (isWeekday) {
            weekdayDiscount = WeekdayDiscount.from(order);
            payment.updateDiscountAmount(weekdayDiscount);
            return;
        }

        weekendDiscount = WeekendDiscount.from(order);
        payment.updateDiscountAmount(weekendDiscount);
    }

    public void updateSpecialDiscount(ChosenDate chosenDate) {
        boolean isSpecialDay = chosenDate.isSpecialDay();

        if (isSpecialDay) {
            specialDiscount = SpecialDiscount.updateDiscount();
            payment.updateDiscountAmount(specialDiscount);
        }
    }
}
