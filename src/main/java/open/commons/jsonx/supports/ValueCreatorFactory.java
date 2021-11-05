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
 * Date  : 2014. 6. 17. 오후 4:48:28
 *
 * Author: Park_Jun_Hong_(parkjunhong77@gmail.com)
 * 
 */

package open.commons.jsonx.supports;

import open.commons.json.IJSONString;
import open.commons.json.model.JSONMapper;
import open.commons.jsonx.IValueCreator;

/**
 * 
 * @since 2014. 6. 17.
 * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
 */
public class ValueCreatorFactory {

    /**
     * Prevent to create an instance.
     * 
     * @since 2014. 6. 17.
     */
    private ValueCreatorFactory() {
    }

    /**
     * 
     * @param typeValue
     *            One of followings.<br>
     *            <ul>
     *            <li><b><code>{@link JSONMapper#FIELD_TYPE_PRIMITIVE}</code></b>
     *            <li><b><code>{@link JSONMapper#FIELD_TYPE_STRING}</code></b>
     *            <li><b><code>{@link JSONMapper#FIELD_TYPE_ENUM}</code></b>
     *            <li><b><code>{@link JSONMapper#FIELD_TYPE_DYNAMIC_NAMED_JSON_CLASS}</code></b>
     *            <li><b><code>{@link JSONMapper#FIELD_TYPE_JSON_CLASS}</code></b>
     *            </ul>
     * @return
     * 
     * @since 2014. 6. 17.
     */
    @SuppressWarnings({ "unchecked", "rawtypes" })
    public static <T> IValueCreator<T> create(int typeValue, Class<?> typeClass) {
        IValueCreator<?> valueCreator = null;
        switch (typeValue) {
            case JSONMapper.FIELD_TYPE_PRIMITIVE:
                valueCreator = new PrimitiveCreator(typeClass);
                break;
            case JSONMapper.FIELD_TYPE_STRING:
                valueCreator = new StringCreator();
                break;
            case JSONMapper.FIELD_TYPE_ENUM:
                valueCreator = new EnumCreator(typeClass);
                break;
            case JSONMapper.FIELD_TYPE_DYNAMIC_NAMED_JSON_CLASS:
                valueCreator = new DnJSONObjCreator(typeClass);
                break;
            case JSONMapper.FIELD_TYPE_JSON_CLASS:
                valueCreator = new JSONObjCreator((Class<IJSONString>) typeClass);
                break;
            default:
                break;
        }

        return (IValueCreator<T>) valueCreator;

    }
}
