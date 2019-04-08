/*
 * Copyright 2011 Park Jun-Hong (parkjunhong77/gmail/com)
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
 * Date  : 2014. 3. 14. 오전 11:57:52
 *
 * Author: Park_Jun_Hong_(fafanmama_at_naver_com)
 * 
 */

package open.commons.json;

/**
 * @author Park_Jun_Hong_(fafanmama_at_naver_com)
 * 
 */
public class DefaultFieldMarshaller implements IFieldMarshaller<Object> {

    /**
     * 
     * @see open.commons.json.IFieldMarshaller#marshall(java.lang.Object)
     * 
     */
    @Override
    public Object marshall(Object fieldValue) {
        return fieldValue.toString();
    }

    /**
     * @see #assertValue(Object, Class)
     * @see open.commons.json.IFieldMarshaller#unmarshall(java.lang.Object)
     */
    @Override
    public Object unmarshall(Object value) {
        return value;
    }
}