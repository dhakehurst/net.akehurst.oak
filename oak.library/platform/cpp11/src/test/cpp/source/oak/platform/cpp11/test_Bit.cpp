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
 
#include <iostream>
#include <cstddef>

#include "oak/platform/api/IBit.hpp"
#include "oak/platform/api/factory_IPlatform.hpp"
#include "oak/platform/cpp11/factory_Platform.hpp"

int main(int argc, char* argv[]) {

	//should be injected!
	factory_IPlatform* platform = new factory_Platform();

    IBit* sut = platform->Bit_SET();

	std::cout << "End of Cpp Test" << std::endl;

    return 0;
}