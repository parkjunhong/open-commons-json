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
 * Date  : 2014. 6. 4. 오전 11:55:36
 *
 * Author: Park_Jun_Hong_(fafanmama_at_naver_com)
 * 
 */

package open.commons.json;

import open.commons.io.IMarshaller;

/**
 * 
 * @since 2014. 6. 4.
 * @author Park_Jun_Hong_(fafanmama_at_naver_com)
 */
public class DefaultMarshaller implements IMarshaller<Object> {

    /**
     * @see open.commons.io.IMarshaller#marshall(java.lang.Object)
     */
    @Override
    public Object marshall(Object value) {
        return value.toString();
    }

    /**
     * @see open.commons.io.IMarshaller#unmarshall(java.lang.Object)
     */
    @Override
    public Object unmarshall(Object value) {
        return value;
    }

}
