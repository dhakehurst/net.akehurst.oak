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

package net.akehurst.oak.gradle.cpp.test;

import java.util.HashMap;
import java.util.Map;

import org.gradle.language.cpp.CppSourceSet;
import org.gradle.model.Each;
import org.gradle.model.Mutate;
import org.gradle.model.RuleSource;
import org.gradle.nativeplatform.NativeLibrarySpec;
import org.gradle.testing.base.TestSuiteContainer;

public class CppComponentRules extends RuleSource {

    @Mutate
    public void customSourceAndHeaderDirectories(@Each final CppSourceSet ss) {

        ss.getExportedHeaders().srcDir("src/main/cpp/header");
        ss.getExportedHeaders().include("**/*.hpp");

        ss.getSource().srcDir("src/main/cpp/source");
        ss.getSource().include("**/*.cpp");
    }

    // by convention always have a Test component called ${name}/Test
    public static void createCppTestSuitePerComponent(final TestSuiteContainer testSuites, final NativeLibrarySpec component) {
        // for (final NativeLibrarySpec component : components.values()) {
        final String suiteName = component.getName() + "Test";
        testSuites.create(suiteName, CppTestSuiteSpec.class, (testSuite) -> {
            testSuite.testing(component);
            testSuite.getSources().afterEach(CppSourceSet.class, (ss) -> {

                ss.getExportedHeaders().srcDir("src/test/cpp/header");
                ss.getExportedHeaders().include("**/*.hpp");

                ss.getSource().srcDir("src/test/cpp/source");
                ss.getSource().include("**/*.cpp");

                final Map<String, String> lib = new HashMap<>();
                lib.put("library", component.getName());
                ss.lib(lib);

                component.getSources().forEach(libSS -> {
                    if (libSS instanceof CppSourceSet) {
                        for (final Object cmpLib : ((CppSourceSet) libSS).getLibs()) {
                            ss.lib(cmpLib);
                        }
                    }
                });
            });
        });
        // }
    }

}
