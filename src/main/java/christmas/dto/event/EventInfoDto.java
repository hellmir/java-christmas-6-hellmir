package christmas.dto.event;

import christmas.domain.event.Badge;

public class EventInfoDto {
    private final GiveawayDto giveawayDto;
    private final ChristmasDiscountDto christmasDiscountDto;
    private final WeekdayDiscountDto weekdayDiscountDto;
    private final WeekendDiscountDto weekendDiscountDto;
    private final Badge badge;

    public EventInfoDto() {
        giveawayDto = null;
        christmasDiscountDto = null;
        weekdayDiscountDto = null;
        weekendDiscountDto = null;
        badge = null;
    }

    private EventInfoDto(GiveawayDto giveawayDto, ChristmasDiscountDto christmasDiscountDto,
                         WeekdayDiscountDto weekdayDiscountDto,
                         WeekendDiscountDto weekendDiscountDto, Badge badge) {
        this.giveawayDto = giveawayDto;
        this.christmasDiscountDto = christmasDiscountDto;
        this.weekdayDiscountDto = weekdayDiscountDto;
        this.weekendDiscountDto = weekendDiscountDto;
        this.badge = badge;
    }

    public static EventInfoDto of(GiveawayDto giveawayDto, ChristmasDiscountDto christmasDiscountDto,
                                  WeekdayDiscountDto weekdayDiscountDto,
                                  WeekendDiscountDto weekendDiscountDto, Badge badge) {
        return new EventInfoDto(giveawayDto, christmasDiscountDto, weekdayDiscountDto, weekendDiscountDto, badge);
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
