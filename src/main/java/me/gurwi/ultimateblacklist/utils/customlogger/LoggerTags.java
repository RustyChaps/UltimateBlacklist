package me.gurwi.ultimateblacklist.utils.customlogger;

public enum LoggerTags {

    SUCCESS_TAG("§2§l§o SUCCESS §r §a"),
    LOAD_TAG("§3§l§o LOADING §r §b"),
    INFO_TAG("§6§l§o   INFO  §r §e"),
    ERROR_TAG("§4§l§o ERROR §r §c"),
    WARNING_TAG("§6§l§o WARNING! §r §e"),

    DB_SUCCESS_TAG("§2§l§o DB INFO §r §a"),
    DB_WARNING_TAG("§6§l§o DB INFO §r §e"),
    DB_ERROR_TAG("§4§l§o DB ERROR §r §c"),

    CONFIG_ERROR_TAG("§4§l§o CONFIG ERROR §r §c"),

    API_ERROR_TAG("§4§l§o API ERROR §r §c");

    // METHODS

    private final String tag;
    private LoggerTags(String tag) {this.tag = tag;}

    public String getTag() {return this.tag;}

}
