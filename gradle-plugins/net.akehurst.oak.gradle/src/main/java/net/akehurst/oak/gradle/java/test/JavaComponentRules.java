/**
 * Copyright (C) 2018 Dr. David H. Akehurst (http://dr.david.h.akehurst.net)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package net.akehurst.oak.gradle.java.test;

import org.gradle.jvm.JvmLibrarySpec;
import org.gradle.jvm.test.JUnitTestSuiteSpec;
import org.gradle.language.cpp.CppSourceSet;
import org.gradle.language.java.JavaSourceSet;
import org.gradle.model.Each;
import org.gradle.model.Mutate;
import org.gradle.model.RuleSource;
import org.gradle.platform.base.DependencySpec;
import org.gradle.platform.base.LibraryBinaryDependencySpec;
import org.gradle.platform.base.ModuleDependencySpec;
import org.gradle.platform.base.ProjectDependencySpec;
import org.gradle.testing.base.TestSuiteContainer;

public class JavaComponentRules extends RuleSource {

    @Mutate
    public void customSourceAndHeaderDirectories(@Each final CppSourceSet ss) {

        ss.getExportedHeaders().srcDir("src/main/cpp/header");
        ss.getExportedHeaders().include("**/*.hpp");

        ss.getSource().srcDir("src/main/cpp/source");
        ss.getSource().include("**/*.cpp");
    }

    // by convention always have a Test component called ${name}/Test
    public static void createJavaTestSuitePerComponent(final TestSuiteContainer testSuites, final JvmLibrarySpec component) {
        // for (final NativeLibrarySpec component : components.values()) {
        final String suiteName = component.getName() + "Test";
        testSuites.create(suiteName, JUnitTestSuiteSpec.class, (testSuite) -> {
            testSuite.testing(component);
            testSuite.setjUnitVersion("4.12");
            testSuite.getSources().afterEach(JavaSourceSet.class, (ss) -> {

                ss.getSource().srcDir("src/test/java");
                ss.getSource().include("**/*.java");

                ss.getDependencies().library("java");

                component.getSources().forEach(libSS -> {
                    if (libSS instanceof JavaSourceSet) {
                        for (final DependencySpec dep : ((JavaSourceSet) libSS).getDependencies().getDependencies()) {
                            if (dep instanceof LibraryBinaryDependencySpec) {
                                final LibraryBinaryDependencySpec depLib = (LibraryBinaryDependencySpec) dep;
                                ss.getDependencies().library(depLib.getLibraryName()).build();
                            } else if (dep instanceof ProjectDependencySpec) {
                                final ProjectDependencySpec depProj = (ProjectDependencySpec) dep;
                                ss.getDependencies().project(depProj.getProjectPath()).library("java").build();
                            } else if (dep instanceof ModuleDependencySpec) {
                                final ModuleDependencySpec depMod = (ModuleDependencySpec) dep;
                                ss.getDependencies().group(depMod.getGroup()).module(depMod.getName()).version(depMod.getVersion()).build();
                            }
                        }
                    }
                });
            });
        });
        // }
    }

}
