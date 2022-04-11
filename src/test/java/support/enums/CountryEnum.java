package support.enums;

public enum CountryEnum {
    AUSTRALIA_AU("AUSTRALIA"),
    AUSTRIA_AT("AUSTRIA"),
    BRAZIL_BR("BRAZIL"),
    CANADA_CA("CANADA"),
    CHINA_CN("CHINA"),
    ROATIA_HR("CROATIA"),
    ENMARK_DK("DENMARK"),
    EGYPT_EG("EGYPT"),
    NGLAND_UK("ENGLAND"),
    FRANCE_FR("FRANCE"),
    ERMANY_DE("GERMANY"),
    GREECE_GR("GREECE"),
    INDIA_IN("INDIA"),
    ISRAEL_IL("ISRAEL"),
    ITALY_IT("ITALY"),
    JAPAN_JP("JAPAN"),
    MEXICO_MX("MEXICO"),
    NETHERLAND_NL("NETHERLAND"),
    NORWAY_NO("NORWAY"),
    NEW_ZEALAND_NZ("NEW_ZEALAND"),
    SPAIN_ES("SPAIN"),
    SOUTH_KOREA_KR("SOUTH_KOREA"),
    NORTH_KOREA_KP("NORTH_KOREA"),
    RTUGAL_PT("PORTUGAL"),
    QATAR_QA("QATAR"),
    OMANIA_RO("ROMANIA"),
    RUSSIA_RU("RUSSIA"),
    SOUTH_AFRICA_ZA("SOUTH_AFRICA"),
    SWEDEN_SE("SWEDEN"),
    SWITZERLAND_CH("SWITZERLAND"),
    AILAND_TH("THAILAND"),
    UNISIA_TN("TUNISIA"),
    TURKEY_TR("TURKEY"),
    KRAINE_UA("UKRAINE"),
    UNITED_KINGDOM_UK("UNITED_KINGDOM"),
    UNITED_STATES_US("UNITED_STATES"),
    YEMEN_YE("YEMEN");


    private String nameCountry;

    private CountryEnum(String nameCountry) {
        this.nameCountry = nameCountry;
    }

    public static CountryEnum getCountry(String nameCountry) {
        for (CountryEnum l : CountryEnum.values()) {
            if (l.nameCountry.equals(nameCountry)) return l;
        }
        throw new IllegalArgumentException("country invalid or not found.");
    }

}
