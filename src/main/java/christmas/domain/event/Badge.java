package christmas.domain.event;

public enum Badge {
    STAR("별", 5_000),
    TREE("트리", 10_000),
    SANTA("산타", 20_000);

    private final String koreanName;
    private final int benefitAmount;

    Badge(String koreanName, int benefitAmount) {
        this.koreanName = koreanName;
        this.benefitAmount = benefitAmount;
    }
}
