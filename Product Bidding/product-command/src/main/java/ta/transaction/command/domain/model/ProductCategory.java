package ta.transaction.command.domain.model;

public enum ProductCategory {

    PAINTING("Painting"),
    SCULPTOR("Sculptor"),
    ORNAMENT("Ornament");

    private final String displayName;

    ProductCategory(String displayName) {

        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}
