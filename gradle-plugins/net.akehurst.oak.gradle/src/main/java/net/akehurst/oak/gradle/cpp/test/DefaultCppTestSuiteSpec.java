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

import org.gradle.nativeplatform.NativeComponentSpec;
import org.gradle.nativeplatform.internal.AbstractNativeComponentSpec;
import org.gradle.platform.base.ComponentSpec;

public class DefaultCppTestSuiteSpec extends AbstractNativeComponentSpec implements CppTestSuiteSpec {
    private NativeComponentSpec testedComponent;

    @Override
    protected String getTypeName() {
        return "Cpp test suite";
    }

    @Override
    public NativeComponentSpec getTestedComponent() {
        return this.testedComponent;
    }

    @Override
    public void setTestedComponent(final ComponentSpec testedComponent) {
        this.testedComponent = (NativeComponentSpec) testedComponent;
    }

    @Override
    public void testing(final ComponentSpec testedComponent) {
        this.testedComponent = (NativeComponentSpec) testedComponent;
    }

    // @Override
    // public ModelMap<CppTestSourceSet> getSources() {
    // return ModelMaps.toView(sources, ModelType.of(LanguageSourceSet.class));
    // }
}