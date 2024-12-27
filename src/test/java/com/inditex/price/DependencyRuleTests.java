package com.inditex.price;

import com.inditex.price.archunit.HexagonalArchitecture;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import org.junit.jupiter.api.Test;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noClasses;

class DependencyRuleTests {

	@Test
	void validateRegistrationContextArchitecture() {
		HexagonalArchitecture.basePackage("com.inditex.price")
				.withDomainLayer("application.domain")
				.withAdaptersLayer("outside.adapters")
				.incoming("in")
				.outgoing("out")
				.and()
				.withApplicationLayer("application")
				.incomingPorts("ports.in")
				.outgoingPorts("ports.out")
				.and()
				.withConfiguration("configuration")
				.check(new ClassFileImporter()
						.importPackages("com.inditex.price.."));
	}

	@Test
	void domainApplicationDoesNotDependOnOutside() {
		noClasses()
				.that()
				.resideInAPackage("com.inditex.price.application..")
				.should()
				.dependOnClassesThat()
				.resideOutsideOfPackages(
						"com.inditex.price.application..",
						"lombok..",
						"jakarta.validation..",
						"org.springframework.core.annotation..",
						"org.springframework.stereotype..",
						"org.mapstruct..",
						"org.junit..",
						"org.mockito..",
						"org.springframework.test..",
						"java.."
				)
				.check(new ClassFileImporter()
						.importPackages("com.inditex.price.."));
	}

	@Test
	void domainDomainDoesNotDependOnOutside() {
		noClasses()
				.that()
				.resideInAPackage("com.inditex.price.application.domain..")
				.should()
				.dependOnClassesThat()
				.resideOutsideOfPackages(
						"com.inditex.price.application.domain..",
						"lombok..",
						"java.."
				)
				.check(new ClassFileImporter()
						.importPackages("com.inditex.price.."));
	}
}
