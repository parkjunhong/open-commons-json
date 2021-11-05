/*

 * Copyright 2011 Park Jun-Hong (parkjunhong77@gmail.com)
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
 * Date  : 2014. 3. 12. 오후 2:46:32
 *
 * Author: Park_Jun_Hong_(parkjunhong77@gmail.com)
 * 
 */

package open.commons.json;

import open.commons.io.IMarshaller;

public interface IFieldMarshaller<T> extends IMarshaller<T> {

    /**
     * 
     * @param fieldValue
     *            from {@link IJSONString} model.
     * @return
     */
    public Object marshall(T fieldValue);

    /**
     * @param value
     *            into {@link IJSONString} model.
     * @return
     */
    public T unmarshall(Object value);
}
