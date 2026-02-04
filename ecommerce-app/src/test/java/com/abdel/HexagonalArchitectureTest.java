package com.abdel;


import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.lang.ArchRule;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.classes;
import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noClasses;

@AnalyzeClasses(packages = "com.abdel")
class HexagonalArchitectureTest {
    @ArchTest
    static final ArchRule business_should_not_depend_on_infra_or_api =
            noClasses()
                    .that().resideInAPackage("..business..")
                    .should().dependOnClassesThat()
                    .resideInAnyPackage("..infrastructure..", "..api..");
    @ArchTest
    static final ArchRule no_spring_in_business =
            noClasses()
                    .that().resideInAPackage("..business..")
                    .should().dependOnClassesThat()
                    .resideInAnyPackage(
                            "org.springframework..",
                            "jakarta.persistence..",
                            "jakarta.transaction.."
                    );

    /* ---------------------------------
       API LAYER
       --------------------------------- */

    @ArchTest
    static final ArchRule api_should_only_depend_on_business =
            classes()
                    .that().resideInAPackage("..api..")
                    .should().onlyDependOnClassesThat()
                    .resideInAnyPackage(
                            "java..",
                            "jakarta..",
                            "org.springframework..",
                            "..business.."
                    );

    /* ---------------------------------
       INFRASTRUCTURE LAYER
       --------------------------------- */

    @ArchTest
    static final ArchRule infra_should_not_depend_on_api =
            noClasses()
                    .that().resideInAPackage("..infrastructure..")
                    .should().dependOnClassesThat()
                    .resideInAPackage("..api..");

    /* ---------------------------------
       USE CASE RULES
       --------------------------------- */

    @ArchTest
    static final ArchRule usecases_should_not_depend_on_infra =
            noClasses()
                    .that().resideInAPackage("..business.usecase..")
                    .should().dependOnClassesThat()
                    .resideInAPackage("..infrastructure..");

}

