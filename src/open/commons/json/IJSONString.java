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
 * Date  : 2012. 11. 17. 오전 5:07:00
 *
 * Author: Park_Jun_Hong_(fafanmama_at_naver_com)
 *
 */
package open.commons.json;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.List;

import open.commons.json.annotation.JSONField;
import open.commons.json.model.BuildConfig;
import open.commons.reflect.GenericTypeVariable;
import open.commons.utils.ConvertUtils;

import code.org.codehaus.jettison.json.JSONException;
import code.org.codehaus.jettison.json.JSONObject;

/**
 * JSON 문자열과 상호연동을 지원하는 인터페이스.
 * 
 * @author Park_Jun_Hong_(fafanmama_at_naver_com)
 * 
 */
public interface IJSONString extends Serializable, Cloneable {

    /**
     * {@link JSONField}가 추가된 모든 {@link Field}의 값을 초기화 시킨다.
     * 
     * @see ConvertUtils#resetField(Object, Field)
     */
    public void clear();

    // public BuildConfig getBuildConfig();

    /**
     * Not yet supported.
     * 
     * @param jsonModel
     * 
     * @since 2014. 5. 30.
     */
    public void inject(IJSONString jsonModel);

    public IJSONString mature(JSONObject json) throws JSONException;

    public IJSONString mature(JSONObject json, BuildConfig buildConfig) throws JSONException;

    public IJSONString mature(JSONObject json, Class<?> paramType) throws JSONException;

    public IJSONString mature(JSONObject json, GenericTypeVariable... paramTypes) throws JSONException;

    public IJSONString mature(JSONObject json, List<Class<?>> paramTypes) throws JSONException;

    public IJSONString mature(String jsonString) throws JSONException;

    public IJSONString mature(String jsonString, BuildConfig buildConfig) throws JSONException;

    public IJSONString mature(String jsonString, Class<?> paramType) throws JSONException;

    public IJSONString mature(String jsonString, GenericTypeVariable... paramTypes) throws JSONException;

    public IJSONString mature(String jsonString, List<Class<?>> paramTypes) throws JSONException;

    public JSONObject toJSONObject() throws JSONException;

    /**
     * 
     * @param ordered
     * @return
     * @throws JSONException
     *
     * @since 2016. 5. 26
     * @since 1.6.0
     */
    public JSONObject toJSONObject(boolean ordered) throws JSONException;

    /**
     * 
     * @param ordered
     * @param paramTypes
     * @return
     * @throws JSONException
     *
     * @since 2016. 5. 26
     * @since 1.6.0
     */
    public JSONObject toJSONObject(boolean ordered, GenericTypeVariable... paramTypes) throws JSONException;

    public JSONObject toJSONObject(BuildConfig buildConfig) throws JSONException;

    /**
     * 
     * @param buildConfig
     * @param ordered
     * @return
     * @throws JSONException
     *
     * @since 2016. 5. 26
     * @since 1.6.0
     */
    public JSONObject toJSONObject(BuildConfig buildConfig, boolean ordered) throws JSONException;

    public JSONObject toJSONObject(Class<?> paramType) throws JSONException;

    /**
     * 
     * @param paramType
     * @param ordered
     * @return
     * @throws JSONException
     *
     * @since 2016. 5. 26
     * @since 1.6.0
     */
    public JSONObject toJSONObject(Class<?> paramType, boolean ordered) throws JSONException;

    public JSONObject toJSONObject(GenericTypeVariable... paramTypes) throws JSONException;

    public JSONObject toJSONObject(List<Class<?>> paramTypes) throws JSONException;

    /**
     * 
     * @param paramTypes
     * @param ordered
     * @return
     * @throws JSONException
     *
     * @since 2016. 5. 26
     * @since 1.6.0
     */
    public JSONObject toJSONObject(List<Class<?>> paramTypes, boolean ordered) throws JSONException;

    public String toJSONString() throws JSONException;

    /**
     * 
     * @param ordered
     * @return
     * @throws JSONException
     *
     * @since 2016. 5. 26.
     * @since 1.6.0
     */
    public String toJSONString(boolean ordered) throws JSONException;

    /**
     * 
     * @param ordered
     * @param paramTypes
     * @return
     * @throws JSONException
     *
     * @since 2016. 5. 26.
     */
    public String toJSONString(boolean ordered, GenericTypeVariable... paramTypes) throws JSONException;

    public String toJSONString(BuildConfig buildConfig) throws JSONException;

    /**
     * 
     * @param buildConfig
     * @param ordered
     * @return
     * @throws JSONException
     *
     * @since 2016. 5. 26.
     * @since 1.6.0
     */
    public String toJSONString(BuildConfig buildConfig, boolean ordered) throws JSONException;

    public String toJSONString(BuildConfig buildConfig, int factor) throws JSONException;

    /**
     * 
     * @param buildConfig
     * @param factor
     * @param ordered
     * @return
     * @throws JSONException
     *
     * @since 2016. 5. 26.
     * @since 1.6.0
     */
    public String toJSONString(BuildConfig buildConfig, int factor, boolean ordered) throws JSONException;

    public String toJSONString(Class<?> paramType) throws JSONException;

    /**
     * 
     * @param paramType
     * @param ordered
     * @return
     * @throws JSONException
     *
     * @since 2016. 5. 26.
     * @since 1.6.0
     */
    public String toJSONString(Class<?> paramType, boolean ordered) throws JSONException;

    public String toJSONString(GenericTypeVariable... paramTypes) throws JSONException;

    public String toJSONString(int factor) throws JSONException;

    /**
     * 
     * @param factor
     * @param ordered
     * @return
     * @throws JSONException
     *
     * @since 2016. 5. 26.
     * @since 1.6.0
     */
    public String toJSONString(int factor, boolean ordered) throws JSONException;

    /**
     * 
     * @param factor
     * @param ordered
     * @param paramTypes
     * @return
     * @throws JSONException
     *
     * @since 2016. 5. 26.
     * @since 1.6.0
     */
    public String toJSONString(int factor, boolean ordered, GenericTypeVariable... paramTypes) throws JSONException;

    public String toJSONString(int factor, Class<?> paramType) throws JSONException;

    /**
     * 
     * @param factor
     * @param paramType
     * @param ordered
     * @return
     * @throws JSONException
     *
     * @since 2016. 5. 26.
     * @since 1.6.0
     */
    public String toJSONString(int factor, Class<?> paramType, boolean ordered) throws JSONException;

    public String toJSONString(int factor, GenericTypeVariable... paramTypes) throws JSONException;

    public String toJSONString(int factor, List<Class<?>> paramTypes) throws JSONException;

    /**
     * 
     * @param factor
     * @param paramTypes
     * @param ordered
     * @return
     * @throws JSONException
     *
     * @since 2016. 5. 26.
     * @since 1.6.0
     */
    public String toJSONString(int factor, List<Class<?>> paramTypes, boolean ordered) throws JSONException;

    public String toJSONString(List<Class<?>> paramTypes) throws JSONException;

    /**
     * 
     * @param paramTypes
     * @param ordered
     * @return
     * @throws JSONException
     *
     * @since 2016. 5. 26.
     * @since 1.6.0
     */
    public String toJSONString(List<Class<?>> paramTypes, boolean ordered) throws JSONException;
}
