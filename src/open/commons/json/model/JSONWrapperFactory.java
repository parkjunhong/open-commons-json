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
 * COPYLEFT by 'Open Commons' &  Park Jun-Hong All Rights Reserved.
 * 
 * This file is generated under this project, "open-commons-json".
 *
 * Date  : 2012. 11. 22. 오전 10:06:51
 *
 * Author: Park_Jun_Hong_(fafanmama_at_naver_com)
 *
 */
package open.commons.json.model;

import java.lang.reflect.Constructor;

import open.commons.json.IJSONSingle;
import open.commons.json.IJSONString;
import open.commons.json.annotation.JSONField;

public class JSONWrapperFactory {

    public static IJSONString createJSONBoolean(String name) {
        return new JSONSingle<Boolean>(name) {
            private static final long serialVersionUID = 6424266958074103932L;

            @JSONField
            private String name;

            private boolean value;

            @Override
            public String getName() {
                return name;
            }

            @Override
            public Boolean getValue() {
                return value;
            }

            @Override
            protected void setName(String name) {
                this.name = name;
            }

            @Override
            public void setValue(Boolean value) {
                this.value = value;
            }
        };
    }

    public static <T extends IJSONString> T createJSONWrapper(Class<T> clazz, Object[] params) {

        T instance = null;

        try {
            Class<?>[] paramsType = null;
            if (params != null && params.length > 0) {
                paramsType = new Class<?>[params.length];

                for (int i = 0; i < params.length; i++) {
                    paramsType[i] = params[i].getClass();
                }
            }

            Constructor<T> cons = null;

            if (IJSONSingle.class.isAssignableFrom(clazz)) {

            }

            cons = clazz.getConstructor(paramsType);

            instance = cons.newInstance(params);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return instance;
    }
}
