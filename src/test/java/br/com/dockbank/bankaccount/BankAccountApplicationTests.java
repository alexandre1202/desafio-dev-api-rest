package br.com.dockbank.bankaccount;

import static org.assertj.core.api.Assertions.*;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

class BankAccountApplicationTests {

	@Test
	void main() {
		BankAccountApplication bankAccountApplication = new BankAccountApplication();
		assertThat(bankAccountApplication).isInstanceOf(BankAccountApplication.class);
	}

}
