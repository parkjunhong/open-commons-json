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


 * 
 *
 * This file is generated under this project, "open-commons-json".
 *
 * Date  : 2014. 3. 13. 오전 12:37:21
 *
 * Author: Park_Jun_Hong_(parkjunhong77@gmail.com)
 * 
 */

package open.commons.json.model;

import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.lang.reflect.GenericArrayType;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

import open.commons.core.concurrent.Mutex;
import open.commons.core.io.IMarshaller;
import open.commons.core.reflect.GenericTypeVariable;
import open.commons.core.utils.ArrayUtils;
import open.commons.core.utils.AssertUtils;
import open.commons.core.utils.ConvertUtils;
import open.commons.json.IEnumMarshaller;
import open.commons.json.IFieldMarshaller;
import open.commons.json.IJSONString;
import open.commons.json.model.exception.ImplClassNotFoundException;
import open.commons.json.model.exception.JSONMarshalledException;
import open.commons.json.model.exception.JSONUnmarshalledException;
import open.commons.json.model.exception.MarshallerNotFoundException;
import open.commons.jsonx.IDynamicNamedJSONString;

/**
 * Default implemented classes are followings.
 * 
 * <pre>
 * java.util.Collection:1.2=java.util.ArrayList:1.2
 * java.util.List:1.2=java.util.ArrayList:1.2
 * java.util.Set:1.2=java.util.HashSet:1.2
 * java.util.SortedSet:1.2=java.util.TreeSet:1.2
 * java.util.Map:1.2=java.util.HashMap:1.2
 * java.util.SortedMap:1.2=java.util.TreeMap:1.2
 * 
 * java.util.Queue:1.5=java.util.concurrent.ConcurrentLinkedQueue:1.5
 * java.util.concurrent.ConcurrentMap:1.5=java.util.concurrent.ConcurrentHashMap:1.5
 * java.util.concurrent.BlockingQueue:1.5=java.util.concurrent.ArrayBlockingQueue:1.5
 * 
 * java.util.Deque:1.6=java.util.ArrayDeque:1.6
 * java.util.concurrent.BlockingDeque:1.6=java.util.concurrent.LinkedBlockingDeque:1.6
 * java.util.NavigableSet:1.6=java.util.concurrent.ConcurrentSkipListSet:1.6
 * javax.script.Bindings:1.6=javax.script.SimpleBindings:1.6
 * java.util.concurrent.ConcurrentNavigableMap:1.6=java.util.concurrent.ConcurrentSkipListMap:1.6
 * java.util.NavigableMap:1.6=java.util.TreeMap:1.2
 * 
 * java.util.concurrent.TransferQueue:1.7=java.util.concurrent.LinkedTransferQueue:1.7
 * </pre>
 * 
 * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
 * 
 */
@SuppressWarnings("rawtypes")
public class JSONMapper {

    /**
     * {@link Collection} 및 하위 인터페이스, {@link Map} 및 하위 인터페이스를 구현한 클래스 제공자.
     */
    private static final HashMap<String, Class<?>> containerMapper = new HashMap<String, Class<?>>();
    private static Mutex mutexContainerMapper = new Mutex("containerMapper");

    static {
        // Support JDK 1.2
        register("java.util.Collection", "java.util.ArrayList");
        register("java.util.List", "java.util.ArrayList");
        register("java.util.Set", "java.util.HashSet");
        register("java.util.SortedSet", "java.util.TreeSet");
        register("java.util.Map", "java.util.HashMap");
        register("java.util.SortedMap", "java.util.TreeMap");

        // Support JDK 1.5
        register("java.util.Queue", "java.util.concurrent.ConcurrentLinkedQueue");
        register("java.util.concurrent.ConcurrentMap", "java.util.concurrent.ConcurrentHashMap");
        register("java.util.concurrent.BlockingQueue", "java.util.concurrent.ArrayBlockingQueue");

        // Support JDK 1.6
        register("java.util.Deque", "java.util.ArrayDeque");
        register("java.util.concurrent.BlockingDeque", "java.util.concurrent.LinkedBlockingDeque");
        register("java.util.NavigableSet", "java.util.concurrent.ConcurrentSkipListSet");
        register("javax.script.Bindings", "javax.script.SimpleBindings");
        register("java.util.concurrent.ConcurrentNavigableMap", "java.util.concurrent.ConcurrentSkipListMap");
        register("java.util.NavigableMap", "java.util.TreeMap");

        // Support JDK 1.7
        register("java.util.concurrent.TransferQueue", "java.util.concurrent.LinkedTransferQueue");
    }

    /** primitive type */
    public static final int FIELD_TYPE_PRIMITIVE = 0x00;
    public static final int FIELD_TYPE_STRING = 0x01;
    public static final int FIELD_TYPE_ARRAY = 0x02;
    public static final int FIELD_TYPE_ENUM = 0x03;
    /** {@link IJSONString}을 구현한 클래스 */
    public static final int FIELD_TYPE_JSON_CLASS = 0x04;
    public static final int FIELD_TYPE_COLLECTION = 0x05;
    public static final int FIELD_TYPE_MAP = 0x06;
    public static final int FIELD_TYPE_GENERIC_ARRAY = 0x07;
    /** */
    public static final int FIELD_TYPE_GENERIC_TYPE_VARIABLE = 0x08;
    /** open.commons.jsonx.IDynamicNamedJSONString */
    public static final int FIELD_TYPE_DYNAMIC_NAMED_JSON_CLASS = 0x09;

    /**
     * key: A class which does not implement {@link IJSONString}.<br>
     * value: a numeric for field type.
     */
    private static final HashMap<Object, Integer> unknownTypeMarshaller = new HashMap<Object, Integer>();
    private static final AtomicInteger unknownTypeMarshallerCounter = new AtomicInteger(FIELD_TYPE_DYNAMIC_NAMED_JSON_CLASS);

    public static final int FIELD_TYPE_UNKNOWN = Integer.MAX_VALUE;

    private static final Map<Integer, String> fieldTypeStrings = new ConcurrentHashMap<Integer, String>();
    static {
        fieldTypes(FIELD_TYPE_ARRAY, "Array");
        fieldTypes(FIELD_TYPE_COLLECTION, "Collection");
        fieldTypes(FIELD_TYPE_DYNAMIC_NAMED_JSON_CLASS, "Dynamic Named JSON class");
        fieldTypes(FIELD_TYPE_ENUM, "Enum");
        fieldTypes(FIELD_TYPE_GENERIC_ARRAY, "Generic Array");
        fieldTypes(FIELD_TYPE_GENERIC_TYPE_VARIABLE, "Generic Type Variable");
        fieldTypes(FIELD_TYPE_JSON_CLASS, "JSON class");
        fieldTypes(FIELD_TYPE_MAP, "Map");
        fieldTypes(FIELD_TYPE_PRIMITIVE, "Primitive types");
        fieldTypes(FIELD_TYPE_STRING, "String");
        fieldTypes(FIELD_TYPE_UNKNOWN, "Unknown");
    }

    public static final int CONTAINER_TYPE_LIST = 0xA0;
    public static final int CONTAINER_TYPE_SET = 0xA1;
    public static final int CONTAINER_TYPE_QEUEU = 0xA2;
    public static final int CONTAINER_TYPE_MAP = 0xA3;
    public static final int CONTAINER_TYPE_COLLECTION = 0xA4;
    /**
     * {@link IJSONString}를 구현한 클래스의 필드값을 변경해주는 객체.
     */
    private static final HashMap<Object, IFieldMarshaller<?>> fieldMarshallers = new HashMap<Object, IFieldMarshaller<?>>();
    private static Mutex mutexFieldMarshallers = new Mutex("fieldMarshallers");

    /**
     * {@link IJSONString}를 구현한 클래스의 필드 중에 {@link Enum}을 처리해주는 객체.
     */
    private static final HashMap<Object, IEnumMarshaller<?>> enumMarshallers = new HashMap<Object, IEnumMarshaller<?>>();
    private static Mutex mutexEnumMarshaller = new Mutex("enumMarshallers");

    /**
     * for classes not implements {@link IJSONString}.
     */
    private static final HashMap<Object, IMarshaller<?>> objectMarshallers = new HashMap<Object, IMarshaller<?>>();
    private static Mutex mutexObjectMarshallers = new Mutex("objectMarshallers");

    private static final HashMap<Class<?>, Map<String, Class<?>>> fieldClasses = new HashMap<Class<?>, Map<String, Class<?>>>();
    private static final Object mutexFieldClasses = new Object();

    private static final ConcurrentHashMap<IJSONString, BuildConfig> buildConfigs = new ConcurrentHashMap<IJSONString, BuildConfig>();
    private static Mutex mutexBuildConfigs = new Mutex("buildConfigs");

    /**
     * {@link JSONObject}를 {@link IJSONString}을 구현한 클래스로 변환해서 반환한다.
     * 
     * 
     * @param json
     * @param classType
     * @return
     * 
     * @see <p>
     *      차후에 {@link DefaultJSONModel}에서 변환 기능을 {@link JSONMapper}로 이관하는 경우 개발한다.
     *      </p>
     */
    public static <T extends IJSONString> T convert(JSONObject json, Class<T> classType) {

        return null;
    }

    /**
     * JSON 타입의 문자열을 {@link IJSONString}을 구현한 클래스로 변환해서 반환한다.
     * 
     * @param json
     * @param classType
     * @return
     * 
     * @see <p>
     *      차후에 {@link DefaultJSONModel}에서 변환 기능을 {@link JSONMapper}로 이관하는 경우 개발한다.
     *      </p>
     */
    public static <T extends IJSONString> T convert(String json, Class<T> classType) {
        return null;
    }

    /**
     * {@link IJSONString}를 구현한 클래스를 {@link JSONObject}로 변환해서 반환한다.
     * 
     * @param obj
     * @return
     * 
     * @see <p>
     *      차후에 {@link DefaultJSONModel}에서 변환 기능을 {@link JSONMapper}로 이관하는 경우 개발한다.
     *      </p>
     */
    public static <T extends IJSONString> JSONObject convert(T obj) {

        return null;
    }

    private static final void fieldTypes(int fieldType, String fieldTypeString) {
        fieldTypeStrings.put(fieldType, fieldTypeString);
    }

    public static <T extends IJSONString> BuildConfig getBuildConfig(T obj) {
        AssertUtils.assertNulls(obj);

        synchronized (mutexBuildConfigs) {
            return buildConfigs.get(obj);
        }
    }

    public static int getBuildConfigCount() {
        synchronized (mutexBuildConfigs) {
            return buildConfigs.size();
        }
    }

    /**
     * 
     * @param key
     * @return
     * @throws ClassCastException
     * @throws NullPointerException
     *             If 'key' is null.
     */
    @SuppressWarnings("unchecked")
    public static <T extends IEnumMarshaller<?>> T getEnumMarshaller(Object key) throws ClassCastException, NullPointerException {

        AssertUtils.assertNull("key MUST NOT be null. key: " + key, key);

        synchronized (mutexEnumMarshaller) {
            return (T) enumMarshallers.get(key);
        }
    }

    /**
     *
     * @deprecated DO NOT USE.
     * 
     * @param objectClass
     * @param fieldName
     * @return
     * @throws NullPointerException
     *
     * @since 2015. 1. 6.
     */
    public static Class<?> getFieldClass(Class<?> objectClass, String fieldName) throws NullPointerException {

        AssertUtils.assertNulls("Neither ObjectClass and FieldName are null.", objectClass, fieldName);

        synchronized (mutexFieldClasses) {
            Map<String, Class<?>> classes = fieldClasses.get(objectClass);

            return classes != null ? classes.get(fieldName) : null;
        }
    }

    /**
     * 
     * @param key
     * @return
     * @throws ClassCastException
     * @throws NullPointerException
     *             If 'key' is null.
     * 
     */
    @SuppressWarnings("unchecked")
    public static <T extends IFieldMarshaller<?>> T getFieldMarshaller(Object key) throws ClassCastException, NullPointerException {
        AssertUtils.assertNull("key MUST NOT be null. key: " + key, key);

        synchronized (mutexFieldMarshallers) {
            return (T) fieldMarshallers.get(key);
        }
    }

    /**
     * Field_TYPE_XXX 값에 해당하는 문자열을 반환한다.
     * 
     * @param fieldType
     * @return
     * 
     * @since 2014. 6. 17.
     */
    public static String getFieldTypeString(int fieldType) {
        return fieldTypeStrings.get(fieldType);
    }

    /**
     * Return a class Class that represents the specific interface.
     * 
     * @param interfaceClass
     * @return
     * @throws NullPointerException
     *             If 'type' is null.
     */
    public static Class<?> getImplClass(Class<?> interfaceClass) throws NullPointerException {
        AssertUtils.assertNull("type MUST NOT be null. type: " + interfaceClass, interfaceClass);

        Class<?> implClass = interfaceClass;

        if (interfaceClass.isInterface()) {
            synchronized (mutexContainerMapper) {
                implClass = containerMapper.get(interfaceClass.getName());
            }
        }

        return implClass;
    }

    /**
     * Return a class Class that represents the specific interface.
     * 
     * @param type
     * @return
     * 
     * @throws NullPointerException
     *             If 'type' is null.
     */
    public static Class<?> getImplClass(String type) throws NullPointerException {
        AssertUtils.assertNull("type MUST NOT be null. type: " + type, type);

        synchronized (mutexContainerMapper) {
            return containerMapper.get(type);
        }
    }

    /**
     * Return a instance of a class implements {@link IMarshaller}.
     * 
     * @param key
     * @return
     * @throws ClassCastException
     * @throws NullPointerException
     * 
     * @since 2014. 6. 3.
     */
    @SuppressWarnings("unchecked")
    public static <T extends IMarshaller<?>> T getMarshaller(Object key) throws ClassCastException, NullPointerException {
        AssertUtils.assertNull("key MUST NOT be null. key: " + key, key);

        synchronized (mutexObjectMarshallers) {
            return (T) objectMarshallers.get(key);
        }

    }

    // /**
    // *
    // * @param interfClass
    // * @return
    // *
    // * @deprecated Use {@link #getImplClass(Class)} instead of.
    // */
    // private static Class<?> getImplClassInternal(Class<?> interfClass) {
    //
    // Class<?> implClass = interfClass;
    //
    // if (interfClass.isInterface()) {
    // implClass = getImplClass((Class<?>) interfClass);
    // }
    //
    // return implClass;
    // }

    static <T extends IJSONString> void handleClassType(T beanObj, String fieldName, Class<?> type, ArrayList<Object> typeClasses) {
        typeClasses.add(type);

        if (type.isArray()) {
            Type componentType = type.getComponentType();

            handleType(beanObj, fieldName, componentType, typeClasses);
        }
    }

    static <T extends IJSONString> void handleGenericArrayType(T beanObj, String fieldName, GenericArrayType arrayType, ArrayList<Object> typeClasses) {

        typeClasses.add(new Object[0].getClass());

        handleType(beanObj, fieldName, arrayType.getGenericComponentType(), typeClasses);
    }

    static <T extends IJSONString> void handleParameterizedType(T beanObj, String fieldName, ParameterizedType paramType, ArrayList<Object> typeClasses) {

        Type rt = paramType.getRawType();
        Type actualTypeArg = null;
        Type[] actualTypeArgs = paramType.getActualTypeArguments();

        if (rt.equals(Map.class) || Map.class.isAssignableFrom((Class<?>) rt)) {
            actualTypeArg = actualTypeArgs[1];
        } else {
            actualTypeArg = actualTypeArgs[0];
        }

        Class<?> rtAliasClass = (Class<?>) rt;
        typeClasses.add(rtAliasClass);

        handleType(beanObj, fieldName, actualTypeArg, typeClasses);
    }

    static <T extends IJSONString> void handleType(T object, String fieldName, Type type, ArrayList<Object> typeClasses) {
        if (type instanceof Class<?>) {

            handleClassType(object, fieldName, (Class<?>) type, typeClasses);

        } else if (type instanceof ParameterizedType) {

            handleParameterizedType(object, fieldName, (ParameterizedType) type, typeClasses);

        } else if (type instanceof GenericArrayType) {

            handleGenericArrayType(object, fieldName, (GenericArrayType) type, typeClasses);

        } else if (type instanceof TypeVariable) {
            BuildConfig buildConfig = getBuildConfig(object);
            AssertUtils.assertNull("The config for TypeVariable MUST NOT be null. config: null", buildConfig);

            String genericName = ((TypeVariable) type).getName();
            GenericTypeVariable gpt = buildConfig.getGenericTypeVariable(genericName);

            AssertUtils.assertNull("There is no GenericTypeVariable for TypeVariable(name: " + genericName + ")", gpt);

            gpt.setTypeVarName(genericName);

            typeClasses.add(gpt);
        }
    }

    /**
     * Return information about a field of a object.
     * 
     * @param object
     *            an instance of a class implements {@link IJSONString}.
     * @param field
     * @param fieldName
     * @return
     * 
     * @since 2014. 5. 8.
     */
    public static <T extends IJSONString> ArrayList<Object> investigateField(T object, Field field, String fieldName) {

        ArrayList<Object> typeClasses = new ArrayList<Object>();

        Type type = field.getGenericType();

        JSONMapper.handleType(object, fieldName, type, typeClasses);

        return typeClasses;
    }

    /**
     * 
     * @param object
     *            필드와 연결된 {@link JSONObject}
     * @param buildConfig
     *            구성하기 위해서 설정된 정보.
     * @param marshaller
     * @param typeInfoList
     * @return
     * @throws JSONException
     * @throws IllegalAccessException
     * @throws InstantiationException
     * @throws CloneNotSupportedException
     * 
     * @since 2014. 6. 3 - Support classes does not implements {@link IJSONString}.
     * @since 2013. 5. 8
     */
    @SuppressWarnings({ "unchecked" })
    public static Object marshall(Object object, BuildConfig buildConfig, IMarshaller marshaller, Object... typeInfoList) throws JSONException, InstantiationException,
            IllegalAccessException, CloneNotSupportedException {

        Object rootClass = typeInfoList[0];

        int type = FIELD_TYPE_UNKNOWN;

        switch (type = JSONMapper.whatType(rootClass)) {
            case JSONMapper.FIELD_TYPE_PRIMITIVE:
            case JSONMapper.FIELD_TYPE_STRING:
            case JSONMapper.FIELD_TYPE_ENUM:
            case JSONMapper.FIELD_TYPE_JSON_CLASS:
            case JSONMapper.FIELD_TYPE_DYNAMIC_NAMED_JSON_CLASS:
                // FIXME: DOES NOT YET distinguish explicitly between 'FIELD_TYPE_JSON_CLASS'. - 2014. 6.
                // 17./Park_Jun_Hong_(parkjunhong77@gmail.com)
                return marshallValue(rootClass, object, buildConfig, marshaller);

            case JSONMapper.FIELD_TYPE_GENERIC_TYPE_VARIABLE:
                return marshallValue(((GenericTypeVariable) rootClass).getTypeClass(), object, buildConfig, marshaller);

            case JSONMapper.FIELD_TYPE_ARRAY:

                Object[] valuesArray = ArrayUtils.toWrapperArray(object);// ConvertUtils.toArray(object);
                int len = valuesArray.length;

                JSONArray jsonArray = new JSONArray();
                Object arrElem = null;

                for (int i = 0; i < len; i++) {
                    // arrElem = marshall(valuesArray[i], buildConfig, marshaller, Arrays.copyOfRange(typeInfoList, 1,
                    // typeInfoList.length));
                    arrElem = marshall(valuesArray[i], buildConfig != null ? (BuildConfig) buildConfig.clone() : null, marshaller,
                            Arrays.copyOfRange(typeInfoList, 1, typeInfoList.length));

                    jsonArray.put(arrElem);
                }

                return jsonArray;

            case JSONMapper.FIELD_TYPE_COLLECTION:

                Collection valuesCol = (Collection) object;
                len = valuesCol.size();

                JSONArray colToArray = new JSONArray();

                Iterator itr = valuesCol.iterator();
                Object colElem = null;

                while (itr.hasNext()) {
                    // colElem = marshall(itr.next(), buildConfig, marshaller, Arrays.copyOfRange(typeInfoList, 1,
                    // typeInfoList.length));
                    colElem = marshall(itr.next(), buildConfig != null ? (BuildConfig) buildConfig.clone() : null, marshaller,
                            Arrays.copyOfRange(typeInfoList, 1, typeInfoList.length));

                    colToArray.put(colElem);
                }

                return colToArray;

            case JSONMapper.FIELD_TYPE_MAP:

                Map<Object, Object> valuesMap = (Map) object;

                JSONObject jsonObject = new JSONObject();
                Object mapValue = null;

                for (Entry<Object, Object> entry : valuesMap.entrySet()) {
                    // mapValue = marshall(entry.getValue(), buildConfig, marshaller, Arrays.copyOfRange(typeInfoList,
                    // 1, typeInfoList.length));
                    mapValue = marshall(entry.getValue(), buildConfig != null ? (BuildConfig) buildConfig.clone() : null, marshaller,
                            Arrays.copyOfRange(typeInfoList, 1, typeInfoList.length));

                    jsonObject.put(entry.getKey().toString(), mapValue);

                }

                return jsonObject;

            case JSONMapper.FIELD_TYPE_GENERIC_ARRAY:
                break;
            default:
                // Support a class does not implement IJSONString. - [Park_Jun_Hong_(parkjunhong77@gmail.com)]: 2014. 6.
                // 3.
                if (type < FIELD_TYPE_UNKNOWN) {
                    if (marshaller == null) {
                        marshaller = getMarshaller(object);
                    }

                    if (marshaller != null) {
                        return marshaller.marshall(object);
                    }
                }

                break;
        }

        return null;
    }

    /**
     * Create a object at leaf-node.
     * 
     * @param value
     * @param buildConfig
     * @param marshaller
     * 
     * @param typeInfoList
     * 
     * @return
     * @throws JSONException
     */
    @SuppressWarnings({ "unchecked" })
    private static Object marshallValue(Object rootClass, Object value, BuildConfig buildConfig, IMarshaller marshaller) throws JSONException {

        JSONObject jsonValue = null;
        IJSONString jsonModel = null;

        switch (JSONMapper.whatType(rootClass)) {
            case JSONMapper.FIELD_TYPE_PRIMITIVE:

                if (marshaller != null) {
                    value = marshaller.marshall(value);
                }

                return value == null ? null : ConvertUtils.toPrimitiveTypeValue((Class<?>) rootClass, value.toString());

            case JSONMapper.FIELD_TYPE_STRING:
                if (marshaller != null) {
                    value = marshaller.marshall(value);
                }

                return value != null ? value.toString() : null;

            case JSONMapper.FIELD_TYPE_ENUM:

                IEnumMarshaller enumMarshaller = getEnumMarshaller(rootClass);

                AssertUtils.assertNull(enumMarshaller, MarshallerNotFoundException.class);

                return enumMarshaller.marshall(value);

            case JSONMapper.FIELD_TYPE_DYNAMIC_NAMED_JSON_CLASS:
                // FIXME: DOES NOT YET distinguish explicitly between 'FIELD_TYPE_JSON_CLASS'. - 2014. 6.
                // 17./Park_Jun_Hong_(parkjunhong77@gmail.com)

                // jsonModel = (IJSONString) value;
                //
                // if (buildConfig != null) {
                // jsonValue = jsonModel.toJSONObject(buildConfig);
                // } else {
                // jsonValue = jsonModel.toJSONObject();
                // }
                //
                // return jsonValue;
            case JSONMapper.FIELD_TYPE_JSON_CLASS:

                jsonModel = (IJSONString) value;

                try {
                    if (buildConfig != null) {
                        jsonValue = jsonModel.toJSONObject(buildConfig);
                    } else {
                        jsonValue = jsonModel.toJSONObject();
                    }
                } catch (Exception e) {
                    throw new JSONMarshalledException(e);
                }

                return jsonValue;
            case JSONMapper.FIELD_TYPE_ARRAY:
                try {
                    Class<?> elemClass = ((Class) rootClass).getComponentType();

                    return JSONArrayFactory.toJSONArray(value, elemClass);

                } catch (Exception e) {
                    throw new JSONMarshalledException(e);
                }
            case JSONMapper.FIELD_TYPE_COLLECTION:
            case JSONMapper.FIELD_TYPE_UNKNOWN:
                break;
        }

        return null;
    }

    /**
     * Register a class Class that implements an interface.
     * 
     * @param type
     * @param implClass
     */
    public static void register(Class<?> type, Class<?> implClass) {
        try {
            registerInterfaceImplClass(type.getName(), implClass);
        } catch (Exception e) {
        }
    }

    /**
     * Register a class Class that implements an interface.
     * 
     * @param type
     * @param implClass
     * @throws ClassNotFoundException
     */
    public static void register(Class<?> type, String implClass) throws ClassNotFoundException {
        registerInterfaceImplClass(type.getName(), Class.forName(implClass));
    }

    /**
     * 
     * @deprecated DO NOT USE.
     * 
     * @param objectClass
     * @param fieldName
     * @param fieldClass
     * @throws NullPointerException
     *
     * @since 2015. 1. 6.
     */
    public static void register(Class<?> objectClass, String fieldName, Class<?> fieldClass) throws NullPointerException {
        AssertUtils.assertNulls(
                "Neither ObjectClass, FieldName and FieldCalss are null. objectClass: " + objectClass + ", fieldName: " + fieldName + ", fieldClass: " + fieldClass, objectClass,
                fieldName, fieldClass);

        synchronized (mutexFieldClasses) {
            // Map<String, Class<?>> classes = fieldClasses.get(objectClass);
            //
            // if (classes == null) {
            // classes = new HashMap<String, Class<?>>();
            //
            // fieldClasses.put(objectClass, classes);
            // }
            //
            // classes.put(fieldName, fieldClass);
        }
    }

    /**
     * 
     * <b>NOTE:</b> for {@link Enum}.<br>
     * 
     * Register a object that implements a {@link IEnumMarshaller}.
     * 
     * @param key
     * @param marshaller
     * 
     * @deprecated Instead of, use {@link #register(Object, IMarshaller)}
     */
    public static void register(Object key, IEnumMarshaller<?> marshaller) {
        AssertUtils.assertNulls("key: " + key + ", marshaller: " + marshaller, key, marshaller);

        synchronized (mutexEnumMarshaller) {
            enumMarshallers.put(key, marshaller);
        }
    }

    /**
     * <b>NOTE:</b> for Data-Marshaling.<br>
     * 
     * Register a object that implements a {@link IFieldMarshaller}.
     * 
     * @param key
     * @param marshaller
     * 
     * @deprecated Instead of, use {@link #register(Object, IMarshaller)}
     */
    public static void register(Object key, IFieldMarshaller<?> marshaller) {
        AssertUtils.assertNulls("key: " + key + ", marshaller: " + marshaller, key, marshaller);

        synchronized (mutexFieldMarshallers) {
            fieldMarshallers.put(key, marshaller);
        }
    }

    /**
     * 
     * <b>NOTE:</b> for Data-Marshaling.<br>
     * 
     * Register a object that implements a {@link IMarshaller}.
     * 
     * @param key
     * @param marshaller
     * 
     * @since 2014. 6. 3.
     * @since open.commons.json-1.0.7
     */
    public static void register(Object key, IMarshaller<?> marshaller) {
        AssertUtils.assertNulls("key: " + key + ", marshaller: " + marshaller, key, marshaller);

        if (marshaller instanceof IEnumMarshaller) {
            synchronized (mutexEnumMarshaller) {
                enumMarshallers.put(key, (IEnumMarshaller<?>) marshaller);
            }
        } else if (marshaller instanceof IFieldMarshaller) {
            synchronized (mutexFieldMarshallers) {
                fieldMarshallers.put(key, (IFieldMarshaller<?>) marshaller);
            }
        } else if (marshaller instanceof IMarshaller) {
            synchronized (mutexObjectMarshallers) {
                objectMarshallers.put(key, marshaller);
                unknownTypeMarshaller.put(key, unknownTypeMarshallerCounter.incrementAndGet());
            }
        } else {
            throw new IllegalArgumentException("'marshaller' MUST one of " + IFieldMarshaller.class + ", " + IEnumMarshaller.class + " or " + IMarshaller.class + ". Input: "
                    + marshaller.getClass());
        }
    }

    /**
     * Register a class Class that implements an interface.
     * 
     * @param type
     * @param implClass
     */
    public static void register(String type, Class<?> implClass) {
        try {
            registerInterfaceImplClass(type, implClass);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Register a class Class that implements an interface.
     * 
     * @param type
     * @param implClass
     */
    public static void register(String type, String implClass) {
        try {
            registerInterfaceImplClass(type, Class.forName(implClass));
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * 
     * @param obj
     * @param config
     * 
     * @since 2014. 4. 7.
     */
    public static <T extends IJSONString> void register(T obj, BuildConfig config) {
        AssertUtils.assertNulls(obj, config);

        synchronized (mutexBuildConfigs) {
            buildConfigs.put(obj, config);
        }
    }

    private static void registerInterfaceImplClass(String type, Class<?> implClass) {
        AssertUtils.assertNulls("type: " + type + ", implClass: " + implClass, type, implClass);

        synchronized (mutexContainerMapper) {
            containerMapper.put(type, implClass);
        }
    }

    /**
     * 
     * @param fieldName
     * 
     * @param value
     *            필드와 연결된 {@link JSONObject}
     * @param buildConfig
     *            구성하기 위해서 설정된 정보.
     * @param typeInfoList
     * 
     * @return
     * @throws JSONException
     * @throws IllegalAccessException
     * @throws InstantiationException
     * @throws CloneNotSupportedException
     *
     * @since 2014. 6. 18. - 연속된 Generic Type Variable 처리 버그 수정
     * @since 2014. 6. 17. - Support {@link #FIELD_TYPE_DYNAMIC_NAMED_JSON_CLASS}
     */
    @SuppressWarnings({ "unchecked" })
    public static Object unmarshall(String fieldName, Object value, BuildConfig buildConfig, Object... typeInfoList) throws JSONException, InstantiationException,
            IllegalAccessException, CloneNotSupportedException {

        Class<?> componentType = null;
        Class<?> implClass = null;

        JSONObject jsonObject = null;
        JSONArray jsonArray = null;

        Object rootClass = null;
        try {
            rootClass = typeInfoList[0];
        } catch (Exception e) {
            e.printStackTrace();
        }

        int type = FIELD_TYPE_UNKNOWN;
        switch (type = JSONMapper.whatType(rootClass)) {
            case JSONMapper.FIELD_TYPE_PRIMITIVE:
            case JSONMapper.FIELD_TYPE_STRING:
            case JSONMapper.FIELD_TYPE_ENUM:
            case JSONMapper.FIELD_TYPE_JSON_CLASS:
            case JSONMapper.FIELD_TYPE_DYNAMIC_NAMED_JSON_CLASS:
                // FIXME: DOES NOT YET distinguish explicitly between 'FIELD_TYPE_JSON_CLASS'. - 2014. 6.
                // 17./Park_Jun_Hong_(parkjunhong77@gmail.com)
                return unmarshallValue(rootClass, value, buildConfig);
            case JSONMapper.FIELD_TYPE_GENERIC_TYPE_VARIABLE:

                BuildConfig config = BuildConfig.config();

                GenericTypeVariable gTypeVar = (GenericTypeVariable) rootClass;
                List<GenericTypeVariable> gTypeVars = gTypeVar.getGenericTypeVars();
                TypeVariable[] typeVars = gTypeVar.getTypeClass().getTypeParameters();

                if (typeVars == null || gTypeVars == null || typeVars.length != gTypeVars.size()) {
                    throw new JSONUnmarshalledException(new IllegalArgumentException("fieldName: " + fieldName + ", value: " + value + ", buildConfig: " + buildConfig
                            + ", typeInfo: " + Arrays.toString(typeInfoList)));
                }

                for (int i = 0; i < typeVars.length; i++) {
                    // (start) [BUG-FIX]: 연속된 Generic Type Variable 처리를 위한 정보 추가 버그 수정 /
                    // Park_Jun_Hong_(parkjunhong77@gmail.com): 2014. 6. 18. 오후 3:47:25
                    // config.register(typeVars[i].getName(), gTypeVars.get(i));
                    config.addTypeVariable(gTypeVars.get(i));
                    // (end): 2014. 6. 18. 오후 3:47:25
                }

                return unmarshallValue(gTypeVar.getTypeClass(), value, config);// BuildConfig.config(typeVar.getGenericTypeVars()));

            case JSONMapper.FIELD_TYPE_ARRAY:
                // (start) [BUG-FIX]: null 이 올 수 있는 경우에 대한 처리 / Park_Jun_Hong_(parkjunhong77@gmail.com): 2014. 11. 4. 오후
                // 5:01:26
                if (value == null) {
                    return null;
                }
                // (end): 2014. 11. 4. 오후 5:01:26

                jsonArray = (JSONArray) value;
                int len = jsonArray.length();

                componentType = ((Class<?>) rootClass).getComponentType();
                Object array = Array.newInstance(componentType, len);

                Object arrayElem = null;
                for (int i = 0; i < len; i++) {
                    // arrayElem = unmarshall(fieldName, jsonArray.get(i), buildConfig, Arrays.copyOfRange(typeInfoList,
                    // 1, typeInfoList.length));
                    arrayElem = unmarshall(fieldName, jsonArray.get(i), buildConfig != null ? (BuildConfig) buildConfig.clone() : null,
                            Arrays.copyOfRange(typeInfoList, 1, typeInfoList.length));

                    if (arrayElem == null) {
                        continue;
                    }

                    Array.set(array, i, arrayElem);
                }

                return array;

            case JSONMapper.FIELD_TYPE_COLLECTION:

                implClass = getImplClass((Class<?>) rootClass);

                AssertUtils.assertNull(rootClass + " is not defined.", implClass, ImplClassNotFoundException.class);

                Collection col = (Collection) implClass.newInstance();

                // (start) [BUG-FIX]: null 이 올 수 있는 경우에 대한 처리 / Park_Jun_Hong_(parkjunhong77@gmail.com): 2014. 11. 4. 오후
                // 5:01:58
                if (value == null) {
                    return col;
                }
                // (end): 2014. 11. 4. 오후 5:01:58

                jsonArray = (JSONArray) value;
                len = jsonArray.length();

                Object colElem = null;
                for (int i = 0; i < len; i++) {
                    // colElem = unmarshall(fieldName, jsonArray.get(i), buildConfig, Arrays.copyOfRange(typeInfoList,
                    // 1, typeInfoList.length));
                    colElem = unmarshall(fieldName, jsonArray.get(i), buildConfig != null ? (BuildConfig) buildConfig.clone() : null,
                            Arrays.copyOfRange(typeInfoList, 1, typeInfoList.length));

                    if (colElem == null) {
                        continue;
                    }

                    col.add(colElem);

                }

                return col;

            case JSONMapper.FIELD_TYPE_MAP:

                implClass = getImplClass((Class<?>) rootClass);

                AssertUtils.assertNull(rootClass + " is not defined.", implClass, ImplClassNotFoundException.class);

                Map map = (Map) implClass.newInstance();

                // (start) [BUG-FIX]: null 이 올 수 있는 경우에 대한 처리 / Park_Jun_Hong_(parkjunhong77@gmail.com): 2014. 11. 4. 오후
                // 5:02:04
                if (value == null) {
                    return map;
                }
                // (end): 2014. 11. 4. 오후 5:02:04

                jsonObject = (JSONObject) value;

                Iterator<?> keysItr = jsonObject.keys();
                String key = null;
                Object mapElem = null;

                while (keysItr.hasNext()) {
                    key = (String) keysItr.next();

                    // mapElem = unmarshall(fieldName, jsonObject.get(key), buildConfig,
                    // Arrays.copyOfRange(typeInfoList, 1, typeInfoList.length));
                    mapElem = unmarshall(fieldName, jsonObject.get(key), buildConfig != null ? (BuildConfig) buildConfig.clone() : null,
                            Arrays.copyOfRange(typeInfoList, 1, typeInfoList.length));

                    map.put(key, mapElem);
                }

                return map;

            case JSONMapper.FIELD_TYPE_GENERIC_ARRAY:
                break;
            default:
                // Support a class does not implement IJSONString. - [Park_Jun_Hong_(parkjunhong77@gmail.com)]: 2014. 6.
                // 3.
                if (type < FIELD_TYPE_UNKNOWN) {
                    return value;
                }

                break;
        }

        return null;
    }

    /**
     * Create a object at leaf-node.
     * 
     * @param typeInfoList
     * @param value
     * @param buildConfig
     * @return
     * @throws JSONException
     * 
     * @since 2014. 6. 17. - Support {@link #FIELD_TYPE_DYNAMIC_NAMED_JSON_CLASS}.
     */
    @SuppressWarnings("unchecked")
    private static Object unmarshallValue(Object rootClass, Object value, BuildConfig buildConfig) throws JSONException {

        IJSONString jsonImpl = null;
        Class<?> clazz = null;

        if (value == null) {
            return null;
        }

        switch (JSONMapper.whatType(rootClass)) {
            case JSONMapper.FIELD_TYPE_PRIMITIVE:
                return ConvertUtils.toPrimitiveTypeValue((Class<?>) rootClass, value.toString());

            case JSONMapper.FIELD_TYPE_STRING:
                return value != null ? value.toString() : null;

            case JSONMapper.FIELD_TYPE_ENUM:
                IEnumMarshaller<?> enumMarshaller = getEnumMarshaller(rootClass);

                AssertUtils.assertNull(enumMarshaller, MarshallerNotFoundException.class);

                return enumMarshaller.unmarshall(value);

            case JSONMapper.FIELD_TYPE_DYNAMIC_NAMED_JSON_CLASS:
                // FIXME: DOES NOT YET distinguish explicitly between 'FIELD_TYPE_JSON_CLASS'. - 2014. 6.
                // 17./Park_Jun_Hong_(parkjunhong77@gmail.com)
                try {

                    // (start) [BUG-FIX]: 실제 데이터가 잘못된 경우에 대한 방어 코드 / Park_Jun_Hong_(parkjunhong77@gmail.com): 2014. 11.
                    // 11. 오후 6:07:21
                    if (value == null || !JSONObject.class.isAssignableFrom(value.getClass())) {
                        return null;
                    }
                    // (end): 2014. 11. 11. 오후 6:07:21

                    clazz = getImplClass((Class<?>) rootClass);

                    AssertUtils.assertNull(rootClass + " is not defined.", clazz, ImplClassNotFoundException.class);

                    jsonImpl = (IJSONString) clazz.newInstance();

                    if (buildConfig != null) {
                        jsonImpl.mature((JSONObject) value, buildConfig);
                    } else {
                        jsonImpl.mature((JSONObject) value);
                    }

                } catch (Exception e) {
                    throw new JSONUnmarshalledException("\n(object): " + rootClass + "\n(value): " + value, e);
                }
                return jsonImpl;

            case JSONMapper.FIELD_TYPE_JSON_CLASS:

                // (start) [BUG-FIX]: 실제 데이터가 잘못된 경우에 대한 방어 코드 / Park_Jun_Hong_(parkjunhong77@gmail.com): 2014. 11. 11.
                // 오후 6:07:21
                if (value == null || !JSONObject.class.isAssignableFrom(value.getClass())) {
                    return null;
                }
                // (end): 2014. 11. 11. 오후 6:07:21

                try {
                    clazz = getImplClass((Class<?>) rootClass);

                    AssertUtils.assertNull(rootClass + " is not defined.", clazz, ImplClassNotFoundException.class);

                    jsonImpl = (IJSONString) clazz.newInstance();

                    if (buildConfig != null) {
                        jsonImpl.mature((JSONObject) value, buildConfig);
                    } else {
                        jsonImpl.mature((JSONObject) value);
                    }

                } catch (Exception e) {
                    throw new JSONUnmarshalledException("\n(object): " + rootClass + "\n(value): " + value, e);
                }
                return jsonImpl;

            case JSONMapper.FIELD_TYPE_ARRAY:

                // (start) [BUG-FIX]: 실제 데이터가 잘못된 경우에 대한 방어 코드 / Park_Jun_Hong_(parkjunhong77@gmail.com): 2014. 11. 11.
                // 오후 6:07:21
                if (value == null || !JSONArray.class.isAssignableFrom(value.getClass())) {
                    return null;
                }
                // (end): 2014. 11. 11. 오후 6:07:21

                try {
                    clazz = ((Class) rootClass).getComponentType();

                    return JSONArrayFactory.createArray((JSONArray) value, clazz, buildConfig);

                } catch (Exception e) {
                    throw new JSONUnmarshalledException("\n(object): " + rootClass + "\n(value): " + value, e);
                }
            case JSONMapper.FIELD_TYPE_COLLECTION:
                // (start) [BUG-FIX]: 실제 데이터가 잘못된 경우에 대한 방어 코드 / Park_Jun_Hong_(parkjunhong77@gmail.com): 2014. 11. 11.
                // 오후 6:07:21
                if (value == null || !JSONArray.class.isAssignableFrom(value.getClass())) {
                    return null;
                }
                // (end): 2014. 11. 11. 오후 6:07:21
                try {
                    Iterator<GenericTypeVariable> itr = buildConfig.getGenericTypeVariables().iterator();
                    if (itr.hasNext()) {
                        GenericTypeVariable typeVar = itr.next();

                        return JSONArrayFactory.createCollection((JSONArray) value, (Class<Collection>) rootClass, typeVar.getTypeClass());
                    }

                } catch (Exception e) {
                    throw new JSONUnmarshalledException("\n(object): " + rootClass + "\n(value): " + value, e);
                }

                break;
            case JSONMapper.FIELD_TYPE_MAP:
                break;
            case JSONMapper.FIELD_TYPE_UNKNOWN:
                break;
            default:
                // FIXME: Support 'unknown type marshaller' - [Park_Jun_Hong_(parkjunhong77@gmail.com)]: 2014. 6. 4.
                break;
        }

        return null;
    }

    /**
     * 객체에 연결된 {@link BuildConfig} 객체를 제거한다.
     * 
     * @param obj
     * 
     * @return
     * 
     * @since 2014. 6. 18.
     */
    public static <T extends IJSONString> BuildConfig unregister(T obj) {
        AssertUtils.assertNull(obj);

        synchronized (mutexBuildConfigs) {

            BuildConfig config = buildConfigs.remove(obj);

            if (config != null) {
                config.destroy();
            }

            return config;
        }
    }

    /**
     * 
     * @param typeInfo
     * 
     * @return
     * 
     * @since 2014. 6. 4 - Support {@link IDynamicNamedJSONString}.
     *        "DOES NOT YET distinguish explicitly between 'FIELD_TYPE_JSON_CLASS'."
     * @since 2014. 6. 3 - Support a class does not implement {@link IJSONString}.
     * @since 2014. 5. 8.
     */
    public static int whatType(Object typeInfo) {

        if (typeInfo instanceof Class<?>) {
            Class<?> fieldClass = (Class<?>) typeInfo;

            if (ConvertUtils.getTypeConst(fieldClass) != ConvertUtils.TYPE_CONST_OBJECT) {
                return FIELD_TYPE_PRIMITIVE;
            }

            if (fieldClass.equals(String.class)) {
                return FIELD_TYPE_STRING;
            }

            if (fieldClass.isArray()) {
                return FIELD_TYPE_ARRAY;
            }

            if (fieldClass.isEnum()) {
                return FIELD_TYPE_ENUM;
            }

            // Support JSON string which has dynamic names. - [Park_Jun_Hong_(parkjunhong77@gmail.com)]: 2014. 6. 4.
            // IMPORTANT: Methods calls this method MUST support 'FIELD_TYPE_DYNAMIC_NAMED_JSON_CLASS'.
            if (IDynamicNamedJSONString.class.isAssignableFrom(fieldClass)) {
                return FIELD_TYPE_DYNAMIC_NAMED_JSON_CLASS;
            }

            if (IJSONString.class.isAssignableFrom(fieldClass)) {
                return FIELD_TYPE_JSON_CLASS;
            }

            if (Collection.class.isAssignableFrom(fieldClass)) {
                return FIELD_TYPE_COLLECTION;
            }

            if (Map.class.isAssignableFrom(fieldClass)) {
                return FIELD_TYPE_MAP;
            }

            Integer fieldType = unknownTypeMarshaller.get(typeInfo);

            if (fieldType != null && objectMarshallers.containsKey(typeInfo)) {
                return fieldType.intValue();
            }

        } else if (typeInfo instanceof GenericArrayType) {
            return FIELD_TYPE_GENERIC_ARRAY;
        } else if (typeInfo instanceof GenericTypeVariable) {
            return FIELD_TYPE_GENERIC_TYPE_VARIABLE;
        } else {
            Integer type = unknownTypeMarshaller.get(typeInfo);

            return type != null && objectMarshallers.containsKey(typeInfo) ? type.intValue() : FIELD_TYPE_UNKNOWN;
        }

        return FIELD_TYPE_UNKNOWN;
    }
}
