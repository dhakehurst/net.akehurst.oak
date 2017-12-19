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

import java.io.File;

import org.gradle.api.internal.project.taskfactory.ITaskFactory;
import org.gradle.internal.service.ServiceRegistry;
import org.gradle.model.ModelMap;
import org.gradle.model.Path;
import org.gradle.model.RuleSource;
import org.gradle.nativeplatform.test.internal.NativeTestSuites;
import org.gradle.platform.base.ComponentBinaries;
import org.gradle.platform.base.ComponentType;
import org.gradle.platform.base.TypeBuilder;

public class CppRules extends RuleSource {
    @ComponentType
    void registerTestSuiteSpecType(final TypeBuilder<CppTestSuiteSpec> builder) {
        builder.defaultImplementation(DefaultCppTestSuiteSpec.class);
    }

    @ComponentType
    void registerTestSuiteBinarySpecType(final TypeBuilder<CppTestSuitBinarySpec> builder) {
        builder.defaultImplementation(DefaultCppTestSuitBinarySpec.class);
    }

    @ComponentBinaries
    public void createTestBinaries(final ModelMap<CppTestSuitBinarySpec> binaries, final CppTestSuiteSpec testSuite, @Path("buildDir") final File buildDir,
            final ServiceRegistry serviceRegistry, final ITaskFactory taskFactory) {
        NativeTestSuites.createNativeTestSuiteBinaries(binaries, testSuite, CppTestSuitBinarySpec.class, "CppTestExe", buildDir, serviceRegistry);
    }

}
