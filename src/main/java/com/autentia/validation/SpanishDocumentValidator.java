package com.autentia.validation;

import java.util.*;

public final class SpanishDocumentValidator {

	private static final String NIF_LETTER_ASSOCIATION = "TRWAGMYFPDXBNJZSQVHLCKE";

	private static final String CIF_LETTER_ASSOCIATION = "ABCDEFGHPQSKLMX";

	private SpanishDocumentValidator() {

	}

	public static boolean isPersonalDocumentValid(String document) {
		final String documentUpperCase = document.toUpperCase();

		if (!documentUpperCase.matches("[0-9]{1,8}[" + NIF_LETTER_ASSOCIATION + "]")) {
			return false;
		}

		try {
			final Integer number = Integer.parseInt(documentUpperCase.substring(0, documentUpperCase.length() - 1));
			final String letter = documentUpperCase.substring(documentUpperCase.length() - 1);

			if (letter.equals(Character.toString(NIF_LETTER_ASSOCIATION.charAt(number % 23)))) {
				return true;
			}

		} catch (Exception e) {
		}

		return false;
	}

	public static boolean isEnterpriseDocumentValid(String document) {
		final String documentUpperCase = document.toUpperCase();
		final List<String> specialLettersForLetter = Arrays.asList("K", "P", "Q", "S");
		final List<String> specialLettersForDigit = Arrays.asList("A", "B", "E", "H");

		if (documentUpperCase.length() != 9) {
			return false;
		}

		final String letter = documentUpperCase.substring(0, 1);
		final String number = documentUpperCase.substring(1, 8);
		final String controlDigitDocument = documentUpperCase.substring(8);
		final int addValue = addEven(number) + addOdd(number);

		if (specialLettersForLetter.contains(letter)) {
			return isControlDigitValidForCharacter(controlDigitDocument, addValue);

		} else if (specialLettersForDigit.contains(letter)) {
			return isControlDigitValidForDigit(controlDigitDocument, addValue);
		} else if (Character.isDigit(controlDigitDocument.charAt(0))) {
			return isControlDigitValidForDigit(controlDigitDocument, addValue);
		} else {
			return isControlDigitValidForCharacter(controlDigitDocument, addValue);
		}

	}

	private static boolean isControlDigitValidForDigit(final String controlDigitDocument, final int addValue) {
		int controlDigit = 10 - (addValue % 10);

		if (controlDigit == 10) {
			controlDigit = 0;
		}

		if (controlDigit == Integer.parseInt(controlDigitDocument)) {
			return true;
		}

		return false;
	}

	private static boolean isControlDigitValidForCharacter(final String controlDigitDocument, final int addValue) {
		int controlDigit = 64 + (10 - (addValue % 10));

		if (controlDigit == 74) {
			controlDigit = 64;
		}

		if (controlDigitDocument.charAt(0) == Character.toChars(controlDigit)[0]) {
			return true;
		}

		return false;
	}

	private static int addOdd(String number) {
		int add = 0;

		for (int i = 0; i < number.length(); i = i + 2) {
			char digit = number.charAt(i);

			switch (digit) {
			case '1':
			case '2':
			case '3':
			case '4':
				add = add + (Character.getNumericValue(digit) * 2);
				break;
			case '5':
				add = add + 1;
				break;
			case '6':
				add = add + 3;
				break;
			case '7':
				add = add + 5;
				break;
			case '8':
				add = add + 7;
				break;
			case '9':
				add = add + 9;
				break;
			}
		}

		return add;
	}

	private static int addEven(String number) {
		int add = 0;
		for (int i = 1; i < (number.length() - 1); i = i + 2) {
			add = add + Character.getNumericValue(number.charAt(i));
		}

		return add;
	}
}