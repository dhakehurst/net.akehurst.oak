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

package oak.types.impl;

import org.junit.Assert;
import org.junit.Test;

import oak.platform.api.factory_IPlatform;
import oak.platform.jdk_1_8.factory_Platform;
import oak.types.api.IBoolean;
import oak.types.api.factory_ITypes;

public class test_Boolean {

	@Test
	public void test() {

		final factory_IPlatform oak = new factory_Platform();
		final factory_ITypes types = new oak.types.impl.factory_Types();

		final IBoolean sut = types.Boolean(oak.Bit_SET());

		Assert.assertTrue(sut.asPrimitive() == oak.Bit_SET());

	}

}