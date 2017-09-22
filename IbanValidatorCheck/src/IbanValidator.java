import java.math.BigInteger;

public class IbanValidator {

	public boolean validator(String iban) {
		try {
			validateLength(iban);

			validateCountry(iban);

			validateChecksum(iban);

			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	private static void validateChecksum(String iban) {
		BigInteger ibanAsInt = convertToInt(iban);
		BigInteger mod = ibanAsInt.mod(BigInteger.valueOf(97));
		if (mod.intValue() != 1)
			throw new InvalidIbanChecksum();
	}

	private static BigInteger convertToInt(String iban) {

		String newIban = iban.substring(4) + iban.substring(0, 4);
		StringBuilder numericAccountNumber = new StringBuilder();

		for (int i = 0; i < newIban.length(); i++) {
			numericAccountNumber.append(Character.getNumericValue(newIban.charAt(i)));
		}
		return new BigInteger(numericAccountNumber.toString());
	}

	private static void validateLength(String iban) {
		int length = iban.length();
		if (length < 15 || length > 34)
			throw new InvalidIbanLength();
	}

	private static void validateCountry(String iban) {
		try {
			CountryCode.valueOf(getCountryCode(iban));
		} catch (IllegalArgumentException e) {
			throw new InvalidIbanCountry();
		}
	}

	private static String getCountryCode(String iban) {
		return iban.substring(0, 2);
	}
}
