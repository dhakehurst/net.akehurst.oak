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

package net.akehurst.oak.gradle;

import java.io.File;
import java.util.Objects;

import javax.inject.Inject;

import org.gradle.api.Plugin;
import org.gradle.api.Project;
import org.gradle.api.internal.artifacts.ArtifactPublicationServices;
import org.gradle.api.internal.project.ProjectIdentifier;
import org.gradle.api.publish.plugins.PublishingPlugin;
import org.gradle.internal.reflect.Instantiator;
import org.gradle.jvm.JvmLibrarySpec;
import org.gradle.jvm.plugins.JUnitTestSuitePlugin;
import org.gradle.jvm.plugins.JvmComponentPlugin;
import org.gradle.language.c.CSourceSet;
import org.gradle.language.c.plugins.CLangPlugin;
import org.gradle.language.cpp.CppSourceSet;
import org.gradle.language.cpp.plugins.CppLangPlugin;
import org.gradle.language.java.JavaSourceSet;
import org.gradle.language.java.plugins.JavaLanguagePlugin;
import org.gradle.model.Defaults;
import org.gradle.model.Each;
import org.gradle.model.ModelMap;
import org.gradle.model.RuleSource;
import org.gradle.nativeplatform.NativeLibrarySpec;
import org.gradle.nativeplatform.test.plugins.NativeBinariesTestPlugin;
import org.gradle.testing.base.TestSuiteContainer;

import groovy.util.logging.Slf4j;
import net.akehurst.oak.gradle.c.test.CComponentRules;
import net.akehurst.oak.gradle.c.test.CRules;
import net.akehurst.oak.gradle.cpp.test.CppComponentRules;
import net.akehurst.oak.gradle.cpp.test.CppRules;
import net.akehurst.oak.gradle.java.test.JavaComponentRules;

@Slf4j
class OakPlugin extends PublishingPlugin implements Plugin<Project> {

    static final String CONFIGURATION_COMPILE_NAME = "compile";

    @Inject
    public OakPlugin(final ArtifactPublicationServices publicationServices, final Instantiator instantiator) {
        super(publicationServices, instantiator);
    }

    @Override
    public void apply(final Project project) {
        super.apply(project);

        project.getPluginManager().apply(NativeBinariesTestPlugin.class);
        project.getPluginManager().apply(CLangPlugin.class);
        project.getPluginManager().apply(CppLangPlugin.class);
        project.getPluginManager().apply(JvmComponentPlugin.class);
        project.getPluginManager().apply(JavaLanguagePlugin.class);

        project.getPluginManager().apply(CRules.class);
        project.getPluginManager().apply(CppRules.class);
        project.getPluginManager().apply(JUnitTestSuitePlugin.class);

        /*
         * System.setProperty("org.gradle.color.normal", "black") System.setProperty("org.gradle.color.header", "red")
         * System.setProperty("org.gradle.color.identifier", "yellow") System.setProperty("org.gradle.color.description", "blue")
         * System.setProperty("org.gradle.color.progressstatus", "magenta") System.setProperty("org.gradle.color.userinput", "green")
         */
        System.setProperty("org.gradle.color.success", "green");
        System.setProperty("org.gradle.color.info", "blue");
        System.setProperty("org.gradle.color.failure", "red");
        System.setProperty("org.gradle.color.error", "red");

        project.getExtensions().create("oak", OakConfig.class, project);

        if (null == project.getConfigurations().findByName(OakPlugin.CONFIGURATION_COMPILE_NAME)) {
            project.getConfigurations().create(OakPlugin.CONFIGURATION_COMPILE_NAME);
        }

    }

    static class Rules extends RuleSource {

        @Defaults
        public void applyJvmDefaults(@Each final JvmLibrarySpec component, final ProjectIdentifier pi) {
            // component.setBaseName(pi.getName());

            if (Objects.equals("java", component.getName())) {
                component.getSources().afterEach(JavaSourceSet.class, (ss) -> {
                    ss.getSource().srcDir("src/main/java");
                    ss.getSource().include("**/*.java");
                });
            }
        }

        // by convention always have a Test component called ${name}/Test
        @Defaults
        public void createTestSuitePerJvmComponent(final TestSuiteContainer testSuites, final ModelMap<JvmLibrarySpec> components, final ProjectIdentifier pi) {
            for (final JvmLibrarySpec component : components.values()) {
                if (Objects.equals("java", component.getName())) {
                    final File testDir = new File(pi.getProjectDir(), "src/test/java");
                    if (testDir.exists()) {
                        JavaComponentRules.createJavaTestSuitePerComponent(testSuites, component);
                    }
                }
            }
        }

        @Defaults
        public void applyNativeDefaults(@Each final NativeLibrarySpec component, final ProjectIdentifier pi) {
            component.setBaseName(pi.getName());

            if (Objects.equals("cpp", component.getName())) {
                component.getSources().afterEach(CppSourceSet.class, (ss) -> {
                    ss.getExportedHeaders().srcDir("src/main/cpp/header");
                    ss.getExportedHeaders().include("**/*.hpp");

                    ss.getSource().srcDir("src/main/cpp/source");
                    ss.getSource().include("**/*.cpp");
                });
            } else if (Objects.equals("c", component.getName())) {
                component.getSources().afterEach(CSourceSet.class, (ss) -> {
                    ss.getExportedHeaders().srcDir("src/main/c/header");
                    ss.getExportedHeaders().include("**/*.h");

                    ss.getSource().srcDir("src/main/c/source");
                    ss.getSource().include("**/*.c");
                });
            }
        }

        // by convention always have a Test component called ${name}/Test
        @Defaults
        public void createTestSuitePerNativeComponent(final TestSuiteContainer testSuites, final ModelMap<NativeLibrarySpec> components,
                final ProjectIdentifier pi) {
            for (final NativeLibrarySpec component : components.values()) {
                if (Objects.equals("cpp", component.getName())) {
                    final File testDir = new File(pi.getProjectDir(), "src/test/cpp");
                    if (testDir.exists()) {
                        CppComponentRules.createCppTestSuitePerComponent(testSuites, component);
                    }
                } else if (Objects.equals("c", component.getName())) {
                    final File testDir = new File(pi.getProjectDir(), "src/test/c");
                    if (testDir.exists()) {
                        CComponentRules.createCTestSuitePerComponent(testSuites, component);
                    }
                }
            }
        }
    }

}