package christmas.domain.event;

import christmas.domain.order.Payment;

import java.util.Objects;

import static christmas.configuration.EventConfig.GIVEAWAY_PRODUCT;

public class EventInfo {
    private Giveaway giveaway;
    private ChristmasDiscount christmasDiscount;
    private WeekdayDiscount weekdayDiscount;
    private WeekendDiscount weekendDiscount;
    private Badge badge;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EventInfo eventInfo = (EventInfo) o;
        return Objects.equals(giveaway, eventInfo.giveaway) && Objects.equals(christmasDiscount, eventInfo.christmasDiscount) && Objects.equals(weekdayDiscount, eventInfo.weekdayDiscount) && Objects.equals(weekendDiscount, eventInfo.weekendDiscount) && Objects.equals(badge, eventInfo.badge);
    }

    @Override
    public int hashCode() {
        return Objects.hash(giveaway, christmasDiscount, weekdayDiscount, weekendDiscount, badge);
    }

    public void updateGiveawayApplication(Payment payment) {
        if (payment.isGiveawayApplied()) {
            giveaway = new Giveaway(GIVEAWAY_PRODUCT);
        }
    }
}
