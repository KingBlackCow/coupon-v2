# coupon-v2

---
### ref 멀티모듈 만드는법

### 1. 프로젝트 생성 후 src 폴더 제거

### 2. 모듈 생성 후 자바, 코틀린 파일 
![img_1.png](img_1.png)

## 3. 최상단 settings.gradle 파일 수정
```
rootProject.name = "최상단 프로젝트명"
include("모듈1", "모듈2", "모듈3")`
```

## 4. 최상위 모듈의 build.gradle 파일 수정
```
val bootJar: org.springframework.boot.gradle.tasks.bundling.BootJar by tasks

bootJar.enabled = false

plugins {
    java
    id("org.springframework.boot") version "3.1.5"
    id("io.spring.dependency-management") version "1.1.3"
}

group = "com.example"
version = "0.0.1-SNAPSHOT"

java {
    sourceCompatibility = JavaVersion.VERSION_17
}

configurations {
    compileOnly {
        extendsFrom(configurations.annotationProcessor.get())
    }
}

repositories {
    mavenCentral()
}


subprojects {
    apply(plugin = "java")
    apply(plugin = "io.spring.dependency-management")
    apply(plugin = "org.springframework.boot")

    repositories {
        mavenCentral()
    }

    dependencies {
        
    }
}

tasks.withType<Test> {
    useJUnitPlatform()
}
```

## 5. 각 모듈의 build.gradle 파일 수정

```
val bootJar: org.springframework.boot.gradle.tasks.bundling.BootJar by tasks

bootJar.enabled = false

repositories {
    mavenCentral()
}

dependencies {
    
}

tasks.withType<Test> {
    useJUnitPlatform()
}
```


