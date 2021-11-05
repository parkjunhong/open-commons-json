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
 * Date  : 2014. 6. 17. 오후 5:02:17
 *
 * Author: Park_Jun_Hong_(parkjunhong77@gmail.com)
 * 
 */

package open.commons.jsonx.supports;

import org.codehaus.jettison.json.JSONObject;

import open.commons.json.model.JSONMapper;
import open.commons.json.model.exception.ImplClassNotFoundException;
import open.commons.json.model.exception.JSONMarshalledException;
import open.commons.json.model.exception.JSONUnmarshalledException;
import open.commons.jsonx.AbstractValueCreator;
import open.commons.jsonx.IDynamicNamedJSONString;
import open.commons.utils.AssertUtils;

/**
 * 
 * @since 2014. 6. 17.
 * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
 */
public class DnJSONObjCreator<T> extends AbstractValueCreator<IDynamicNamedJSONString<T>> {

    /**
     * @param typeClass
     * @since 2014. 6. 17.
     */
    public DnJSONObjCreator(Class<IDynamicNamedJSONString<T>> typeClass) {
        super(typeClass);
    }

    /**
     * @see open.commons.jsonx.AbstractValueCreator#marshall(java.lang.Object)
     */
    @Override
    public Object marshall(IDynamicNamedJSONString<T> value) {
        JSONObject jsonValue = null;

        try {
            if (config != null) {
                jsonValue = value.toJSONObject(config);
            } else {
                jsonValue = value.toJSONObject();
            }
        } catch (Exception e) {
            throw new JSONMarshalledException(e);
        }

        return jsonValue;
    }

    /**
     * @see open.commons.jsonx.AbstractValueCreator#unmarshall(java.lang.Object)
     */
    @Override
    public IDynamicNamedJSONString<T> unmarshall(Object value) {
        IDynamicNamedJSONString jsonImpl = null;

        try {
            Class<?> clazz = JSONMapper.getImplClass((Class<?>) typeClass);

            AssertUtils.assertNull(typeClass + " is not defined.", clazz, ImplClassNotFoundException.class);

            jsonImpl = (IDynamicNamedJSONString) clazz.newInstance();

            if (config != null) {
                jsonImpl.mature((JSONObject) value, config);
            } else {
                jsonImpl.mature((JSONObject) value);
            }
        } catch (Exception e) {
            throw new JSONUnmarshalledException(e);
        }

        return jsonImpl;
    }
}
