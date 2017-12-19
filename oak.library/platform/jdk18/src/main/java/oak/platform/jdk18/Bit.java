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
package oak.platform.jdk18;

import oak.platform.api.*;

public class Bit implements IBit {

  public static IBit SET = new Bit(true);
  public static IBit UNSET = new Bit(false);

  private final boolean value;

  private Bit(boolean value) {
    this.value = value;
  }

  public IBit getIsSet() {
    if (value) {
      return Bit.SET;
    } else {
      return Bit.UNSET;
    }
  }

  public IBit getIsUnset() {
    if (value) {
      return Bit.UNSET;
    } else {
      return Bit.SET;
    }
  }

  public IBit getInverted() {
    return this.getIsUnset();
  }

}