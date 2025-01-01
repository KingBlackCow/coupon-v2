plugins {
    kotlin("jvm") version "1.9.10" // Kotlin JVM 플러그인 추가
    kotlin("plugin.spring") version "1.9.10" // Kotlin Spring 플러그인 추가
    id("org.springframework.boot") version "3.1.5"
    id("io.spring.dependency-management") version "1.1.3"
}

group = "com.example"
version = "0.0.1-SNAPSHOT"

dependencies {
    // 프로젝트 내부 모듈 의존성
    implementation(project(":coupon-core"))

    // Spring Boot Starter Web (웹 애플리케이션)
    implementation("org.springframework.boot:spring-boot-starter-web")

    // Spring Boot 기본 스타터
    implementation("org.springframework.boot:spring-boot-starter")

    // Spring Boot Starter Test (테스트 종속성)
    testImplementation("org.springframework.boot:spring-boot-starter-test")
}

tasks.withType<Test> {
    // JUnit Platform 사용 설정
    useJUnitPlatform()
}