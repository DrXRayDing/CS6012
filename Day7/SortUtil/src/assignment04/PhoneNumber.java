package assignment04;

import java.util.Comparator;

/**
 * Class representation of a phone number.
 * 
 */
public class PhoneNumber {

  private String areaCode;

  private String trunk;

  private String rest;

  public PhoneNumber(String phoneNum) {
    phoneNum = phoneNum.replaceAll("-|\\s|\\.|\\(|\\)", "");

    boolean isValid = true;
    if (phoneNum.length() != 10)
      isValid = false;
    for (int i = 0; isValid && i < 10; i++)
      if (!Character.isDigit(phoneNum.charAt(i)))
        isValid = false;

    if (isValid) {
      areaCode = phoneNum.substring(0, 3);
      trunk = phoneNum.substring(3, 6);
      rest = phoneNum.substring(6, 10);
    } else {
      areaCode = "000";
      trunk = "000";
      rest = "000";
      System.err
          .println("Phone number \"" + phoneNum + "\" is not formatted correctly, initializing as " + toString() + ".");
    }
  }

  public boolean equals(Object other) {
    if (!(other instanceof PhoneNumber))
      return false;

    PhoneNumber rhs = (PhoneNumber) other;
    PhoneNumber lhs = this;

    return lhs.areaCode.equals(rhs.areaCode) && lhs.trunk.equals(rhs.trunk) && lhs.rest.equals(rhs.rest);
  }

  public String toString() {
    return "(" + areaCode + ") " + trunk + "-" + rest;
  }

  @Override
  public int hashCode() {
    return areaCode.hashCode() + trunk.hashCode() + rest.hashCode();
  }

  protected static class OrderByNumber implements
          Comparator<PhoneNumber> {
    /**
     * Returns a negative value if lhs is smaller than rhs. Returns a positive
     * value if lhs is larger than rhs. Returns 0 if lhs and rhs are equal.
     */
    public int compare(PhoneNumber lhs,
                       PhoneNumber rhs) {
      int areaCodeCompare = lhs.areaCode.compareTo(rhs.areaCode);
      int trunkCompare = lhs.trunk.compareTo(rhs.trunk);
      if (areaCodeCompare != 0) {
        return areaCodeCompare;
      }
      else if (trunkCompare != 0){
        return trunkCompare;  // If area codes are the same, use trunk as tie-breaker
      }
      else {
        return lhs.rest.compareTo(rhs.rest); // If trunks are still the same, use trunk as tie-breaker
      }

    }
  }
}
