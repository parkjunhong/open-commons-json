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

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.lang.model.type.NullType;

import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

import open.commons.io.IMarshaller;
import open.commons.json.IFieldMarshaller;
import open.commons.json.annotation.JSONField;
import open.commons.json.model.exception.JSONUnmarshalledException;
import open.commons.json.model.exception.RequiredNameNotFoundException;
import open.commons.json.util.JSONUtil;
import open.commons.reflect.GenericTypeVariable;

/**
 * 하위 클래스들의 필드중에서 {@link JSONField}를 사용한 필드들을 검색해 자동으로 {@link #toString()}를 제공해주는 클래스.
 * 
 * @author Park_Jun_Hong_(fafanmama_at_naver_com)
 * 
 */
@SuppressWarnings({ "serial" })
public abstract class DefaultJSONModel extends AbstractJSONModel {

    /**
     * 클래스 필드에 적용된 타입 정보들.<br>
     * 아래와 같은 경우 Field(list)에 대한 정보는 [java.util.List, java.lang.Integer] 이다.
     * 
     * <pre>
     * class A {
     *     List&lt;Integer&gt; list;
     * }
     * </pre>
     * 
     */
    private Map<Field, ArrayList<Object>> fieldActualTypeArgClasses = new HashMap<Field, ArrayList<Object>>();

    protected void mature0(JSONObject json) throws JSONException {

        String fieldName = null;
        IFieldMarshaller<?> marshaller = null;
        String marshallerName = null;

        boolean oldAccessible = false;

        Field f = null;
        JSONField anno = null;

        Map<Field, JSONField> annotated = getAnnotatedFields();

        for (Entry<Field, JSONField> entry : annotated.entrySet()) {
            f = entry.getKey();
            anno = entry.getValue();

            fieldName = anno.name();

            // check 'JSON Property name'
            if ("".equals(fieldName)) {
                fieldName = f.getName();
            }

            try {
                oldAccessible = f.isAccessible();
                f.setAccessible(true);

                if (JSONUtil.hasValue(json, fieldName)) {

                    // check 'Marshaller'
                    marshallerName = anno.marshaller();
                    if (!"".equals(marshallerName)) {
                        marshaller = JSONMapper.getFieldMarshaller(marshallerName);
                    }

                    // check 'Field Generic Param Types'
                    // e.g. : Set<String>
                    Class<?>[] gpt = anno.genericParamClasses();
                    if (gpt.length != 1 || gpt[0].equals(NullType.class)) {

                    }

                    ArrayList<Object> actualTypeArgClasses = fieldActualTypeArgClasses.get(f);

                    if (actualTypeArgClasses == null) {
                        actualTypeArgClasses = JSONMapper.investigateField(this, f, fieldName);
                    }

                    if (actualTypeArgClasses.size() > 0) {
                        fieldActualTypeArgClasses.put(f, actualTypeArgClasses);

                        setValue0(f, fieldName, json, new ArrayList<Object>(actualTypeArgClasses), marshaller);
                    }
                } else {

                    if (anno.required()) {
                        throw new RequiredNameNotFoundException("'name' is NOT EXIST. The name is '" + fieldName + "'");
                    }
                }

                f.setAccessible(oldAccessible);
            } catch (IllegalArgumentException e) {
                e.printStackTrace();
            } finally {
                marshaller = null;
            }
        }
    }

    /**
     * @see open.commons.json.model.AbstractJSONModel#loadDynamicNamedFieldGenericTypeVar()
     */
    @Override
    protected Map<String, GenericTypeVariable> loadDynamicNamedFieldGenericTypeVar() {
        return null;
    }

    private void putValue0(JSONObject json, String fieldName, Object value, List<Object> typeInfoList, IMarshaller<?> marshaller) throws JSONException {
        try {
            // Object jsonValue = JSONMapper.marshall(value, getBuildConfig().getFieldBuildConfig(fieldName),
            // marshaller,
            // typeInfoList.toArray());

            // TEST-CODE - [Park_Jun_Hong_(fafanmama_at_naver_com)]: 2014. 4. 7.
            Object jsonValue = JSONMapper.marshall(value, JSONMapper.getBuildConfig(this).getFieldBuildConfig(fieldName), marshaller, typeInfoList.toArray());

            if (jsonValue != null) {
                putValue(json, fieldName, jsonValue);
            }
        } catch (Exception e) {
            throw new JSONException(e);
        }
    }

    /**
     * 
     * @param f
     *            변수 객체
     * @param fieldName
     *            변수 이름
     * @param json
     *            JSON 객체
     * @param typeInfoList
     *            변수 타입 정보.
     * @param marshaller
     *            해당 변수에 설정된 변환기
     * @throws JSONException
     */
    private void setValue0(Field f, String fieldName, JSONObject json, List<Object> typeInfoList, IMarshaller<?> marshaller) throws JSONException {
        try {
            // Object value = JSONMapper.unmarshall(fieldName, JSONUtil.get(json, fieldName), getBuildConfig(),
            // typeInfoList.toArray());

            // TEST-CODE - [Park_Jun_Hong_(fafanmama_at_naver_com)]: 2014. 4. 7.
            Object value = JSONMapper.unmarshall(fieldName, JSONUtil.get(json, fieldName), JSONMapper.getBuildConfig(this).getFieldBuildConfig(fieldName), typeInfoList.toArray());

            if (marshaller == null) {
                marshaller = JSONMapper.getMarshaller(typeInfoList.get(0));
            }

            setValue(f, fieldName, value, marshaller);
        } catch (Exception e) {
            throw new JSONUnmarshalledException("\n(field): " + f + "\n(field-name): " + fieldName + "\n(json): " + json, e);
        }
    }

    /**
     * 하위 클래스 필드 중에 {@link JSONField}가 설정된 필드를 이용해서 자동으로 생성한다.
     */
    protected JSONObject toJSONObject0() throws JSONException {
        JSONObject json = new JSONObject();
        json = new JSONObject(false, null, true, false);

        String fieldName = null;
        Object fieldValue = null;

        IMarshaller<?> marshaller = null;
        String marshallerName = null;

        boolean oldAccessible = false;

        Field f = null;
        JSONField anno = null;

        Map<Field, JSONField> annotated = getAnnotatedFields();

        for (Entry<Field, JSONField> entry : annotated.entrySet()) {
            f = entry.getKey();
            anno = entry.getValue();

            // JSON 모델로 export 를 하는지 여부 확인
            if (!anno.export()) {
                continue;
            }

            fieldName = anno.name();

            if ("".equals(fieldName)) {
                fieldName = f.getName();
            }

            marshallerName = anno.marshaller();
            if (!"".equals(marshallerName)) {
                marshaller = JSONMapper.getFieldMarshaller(marshallerName);
            }

            try {
                oldAccessible = f.isAccessible();
                f.setAccessible(true);

                fieldValue = f.get(this);

                // 값이 없는 경우 항목 자체를 버림
                if (fieldValue == null) {
                    continue;
                }

                ArrayList<Object> actualTypeArgClasses = fieldActualTypeArgClasses.get(f);

                if (actualTypeArgClasses == null) {
                    actualTypeArgClasses = JSONMapper.investigateField(this, f, fieldName);
                }

                if (actualTypeArgClasses.size() > 0) {
                    fieldActualTypeArgClasses.put(f, actualTypeArgClasses);

                    if (marshaller == null) {
                        marshaller = JSONMapper.getMarshaller(f.getType());
                    }

                    putValue0(json, fieldName, fieldValue, actualTypeArgClasses, marshaller);
                }

            } catch (IllegalArgumentException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } finally {

                f.setAccessible(oldAccessible);

                marshaller = null;
            }
        }

        return json;
    }
}
