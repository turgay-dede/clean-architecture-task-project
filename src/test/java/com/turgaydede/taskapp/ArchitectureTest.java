package com.turgaydede.taskapp;

import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.lang.ArchRule;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noClasses;

@AnalyzeClasses(packages = "com.turgaydede.taskapp")
public class ArchitectureTest {

    @ArchTest
    static final ArchRule controllers_should_not_access_repositories_directly =
            noClasses()
                    .that().resideInAPackage("..adapter.in.web..")
                    .should().dependOnClassesThat()
                    .resideInAnyPackage("..adapter.out.persistence..")
                    .as("Controllers should not access repositories directly, only through use cases");

    @ArchTest
    static final ArchRule domain_should_not_access_application_or_adapter =
            noClasses()
                    .that().resideInAPackage("..domain..")
                    .should().dependOnClassesThat()
                    .resideInAnyPackage("..application..", "..adapter..");

}
