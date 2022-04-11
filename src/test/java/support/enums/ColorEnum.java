package support.enums;

public enum ColorEnum {

    RED("RED", "DD3A5B"),
    GRAY("GRAY", "C3C3C3"),
    WHITE("WHITE", "FFFFFF"),
    BLUE("BLUE", "3683D1"),
    PURPLE("PURPLE", "545195"),
    YELLOW("YELLOW", "FCC23D"),
    UNASSIGNED("UNASSIGNED", "ABCDEF"),
    BLACK("BLACK", "414141"),
    TURQUOISE("TURQUOISE", "55CDD5");


    private String colorCode;
    private String colorName;

    ColorEnum(String colorName, String colorCode) {
        this.colorName = colorName;
        this.colorCode = colorCode;

    }

    public static String getColor(String colorName) {
        for (ColorEnum l : ColorEnum.values()) {
            if (l.colorName.equals(colorName)) return l.getColorCode();
        }
        throw new IllegalArgumentException("Color invalid or not found.");
    }

    public String getColorCode() {
        return colorCode;
    }
}
