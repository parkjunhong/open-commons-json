/*

 * Copyright 2011 Park Jun-Hong_(fafanmama_at_naver_com)
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *     http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

/*


 * 
 * 
 * This file is generated under this project, "open-commons-json".
 *
 * Date  : 2012. 11. 22. 오전 10:11:06
 *
 * Author: Park_Jun_Hong_(fafanmama_at_naver_com)
 *
 */
package open.commons.json;

import java.io.Serializable;

public interface IJSONSingle<T extends Serializable> extends IJSONString {

    public String getName();

    public T getValue();

    public void setValue(T value);
    
    public T get(String name);
}
