package gamePackage.interfacePackage;

public class Color{

  // équivalence                            {"NONE", "RED",     "BLUE",       "GREEN",      "PURPLE",     "YELLOW",     "CYAN"};
  private final static String[] colorsCode = {"", "\u001B[31m", "\u001B[34m", "\u001B[32m", "\u001B[35m", "\u001B[33m", "\u001B[36m"};

  // équivalence                          {"NORMAL", "BOLD",   "Italic"};
  private final static String[] formatType = {"", "\u001B[1m", "\u001B[3m"};

  public static String getTextReset(){
    String reset = "\u001B[0m";
    return reset;
  }

  public static int getTextBold(){
    return 1;
  }

  public static int getTextItalic(){
    return 2;
  }

  public static int getNone(){
    return 0;
  }

  public static int getRed(){
    return 1;
  }

  public static int getBlue(){
    return 2;
  }

  public static int getGreen(){
    return 3;
  }

  public static int getPurple(){
    return 4;
  }

  public static int getYellow(){
    return 5;
  }

  public static int getCyan(){
    return 6;
  }

  public static String setColor(String buffer, int indexColor, int indexFormat) {
    String reset = getTextReset();
    return formatType[indexFormat] + colorsCode[indexColor] + buffer + reset;
  }
}
