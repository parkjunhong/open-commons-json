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
 * Date  : 2012. 10. 29. 오전 1:08:28
 *
 * Author: Park_Jun_Hong_(fafanmama_at_naver_com)
 *
 */
package open.commons.json.model;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;

import open.commons.io.IMarshaller;
import open.commons.json.IFieldMarshaller;
import open.commons.json.IJSONString;
import open.commons.json.annotation.JSONField;
import open.commons.json.util.JSONUtil;
import open.commons.jsonx.IDynamicNamedJSONString;
import open.commons.jsonx.model.DynamicNamedJSONModel;
import open.commons.reflect.GenericTypeVariable;
import open.commons.util.IFilter;
import open.commons.utils.AssertUtils;
import open.commons.utils.ConvertUtils;
import open.commons.utils.ReflectionUtils;

import code.org.codehaus.jettison.json.JSONArray;
import code.org.codehaus.jettison.json.JSONException;
import code.org.codehaus.jettison.json.JSONObject;

/**
 * 하위 클래스들의 필드중에서 {@link JSONField}를 사용한 필드들을 검색해 자동으로 {@link #toString()}를 제공해주는 클래스.
 * 
 * @author Park_Jun_Hong_(fafanmama_at_naver_com)
 * 
 */
@SuppressWarnings("serial")
abstract class AbstractJSONModel implements IJSONString, Serializable {

    transient private Map<Field, JSONField> annotated;

    protected AbstractJSONModel() {
        loadAnnotatedFields();
    }

    /**
     * 
     * @param buildConfig
     *
     * @since 2014. 6. 18. - 하위 클래스가 직접 제공하는 GenericTypeVariable 처리
     * @since 2012. 10. 29.
     */
    private void buildConfig(BuildConfig buildConfig) {
        AssertUtils.assertNull(buildConfig);

        buildConfig.build(this);

        // start - 하위 클래스가 직접 제공하는 GenericTypeVariable 처리 : 2014. 6. 18., Park_Jun_Hong_(fafanmama_at_naver_com)
        Map<String, GenericTypeVariable> typeVars = loadDynamicNamedFieldGenericTypeVar();

        if (typeVars != null) {
            for (Entry<String, GenericTypeVariable> entry : typeVars.entrySet()) {
                buildConfig.addFieldGenericTypeVar(entry.getKey(), entry.getValue());
            }
        }
        // end - 하위 클래스가 직접 제공하는 GenericTypeVariable 처리 : 2014. 6. 18.

        JSONMapper.register(this, buildConfig);
    }

    /**
     * @see open.commons.json.IJSONString#clear()
     */
    @Override
    public void clear() {

        boolean oldAccessible = false;

        for (Field field : annotated.keySet()) {
            try {
                oldAccessible = field.isAccessible();
                field.setAccessible(true);

                ReflectionUtils.resetField(this, field);
            } catch (IllegalArgumentException ignored) {
            } catch (IllegalAccessException ignored) {
            } finally {
                field.setAccessible(oldAccessible);
            }
        }
    }

    protected final Map<Field, JSONField> getAnnotatedFields() {
        return annotated;
    }

    /**
     * 변수와 연결된 {@link JSONObject}의 이름을 반환한다.
     * 
     * @param fieldName
     *            클래스 변수명
     * @return
     */
    protected String getJSONKey(String fieldName) {

        String fieldJsonName = null;

        boolean oldAccessible = false;

        Field field = null;

        try {
            field = getClass().getDeclaredField(fieldName);

            oldAccessible = field.isAccessible();

            JSONField anno = field.getAnnotation(JSONField.class);

            if (anno != null) {
                fieldJsonName = anno.name();

                if ("".equals(fieldJsonName)) {
                    fieldJsonName = field.getName();
                }
            } else {
                fieldJsonName = field.getName();
            }

        } catch (NoSuchFieldException ignored) {
        } finally {
            field.setAccessible(oldAccessible);
        }

        return fieldJsonName;
    }

    private Class<?> getSuperClass(Class<?> childClass) {
        Class<?> superClass = childClass.getSuperclass();
        return AbstractJSONModel.class.equals(superClass) || DefaultJSONModel.class.equals(superClass) ? null : superClass;
    }

    // /**
    // * @see open.commons.json.IJSONString#getBuildConfig()
    // */
    // @Override
    // public final BuildConfig getBuildConfig() {
    // // return buildConfig;
    //
    // // TEST-CODE - [Park_Jun_Hong_(fafanmama_at_naver_com)]: 2014. 4. 7.
    // return null;
    // }

    /**
     * 
     * Blocks duplicated entries in hashtable.
     * 
     * @see java.lang.Object#hashCode()
     */
    @Override
    public final int hashCode() {
        return super.hashCode();
    }

    /**
     * @see open.commons.json.IJSONString#inject(IJSONString)
     */
    @Override
    public void inject(IJSONString jsonModel) {
    };

    /**
     * load
     * 
     * 
     * @since 2014. 5. 2.
     */
    protected void loadAnnotatedFields() {

        // #1. get fields of mine.
        annotated = ReflectionUtils.getAnnotatedFields(this, JSONField.class);

        // #2. get fields of super classes.
        Class<?> superClass = getSuperClass(getClass());

        IFilter<JSONField> filter = new IFilter<JSONField>() {
            /**
             * @see open.commons.util.IFilter#filter(java.lang.Object, Object...)
             */
            @Override
            public boolean filter(JSONField target, Object... objects) {

                // begin - PATCH [2016. 2. 5.]: Use JSONField.inheritable() | Park_Jun_Hong_(fafanmama_at_naver_com)
                // if (target.isfinal()) {
                // return false;
                // }
                if (!target.inheritable()) {
                    return false;
                }
                // end - Park_Jun_Hong_(fafanmama_at_naver_com), 2016. 2. 5.

                Field targetField = (Field) objects[0];

                for (Field f : annotated.keySet()) {
                    if (ReflectionUtils.equalsName(targetField, f)) {
                        return false;
                    }
                }

                return true;
            }
        };

        while (superClass != null //
                && IJSONString.class.isAssignableFrom(superClass)) {

            annotated.putAll(ReflectionUtils.getAnnotatedFields(superClass, JSONField.class, filter));

            superClass = getSuperClass(superClass);
        }
    }

    /**
     * 클래스 변수에 대한 {@link GenericTypeVariable}들을 반환한다.<br>
     * 기존에 {@link BuildConfig}를 통해서 추가하는 방법을 대치하는 메소드이다.<br>
     * 
     * @return <code>nullable.</code>
     * 
     * @since 2014. 6. 18.
     * 
     * @see IDynamicNamedJSONString
     * @see DynamicNamedJSONModel
     */
    protected abstract Map<String, GenericTypeVariable> loadDynamicNamedFieldGenericTypeVar();;

    @Override
    public final IJSONString mature(JSONObject json) throws JSONException {
        mature(json, new GenericTypeVariable[] {});

        return this;
    }

    /**
     * @see open.commons.json.IJSONString#mature(code.org.codehaus.jettison.json.JSONObject,
     *      open.commons.json.model.BuildConfig)
     */
    @Override
    public final IJSONString mature(JSONObject json, BuildConfig buildConfig) throws JSONException {
        AssertUtils.assertNulls("Neithger json and buildConfig MUST be null. json: " + json + ", buildConfig: " + buildConfig);

        buildConfig(buildConfig);

        mature0(json);

        JSONMapper.unregister(this);

        return this;
    }

    /**
     * @see open.commons.json.IJSONString#mature(code.org.codehaus.jettison.json.JSONObject, java.lang.Class[])
     */
    @Override
    public IJSONString mature(JSONObject json, Class<?> paramType) throws JSONException {
        return mature(json, GenericTypeVariable.getParamType(paramType));
    }

    public final IJSONString mature(JSONObject json, GenericTypeVariable... paramTypes) throws JSONException {
        AssertUtils.assertNull("The json MUST NOT be null. json: null", json);
        AssertUtils.assertNulls((Object[]) paramTypes);

        buildConfig(BuildConfig.config(paramTypes));

        mature0(json);

        JSONMapper.unregister(this);

        return this;
    }

    /**
     * @see open.commons.json.IJSONString#mature(code.org.codehaus.jettison.json.JSONObject, java.util.List)
     */
    @Override
    public IJSONString mature(JSONObject json, List<Class<?>> paramTypes) throws JSONException {

        GenericTypeVariable[] paramTypeArray = new GenericTypeVariable[paramTypes.size()];

        int i = 0;
        for (Class<?> paramType : paramTypes) {
            paramTypeArray[i] = GenericTypeVariable.getParamType(paramType);
            i++;
        }

        return mature(json, paramTypeArray);
    }

    public final IJSONString mature(String jsonString) throws JSONException {
        AssertUtils.assertNull("The jsonString MUST NOT be null. jsonString: null", jsonString);

        mature(new JSONObject(jsonString), new GenericTypeVariable[] {});

        return this;
    }

    /**
     * @see open.commons.json.IJSONString#mature(java.lang.String, open.commons.json.model.BuildConfig)
     */
    @Override
    public final IJSONString mature(String jsonString, BuildConfig buildConfig) throws JSONException {
        AssertUtils.assertNulls("Neithger jsonString and buildConfig MUST be null. jsonString: " + jsonString + ", buildConfig: " + buildConfig);

        buildConfig(buildConfig);

        mature0(new JSONObject(jsonString));

        JSONMapper.unregister(this);

        return this;
    }

    /**
     * @see open.commons.json.IJSONString#mature(java.lang.String, java.lang.Class)
     */
    @Override
    public IJSONString mature(String jsonString, Class<?> paramType) throws JSONException {
        return mature(new JSONObject(jsonString), GenericTypeVariable.getParamType(paramType));
    }

    public final IJSONString mature(String jsonString, GenericTypeVariable... typeVars) throws JSONException {
        AssertUtils.assertNull("The jsonString MUST NOT be null. jsonString: null", jsonString);

        mature(new JSONObject(jsonString), typeVars);

        return this;
    }

    /**
     * @see open.commons.json.IJSONString#mature(java.lang.String, java.util.List)
     */
    @Override
    public IJSONString mature(String jsonString, List<Class<?>> typeVars) throws JSONException {
        GenericTypeVariable[] typeVarArray = new GenericTypeVariable[typeVars.size()];

        int i = 0;
        for (Class<?> typeVar : typeVars) {
            typeVarArray[i] = GenericTypeVariable.getParamType(typeVar);
            i++;
        }

        return mature(new JSONObject(jsonString), typeVarArray);
    }

    protected abstract void mature0(JSONObject json) throws JSONException;

    private JSONObject processJSONObject() throws JSONException {

        JSONObject jsonobject = toJSONObject0();
        JSONMapper.unregister(this);
        return jsonobject;
    }

    /**
     * 객체에 저장된 값을 {@link JSONObject}로 설정한다.<br>
     * 이 메소드의 목적은 객체에 저장된 데이터의 형태가 변경되어 {@link JSONObject}형태로 전달되는 경우 사용한다.
     * 
     * @param json
     * @param fieldName
     * @param fieldValue
     * @throws JSONException
     */
    protected void putValue(JSONObject json, String fieldName, Object fieldValue) throws JSONException {
        json.put(fieldName, fieldValue);
    }

    /**
     * 객체의 해당 {@link Field}에 값을 설정한다.<br>
     * 이 메소드의 목적은 JSON 방식으로 전달된 데이터를 가공하여 객체에 설정하는 경우를 위해서이다.
     * 
     * @param f
     * @param fieldName
     * @param json
     * @param marshaller
     *            데이터를 변환해주는 객체
     * @throws IllegalArgumentException
     * @throws IllegalAccessException
     * @throws JSONException
     */
    protected void setValue(Field f, String fieldName, JSONObject json, IFieldMarshaller<?> marshaller) throws IllegalArgumentException, IllegalAccessException, JSONException {

        Object value = JSONUtil.get(json, fieldName);
        if (marshaller != null) {
            value = marshaller.unmarshall(value);
        }

        f.set(this, value);
    }

    /**
     * 객체의 해당 {@link Field}에 값을 설정한다.<br>
     * 이 메소드의 목적은 JSON 방식으로 전달된 데이터를 가공하여 객체에 설정하는 경우를 위해서이다.
     * 
     * @param f
     * @param fieldName
     * @param marshaller
     *            데이터를 변환해주는 객체
     * @throws IllegalArgumentException
     * @throws IllegalAccessException
     * @throws JSONException
     */
    protected void setValue(Field f, String fieldName, Object value, IMarshaller<?> marshaller) throws IllegalArgumentException, IllegalAccessException, JSONException {

        if (marshaller != null) {
            value = marshaller.unmarshall(value);
        }

        f.set(this, value);
    }

    protected JSONArray toJSONArray(Object array, Class<?> componentType) {

        JSONArray jsonArray = new JSONArray();

        Object[] objArray = ConvertUtils.wrapClassArray(componentType, array);

        for (Object obj : objArray) {
            jsonArray.put(obj);
        }

        return jsonArray;
    }

    /**
     * {@link #toJSONObject0()}를 이용한다.<br>
     * 하위 클래스는 목적에 맞도록 이 메소드를 오버라이딩 해야 한다.
     * 
     * @see #toJSONObject0()
     */
    @Override
    public final JSONObject toJSONObject() throws JSONException {

        buildConfig(BuildConfig.config(new GenericTypeVariable[] {}));

        return processJSONObject();
    }

    /**
     * @see open.commons.json.IJSONString#toJSONObject(boolean)
     */
    @Override
    public JSONObject toJSONObject(boolean ordered) throws JSONException {
        JSONObject jsonObject = toJSONObject();
        return ordered ? toOrderedJSONObject(jsonObject) : jsonObject;
    }

    /**
     * @see open.commons.json.IJSONString#toJSONObject(boolean, open.commons.reflect.GenericTypeVariable[])
     */
    @Override
    public JSONObject toJSONObject(boolean ordered, GenericTypeVariable... paramTypes) throws JSONException {
        JSONObject jsonObject = toJSONObject(paramTypes);
        return ordered ? toOrderedJSONObject(jsonObject) : jsonObject;
    }

    /**
     * @see open.commons.json.IJSONString#toJSONObject(open.commons.json.model.BuildConfig)
     */
    @Override
    public final JSONObject toJSONObject(BuildConfig buildConfig) throws JSONException {

        buildConfig(buildConfig);

        return processJSONObject();
    }

    /**
     * @see open.commons.json.IJSONString#toJSONObject(open.commons.json.model.BuildConfig, boolean)
     */
    @Override
    public JSONObject toJSONObject(BuildConfig buildConfig, boolean ordered) throws JSONException {
        JSONObject jsonObject = toJSONObject(buildConfig);
        return ordered ? toOrderedJSONObject(jsonObject) : jsonObject;
    }

    /**
     * @see open.commons.json.IJSONString#toJSONObject(java.lang.Class)
     */
    @Override
    public JSONObject toJSONObject(Class<?> paramType) throws JSONException {
        return toJSONObject(GenericTypeVariable.getParamType(paramType));
    }

    /**
     * @see open.commons.json.IJSONString#toJSONObject(java.lang.Class, boolean)
     */
    @Override
    public JSONObject toJSONObject(Class<?> paramType, boolean ordered) throws JSONException {
        JSONObject jsonObject = toJSONObject(paramType);
        return ordered ? toOrderedJSONObject(jsonObject) : jsonObject;
    }

    /**
     * @see open.commons.json.IJSONString#toJSONObject(open.commons.reflect.GenericTypeVariable[])
     */
    @Override
    public final JSONObject toJSONObject(GenericTypeVariable... paramTypes) throws JSONException {

        buildConfig(BuildConfig.config(paramTypes));

        return processJSONObject();
    }

    /**
     * @see open.commons.json.IJSONString#toJSONObject(java.util.List)
     */
    @Override
    public JSONObject toJSONObject(List<Class<?>> paramTypes) throws JSONException {

        GenericTypeVariable[] paramTypeArray = new GenericTypeVariable[paramTypes.size()];

        int i = 0;
        for (Class<?> paramType : paramTypes) {
            paramTypeArray[i] = GenericTypeVariable.getParamType(paramType);
            i++;
        }

        return toJSONObject(paramTypeArray);
    }

    /**
     * @see open.commons.json.IJSONString#toJSONObject(java.util.List, boolean)
     */
    @Override
    public JSONObject toJSONObject(List<Class<?>> paramTypes, boolean ordered) throws JSONException {
        JSONObject jsonObject = toJSONObject(paramTypes);
        return ordered ? toOrderedJSONObject(jsonObject) : jsonObject;
    }

    /**
     * 하위 클래스 필드 중에 {@link JSONField}가 설정된 필드를 이용해서 자동으로 생성한다.
     */
    protected abstract JSONObject toJSONObject0() throws JSONException;

    /**
     * 
     * @see open.commons.json.IJSONString#toJSONString()
     */
    @Override
    public String toJSONString() throws JSONException {
        return toJSONObject().toString();
    }

    /**
     * @see open.commons.json.IJSONString#toJSONString(boolean)
     */
    @Override
    public String toJSONString(boolean ordered) throws JSONException {
        return toJSONObject(ordered).toString();
    }

    /**
     * @see open.commons.json.IJSONString#toJSONString(boolean, open.commons.reflect.GenericTypeVariable[])
     */
    @Override
    public String toJSONString(boolean ordered, GenericTypeVariable... paramTypes) throws JSONException {
        return toJSONObject(ordered, paramTypes).toString();
    }

    /**
     * @see open.commons.json.IJSONString#toJSONString(open.commons.json.model.BuildConfig)
     */
    @Override
    public String toJSONString(BuildConfig buildConfig) throws JSONException {
        return toJSONObject(buildConfig).toString();
    }

    /**
     * @see open.commons.json.IJSONString#toJSONString(open.commons.json.model.BuildConfig, boolean)
     */
    @Override
    public String toJSONString(BuildConfig buildConfig, boolean ordered) throws JSONException {
        return toJSONObject(buildConfig, ordered).toString();
    }

    /**
     * @see open.commons.json.IJSONString#toJSONString(open.commons.json.model.BuildConfig, int)
     */
    @Override
    public String toJSONString(BuildConfig buildConfig, int factor) throws JSONException {
        return JSONUtil.toString(toJSONObject(buildConfig).toString(), factor);
    }

    /**
     * @see open.commons.json.IJSONString#toJSONString(open.commons.json.model.BuildConfig, int, boolean)
     */
    @Override
    public String toJSONString(BuildConfig buildConfig, int factor, boolean ordered) throws JSONException {
        return JSONUtil.toString(toJSONObject(buildConfig, ordered).toString(), factor);
    }

    /**
     * @see open.commons.json.IJSONString#toJSONString(java.lang.Class)
     */
    @Override
    public String toJSONString(Class<?> paramType) throws JSONException {
        return toJSONString(GenericTypeVariable.getParamType(paramType));
    }

    /**
     * @see open.commons.json.IJSONString#toJSONString(java.lang.Class, boolean)
     */
    @Override
    public String toJSONString(Class<?> paramType, boolean ordered) throws JSONException {
        return toJSONString(ordered, GenericTypeVariable.getParamType(paramType));
    }

    /**
     * @see open.commons.json.IJSONString#toJSONString(open.commons.reflect.GenericTypeVariable[])
     */
    @Override
    public String toJSONString(GenericTypeVariable... paramTypes) throws JSONException {
        return toJSONObject(paramTypes).toString();
    }

    @Override
    public String toJSONString(int factor) throws JSONException {
        return JSONUtil.toString(toJSONObject().toString(), factor);
    }

    /**
     * @see open.commons.json.IJSONString#toJSONString(int, boolean)
     */
    @Override
    public String toJSONString(int factor, boolean ordered) throws JSONException {
        return JSONUtil.toString(toJSONObject(ordered).toString(), factor);
    }

    /**
     * @see open.commons.json.IJSONString#toJSONString(int, boolean, open.commons.reflect.GenericTypeVariable[])
     */
    @Override
    public String toJSONString(int factor, boolean ordered, GenericTypeVariable... paramTypes) throws JSONException {
        return JSONUtil.toString(toJSONObject(ordered, paramTypes).toString(), factor);
    }

    /**
     * @see open.commons.json.IJSONString#toJSONString(int, java.lang.Class)
     */
    @Override
    public String toJSONString(int factor, Class<?> paramType) throws JSONException {
        return JSONUtil.toString(toJSONObject(GenericTypeVariable.getParamType(paramType)).toString(), factor);
    }

    /**
     * @see open.commons.json.IJSONString#toJSONString(int, java.lang.Class, boolean)
     */
    @Override
    public String toJSONString(int factor, Class<?> paramType, boolean ordered) throws JSONException {
        return JSONUtil.toString(toJSONObject(ordered, GenericTypeVariable.getParamType(paramType)).toString(), factor);
    }

    /**
     * @see open.commons.json.IJSONString#toJSONString(int, open.commons.reflect.GenericTypeVariable[])
     */
    @Override
    public String toJSONString(int factor, GenericTypeVariable... paramTypes) throws JSONException {
        return JSONUtil.toString(toJSONObject(paramTypes).toString(), factor);
    }

    /**
     * @see open.commons.json.IJSONString#toJSONString(int, java.util.List)
     */
    @Override
    public String toJSONString(int factor, List<Class<?>> paramTypes) throws JSONException {
        return toJSONString(factor, paramTypes, false);
    }

    /**
     * @see open.commons.json.IJSONString#toJSONString(int, java.util.List, boolean)
     */
    @Override
    public String toJSONString(int factor, List<Class<?>> paramTypes, boolean ordered) throws JSONException {
        GenericTypeVariable[] paramTypeArray = new GenericTypeVariable[paramTypes.size()];

        int i = 0;
        for (Class<?> paramType : paramTypes) {
            paramTypeArray[i] = GenericTypeVariable.getParamType(paramType);
            i++;
        }

        return toJSONString(factor, ordered, paramTypeArray);
    }

    /**
     * @see open.commons.json.IJSONString#toJSONString(java.util.List)
     */
    @Override
    public String toJSONString(List<Class<?>> paramTypes) throws JSONException {
        return toJSONString(paramTypes, false);
    }

    /**
     * @see open.commons.json.IJSONString#toJSONString(java.util.List, boolean)
     */
    @Override
    public String toJSONString(List<Class<?>> paramTypes, boolean ordered) throws JSONException {
        GenericTypeVariable[] paramTypeArray = new GenericTypeVariable[paramTypes.size()];

        int i = 0;
        for (Class<?> paramType : paramTypes) {
            paramTypeArray[i] = GenericTypeVariable.getParamType(paramType);
            i++;
        }

        return toJSONString(ordered, paramTypeArray);
    }

    /**
     * 
     * @param jsonObject
     * @return
     * @throws JSONException
     *
     * @since 2016. 5. 26.
     * @since 1.6.0
     */
    private JSONObject toOrderedJSONObject(JSONObject jsonObject) throws JSONException {

        Map<Field, JSONField> annotated = getAnnotatedFields();

        Map<Integer, List<String>> orderedProps = new TreeMap<>();

        Field f = null;
        String fieldName = null;
        int order = 0;
        JSONField anno = null;
        List<String> props = null;

        for (Entry<Field, JSONField> entry : annotated.entrySet()) {
            f = entry.getKey();
            anno = entry.getValue();

            props = orderedProps.get(order = anno.order());

            if (props == null) {
                orderedProps.put(order, props = new ArrayList<>());
            }

            fieldName = anno.name();

            if ("".equals(fieldName)) {
                fieldName = f.getName();
            }

            props.add(fieldName);
        }

        JSONObject orderedJsonObject = new JSONObject();
        for (List<String> propsList : orderedProps.values()) {
            for (String prop : propsList) {
                orderedJsonObject.put(prop, (Object)JSONUtil.get(jsonObject, prop, null));
            }
        }

        return orderedJsonObject;
    }

    public String toString() {
        StringBuffer sb = new StringBuffer();

        Iterator<Field> fields = ReflectionUtils.getAllAnnotatedFields(this, JSONField.class).keySet().iterator();

        Field field = null;

        sb.append(getClass().getSimpleName());
        sb.append('[');
        if (fields.hasNext()) {
            field = fields.next();

            sb.append(field.getName());
            sb.append('=');
            sb.append(ReflectionUtils.getValue(field, this, "no value"));

            while (fields.hasNext()) {
                field = fields.next();

                sb.append(',');
                sb.append(' ');
                sb.append(field.getName());
                sb.append('=');
                sb.append(ReflectionUtils.getValue(field, this, "no value"));
            }
        }

        sb.append(']');

        return sb.toString();
    }
}
