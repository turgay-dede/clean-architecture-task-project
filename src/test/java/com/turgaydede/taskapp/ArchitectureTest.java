package com.turgaydede.taskapp;

import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.lang.ArchRule;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.classes;
import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noClasses;

@AnalyzeClasses(packages = "com.turgaydede.taskapp")
public class ArchitectureTest {

    // ✅ DOMAIN → sadece kendine ve JDK’ya bağımlı olabilir
    @ArchTest
    static final ArchRule domain_should_be_isolated =
            classes().that().resideInAPackage("..domain..")
                    .should().onlyDependOnClassesThat()
                    .resideInAnyPackage("..domain..", "java..")
                    .as("Domain should not depend on application, adapter, config or spring");

    // ❌ DOMAIN → application, adapter veya config’e bağlı olamaz
    @ArchTest
    static final ArchRule domain_should_not_access_upper_layers =
            noClasses().that().resideInAPackage("..domain..")
                    .should().dependOnClassesThat()
                    .resideInAnyPackage("..application..", "..adapter..", "..config..")
                    .as("Domain must not depend on application, adapter, or config layers");

    // ❌ APPLICATION → adapter veya config katmanına bağlı olamaz
    @ArchTest
    static final ArchRule application_should_not_access_adapter_or_config =
            noClasses().that().resideInAPackage("..application..")
                    .should().dependOnClassesThat()
                    .resideInAnyPackage("..adapter..", "..config..")
                    .as("Application must not depend on adapter or config layers");

    // ✅ APPLICATION SERVICE → sadece domain, port ve java’ya bağımlı olabilir
    @ArchTest
    static final ArchRule application_service_should_be_clean =
            classes().that().resideInAPackage("..application.service..")
                    .should().onlyDependOnClassesThat()
                    .resideInAnyPackage(
                            "..application.service..",
                            "..application.port.in..",
                            "..application.port.out..",
                            "..domain..",
                            "java.."
                    )
                    .as("Application services must depend only on domain, ports, and java");

    // ❌ ADAPTER → application.service'e bağlı olamaz (sadece portlara bağlı olabilir)
    @ArchTest
    static final ArchRule adapter_should_not_access_services =
            noClasses().that().resideInAPackage("..adapter..")
                    .should().dependOnClassesThat()
                    .resideInAnyPackage("..application.service..")
                    .as("Adapters must depend only on ports, not service implementations");

    // ✅ CONTROLLERS → sadece port.in, DTO, java ve spring’e bağlı olabilir
    @ArchTest
    static final ArchRule controllers_should_only_depend_on_ports =
            classes()
                    .that().haveSimpleNameEndingWith("Controller")
                    .and().resideInAPackage("..adapter.in.web..")
                    .should().onlyDependOnClassesThat()
                    .resideInAnyPackage(
                            "java..",
                            "org.springframework..",
                            "..adapter.in.web..",          // DTO’lar burada
                            "..application.port.in.."      // UseCase arayüzleri burada
                    )
                    .as("Controllers should depend only on ports, spring, and their own DTOs");

    // ❌ CONTROLLER → doğrudan repository (persistence) sınıflarına erişemez
    @ArchTest
    static final ArchRule controllers_should_not_access_repositories_directly =
            noClasses()
                    .that().haveSimpleNameEndingWith("Controller")
                    .and().resideInAPackage("..adapter.in.web..")
                    .should().dependOnClassesThat()
                    .resideInAnyPackage("..adapter.out.persistence..")
                    .as("Controllers must not access repositories directly");

}
