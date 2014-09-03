import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

import org.junit.*;

import com.autentia.validation.*;

public class SpanishDocumentValidatorTest {

	@Test
	public void isPersonalDocumentValidShouldReturnFalseIfTheSpanishPersonalDocumentIsInvalid() throws Exception {

		assertThat(SpanishDocumentValidator.isPersonalDocumentValid("1T"), is(false));
	}

	@Test
	public void isPersonalDocumentValidShouldReturnTrueIfTheSpanishPersonalDocumentIsValid() throws Exception {

		assertThat(SpanishDocumentValidator.isPersonalDocumentValid("0T"), is(true));
	}

	@Test
	public void isPersonalDocumentValidShouldReturnFalseIfDocumentHasSizeMinorThanOne() throws Exception {

		assertThat(SpanishDocumentValidator.isPersonalDocumentValid("0"), is(false));
	}

	@Test
	public void isPersonalDocumentValidShouldReturnFalseIfDocumentHasLetterInvalid() throws Exception {

		assertThat(SpanishDocumentValidator.isPersonalDocumentValid("1Ñ"), is(false));
	}

	@Test
	public void isEnterpriseDocumentValidShouldReturnFalseIfTheSpanishEnterpriseDocumentIsInvalid() throws Exception {
		assertThat(SpanishDocumentValidator.isEnterpriseDocumentValid("1T"), is(false));

	}

	@Test
	public void isEnterpriseDocumentValidShouldReturnTrueIfTheSpanishEnterpriseDocumentIsValid() throws Exception {

		assertThat(SpanishDocumentValidator.isEnterpriseDocumentValid("P2739791H"), is(true));
	}

	@Test
	public void isEnterpriseDocumentValidShouldReturnTrueWhenFirstCharacterLetter() throws Exception {

		assertThat(SpanishDocumentValidator.isEnterpriseDocumentValid("D41611880"), is(true));
	}

	@Test
	public void isEnterpriseDocumentValidShouldReturnTrueWhenFirstCharacterIsR() throws Exception {

		assertThat(SpanishDocumentValidator.isEnterpriseDocumentValid("R0947906D"), is(true));
	}
}
