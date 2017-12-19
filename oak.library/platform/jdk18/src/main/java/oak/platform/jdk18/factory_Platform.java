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

public class factory_Platform implements factory_IPlatform {

  public IBit Bit_SET() {
    return Bit.SET;
  }

  public IBit Bit_UNSET() {
    return Bit.UNSET;
  }

  public IBitString BitString8(){
    return new BitString8();
  }

  public IBitString BitString16(){
    return new BitString16();
  }

  public IBitString BitString32(){
    return new BitString32();
  }

  public IBitString BitString64(){
    return new BitString64();
  }


  public IBitString BitString(IBitString length){
    return new BitString();
  }


  public IArray Array(IBitString length){
    return new Array(length);
  }


}