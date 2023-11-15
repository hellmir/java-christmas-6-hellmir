package christmas.domain.event;

public enum Badge {
    NONE("없음", 0),
    STAR("별", 5_000),
    TREE("트리", 10_000),
    SANTA("산타", 20_000);

    private final String koreanName;
    private final int benefitAmount;

    Badge(String koreanName, int benefitAmount) {
        this.koreanName = koreanName;
        this.benefitAmount = benefitAmount;
    }

    public boolean isNone() {
        return this == NONE;
    }

    public boolean isSanta(int benefitAmount) {
        return benefitAmount >= SANTA.benefitAmount;
    }

    public boolean isTree(int benefitAmount) {
        return benefitAmount >= TREE.benefitAmount;
    }

    public boolean isStar(int benefitAmount) {
        return benefitAmount >= STAR.benefitAmount;
    }

    public int getBenefitAmount() {
        return benefitAmount;
    }
}
