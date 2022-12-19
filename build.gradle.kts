plugins {
	java
	war
	id("org.springframework.boot") version "3.0.0"
	id("io.spring.dependency-management") version "1.1.0"
}

group = "com.example"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_17

repositories {
	mavenCentral()
}

dependencies {
	implementation("org.springframework.boot:spring-boot-starter-jdbc")
	implementation("org.springframework.boot:spring-boot-starter-web")
	implementation("org.springframework.boot:spring-boot-starter-data-jpa")
	runtimeOnly("org.postgresql:postgresql")
	implementation("org.hibernate.validator:hibernate-validator:8.0.0.Final")
	testImplementation("org.springframework.boot:spring-boot-starter-test")
}

tasks.war {
	archiveFileName.set("CheckRunner.war")
}

tasks.withType<Test> {
	useJUnitPlatform()
}
