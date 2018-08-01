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
 * COPYLEFT by 'Open Commons' &  Park Jun-Hong All Rights Reserved when use for commercial purpose.
 *
 * This file is generated under this project, "open-commons-json".
 *
 * Date  : 2014. 6. 17. 오후 4:01:41
 *
 * Author: Park_Jun_Hong_(fafanmama_at_naver_com)
 * 
 */

package open.commons.jsonx;

import open.commons.io.IMarshaller;
import open.commons.json.model.BuildConfig;
import open.commons.utils.ConvertUtils;

/**
 * 
 * @param T
 * @since 2014. 6. 17.
 * @author Park_Jun_Hong_(fafanmama_at_naver_com)
 */
public abstract class AbstractValueCreator<T> implements IValueCreator<T> {

    protected Class<T> typeClass;
    protected BuildConfig config;
    protected IMarshaller<T> marshaller;

    protected AbstractValueCreator(Class<T> typeClass) {
        this(typeClass, null);
    }

    protected AbstractValueCreator(Class<T> typeClass, BuildConfig config) {
        this(typeClass, config, null);
    }

    protected AbstractValueCreator(Class<T> typeClass, BuildConfig config, IMarshaller<T> marshaller) {
        this.typeClass = typeClass;
        this.config = config;
        this.marshaller = marshaller;
    }

    /**
     * @see open.commons.io.IMarshaller#marshall(java.lang.Object)
     */
    @Override
    public Object marshall(T value) {
        return ConvertUtils.marshall(value, marshaller);
    }

    /**
     * @see open.commons.jsonx.IValueCreator#setBuildConfig(open.commons.json.model.BuildConfig)
     */
    @Override
    public IValueCreator<T> setBuildConfig(BuildConfig config) {
        this.config = config;
        
        return this;
    }

    /**
     * @see open.commons.jsonx.IValueCreator#setMarshaller(open.commons.io.IMarshaller)
     */
    @Override
    public IValueCreator<T> setMarshaller(IMarshaller<T> marshaller) {
        this.marshaller = marshaller;
        
        return this;
    }

    /**
     * @see open.commons.jsonx.IValueCreator#setTypeClass(Class)
     */
    @Override
    public IValueCreator<T> setTypeClass(Class<T> typeClass) {
        this.typeClass = typeClass;
        
        return this;
    }

    /**
     * @see open.commons.io.IMarshaller#unmarshall(java.lang.Object)
     */
    @Override
    public T unmarshall(Object value) {
        return ConvertUtils.unmarshall(value, marshaller);
    }
}
