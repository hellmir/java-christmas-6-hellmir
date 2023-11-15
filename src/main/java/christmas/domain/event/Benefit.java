package christmas.domain.event;

import christmas.dto.event.BenefitDto;

import java.util.Objects;

import static christmas.domain.event.Badge.*;

public class Benefit {
    private int benefitAmount;

    public Benefit(int benefitAmount) {
        this.benefitAmount = benefitAmount;
    }

    public static Benefit from(BenefitDto benefitDto) {
        return new Benefit(benefitDto.getBenefitAmount());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Benefit benefit = (Benefit) o;
        return benefitAmount == benefit.benefitAmount;
    }

    @Override
    public int hashCode() {
        return Objects.hash(benefitAmount);
    }

    public void addBenefitAmount(int money) {
        benefitAmount += money;
    }

    public BenefitDto toDto() {
        return new BenefitDto(benefitAmount);
    }

    public Badge chooseBadge(Badge badge) {
        if (badge.isSanta(benefitAmount)) {
            return SANTA;

        }

        if (badge.isTree(benefitAmount)) {
            return TREE;

        }

        if (badge.isStar(benefitAmount)) {
            return STAR;
        }

        return NONE;
    }
}
