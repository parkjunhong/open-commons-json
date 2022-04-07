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
 * COPYLEFT by 'Open Commons' &  Park Jun-Hong All Rights Reserved when use for commercial purpose.
 *
 * This file is generated under this project, "open-commons-json".
 *
 * Date  : 2014. 6. 17. 오후 4:04:39
 *
 * Author: Park_Jun_Hong_(parkjunhong77@gmail.com)
 * 
 */

package open.commons.jsonx.supports;

import open.commons.core.utils.ConvertUtils;
import open.commons.jsonx.AbstractValueCreator;

/**
 * 
 * @since 2014. 6. 17.
 * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
 */
public class PrimitiveCreator extends AbstractValueCreator<Object> {

    /**
     * 
     * @since 2014. 6. 17.
     */
    @SuppressWarnings("unchecked")
    public PrimitiveCreator(Class<?> typeClass) {
        super((Class<Object>) typeClass);
    }

    /**
     * @see open.commons.io.IMarshaller#marshall(java.lang.Object)
     */
    @Override
    public String marshall(Object value) {
        Object jsonValue = ConvertUtils.marshall(value, marshaller);
        return jsonValue != null ? ConvertUtils.toPrimitiveTypeValue(typeClass, jsonValue.toString()).toString() : null;
    }

    /**
     * @see open.commons.io.IMarshaller#unmarshall(java.lang.Object)
     */
    @Override
    public Object unmarshall(Object value) {
        return ConvertUtils.unmarshall(ConvertUtils.toPrimitiveTypeValue(typeClass, value.toString()), marshaller);
    }

}
