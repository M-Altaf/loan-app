plugins{
	java
	id("org.springframework.boot")version "4.0.2"
	id("io.spring.dependency-management")version "1.1.7"
}
group = "com.loan"
version = "0.0.1-SNAPSHOT"
description = "Loan app for loan managment"
java {
	toolchain {
		languageVersion = JavaLanguageVersion.of(25)
	}
}
configurations {
	compileOnly {
		extendsFrom(configurations.annotationProcessor.get())
	}
}

repositories { mavenCentral()
}
dependencies {

	implementation ("org.springframework.boot:spring-boot-starter-web")
	implementation ("org.springframework.boot:spring-boot-starter-data-jpa")
	implementation ("org.springframework.boot:spring-boot-starter-security")
	implementation ("org.springframework.boot:spring-boot-starter-validation")

	runtimeOnly ("org.postgresql:postgresql")

	// JWT
	implementation ("io.jsonwebtoken:jjwt-api:0.11.5")
	runtimeOnly ("io.jsonwebtoken:jjwt-impl:0.11.5")
	runtimeOnly ("io.jsonwebtoken:jjwt-jackson:0.11.5")

	// Swagger

		implementation ("org.springdoc:springdoc-openapi-starter-webmvc-ui:2.5.0")


	compileOnly ("org.projectlombok:lombok")
	annotationProcessor ("org.projectlombok:lombok")

	testImplementation ("org.springframework.boot:spring-boot-starter-test")
}

tasks.withType<Test>
{ useJUnitPlatform() }