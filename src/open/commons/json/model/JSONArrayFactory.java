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
 * Date  : 2014. 4. 15. 오후 9:19:38
 *
 * Author: Park_Jun_Hong_(fafanmama_at_naver_com)
 * 
 */

package open.commons.json.model;

import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import open.commons.json.IJSONString;
import open.commons.reflect.GenericTypeVariable;

import code.org.codehaus.jettison.json.JSONArray;
import code.org.codehaus.jettison.json.JSONException;
import code.org.codehaus.jettison.json.JSONObject;

/**
 * 
 * @since 2014. 4. 15.
 * @author Park_Jun_Hong_(fafanmama_at_naver_com)
 */
@SuppressWarnings("unchecked")
public class JSONArrayFactory {

    // protect to create a new instance.
    private JSONArrayFactory() {
    }

    /**
     * Return a <b><code>boolean</code></b> array.
     * 
     * @param jsonArray
     *            a instance of a class supports JSONArray
     * @return
     * @throws JSONException
     * 
     * @since 2014. 4. 15.
     */
    public static final boolean[] booleanArray(JSONArray jsonArray) throws JSONException {

        int length = jsonArray.length();

        boolean[] array = new boolean[length];

        for (int i = 0; i < length; i++) {
            array[i] = jsonArray.getBoolean(i);
        }

        return array;
    }

    /**
     * Return a <b><code>boolean</code></b> array.
     * 
     * @param jsonArrayStr
     *            a string representation of JSONArray
     * @return
     * @throws JSONException
     * 
     * @since 2014. 4. 15.
     * 
     * @see #booleanArray(JSONArray)
     */
    public static final boolean[] booleanArray(String jsonArrayStr) throws JSONException {
        return booleanArray(new JSONArray(jsonArrayStr));
    }

    /**
     * Return a <b><code>byte</code></b> array.
     * 
     * @param jsonArray
     *            the instance of a class supports JSONArray
     * @return
     * @throws JSONException
     * 
     * @since 2014. 4. 15.
     */
    public static final byte[] byteArray(JSONArray jsonArray) throws JSONException {

        int length = jsonArray.length();

        byte[] array = new byte[length];

        for (int i = 0; i < length; i++) {
            array[i] = (byte) jsonArray.getInt(i);
        }

        return array;
    }

    /**
     * Return a <b><code>byte</code></b> array.
     * 
     * @param jsonArrayStr
     *            a string representation of JSONArray
     * @return
     * @throws JSONException
     * 
     * @since 2014. 4. 15.
     * @see #byteArray(JSONArray)
     */
    public static final byte[] byteArray(String jsonArrayStr) throws JSONException {
        return byteArray(new JSONArray(jsonArrayStr));
    }

    /**
     * Return a <b><code>char</code></b> array.
     * 
     * @param jsonArray
     *            the instance of a class supports JSONArray
     * @return
     * @throws JSONException
     * 
     * @since 2014. 4. 15.
     */
    public static final char[] charArray(JSONArray jsonArray) throws JSONException {

        int length = jsonArray.length();

        char[] array = new char[length];

        for (int i = 0; i < length; i++) {
            array[i] = jsonArray.getString(i).charAt(0);
        }

        return array;
    }

    /**
     * Return a <b><code>char</code></b> array.
     * 
     * @param jsonArrayStr
     *            a string representation of JSONArray
     * @return
     * @throws JSONException
     * 
     * @since 2014. 4. 15.
     * @see #charArray(JSONArray)
     */
    public static final char[] charArray(String jsonArrayStr) throws JSONException {
        return charArray(new JSONArray(jsonArrayStr));
    }

    /**
     * Return an array.
     * 
     * @param T
     *            primitive types or {@link String} or the type of a class implements {@link IJSONString}
     * @param jsonArray
     *            the instance of a class supports JSONArray
     * @param elemType
     *            the type of data contained in JSONArray
     * 
     * @return
     * @throws JSONException
     * 
     * @since 2014. 6. 4 - Only forward parameters to {@link #createArray(JSONArray, Class, BuildConfig)}.
     * @since 2014. 4. 15.
     * 
     * @see #createArray(JSONArray, Class, BuildConfig)
     */
    public static final <T> Object createArray(JSONArray jsonArray, Class<T> elemType) throws JSONException {
        return createArray(jsonArray, elemType, BuildConfig.config());
    }

    /**
     * Return an array.
     * 
     * @param T
     *            primitive types or {@link String} or the type of a class implements {@link IJSONString}
     * @param jsonArray
     *            the instance of a class supports JSONArray
     * @param elemType
     *            the type of data contained in JSONArray
     * @param buildConfig
     * 
     * @return
     * @throws JSONException
     * 
     * @since 2014. 6. 4
     */
    public static final <T> Object createArray(JSONArray jsonArray, Class<T> elemType, BuildConfig buildConfig) throws JSONException {

        Object array = null;
        switch (JSONMapper.whatType(elemType)) {
            case JSONMapper.FIELD_TYPE_PRIMITIVE:
                array = toPrimitiveArray(jsonArray, elemType);
                break;
            case JSONMapper.FIELD_TYPE_STRING:
                array = (T[]) createStringArray(jsonArray);
                break;
            case JSONMapper.FIELD_TYPE_JSON_CLASS:
                array = createObjectArray(jsonArray, elemType, buildConfig);
                break;
            default:
                break;
        }

        return array;
    }

    /**
     * Return an array.
     * 
     * @param T
     *            primitive types or {@link String} or the type of a class implements {@link IJSONString}
     * @param jsonArrayString
     *            a string representation of JSONArray
     * @param elemType
     *            the type of data contained in JSONArray
     * @param buildConfig
     * 
     * @return
     * @throws JSONException
     * 
     * @since 2014. 6. 4 - Only forward parameters to {@link #createArray(String, Class, BuildConfig)}.
     * @since 2014. 4. 15.
     * 
     * @see #createArray(JSONArray, Class, BuildConfig)
     */
    public static final <T> Object createArray(String jsonArrayString, Class<T> elemType) throws JSONException {
        return createArray(new JSONArray(jsonArrayString), elemType, BuildConfig.config());
    }

    /**
     * Return an array.
     * 
     * @param T
     *            primitive types or {@link String} or the type of a class implements {@link IJSONString}
     * @param jsonArrayString
     *            a string representation of JSONArray
     * @param elemType
     *            the type of data contained in JSONArray
     * @param buildConfig
     * 
     * @return
     * @throws JSONException
     * 
     * @since 2014. 6. 4
     * 
     * @see #createArray(JSONArray, Class, BuildConfig)
     */
    public static final <T> Object createArray(String jsonArrayString, Class<T> elemType, BuildConfig buildConfig) throws JSONException {
        return createArray(new JSONArray(jsonArrayString), elemType, buildConfig);
    }

    /**
     * Return an instance of a class implements {@link Collection}.
     * 
     * @param T
     *            primitive types or {@link String} or the type of a class implements {@link IJSONString}.
     * @param jsonArray
     *            an instance of a class supports JSONArray.
     * @param colType
     *            the type of a class implements {@link Collection}.
     * @param elemType
     *            the type of data contained in JSONArray.
     * 
     * @return
     * @throws JSONException
     * 
     * 
     * @since 2014. 6. 4. - Only forward parameters to {@link #createCollection(JSONArray, Class, Class, BuildConfig)}.
     * @since 2014. 4. 15.
     */
    public static final <C extends Collection<T>, T> Collection<T> createCollection(JSONArray jsonArray, Class<C> colType, Class<T> elemType) throws JSONException {
        return createCollection(jsonArray, colType, elemType, BuildConfig.config());
    }

    /**
     * Return an instance of a class implements {@link Collection}.
     * 
     * @param T
     *            primitive types or {@link String} or the type of a class implements {@link IJSONString}.
     * @param jsonArray
     *            an instance of a class supports JSONArray.
     * @param colType
     *            the type of a class implements {@link Collection}.
     * @param elemType
     *            the type of data contained in JSONArray.
     * @param buildConfig
     * 
     * @return
     * @throws JSONException
     * 
     * @since 2014. 6. 4.
     */
    public static final <C extends Collection<T>, T> Collection<T> createCollection(JSONArray jsonArray, Class<C> colType, Class<T> elemType, BuildConfig buildConfig)
            throws JSONException {
        Collection<T> col = null;
        switch (JSONMapper.whatType(elemType)) {
            case JSONMapper.FIELD_TYPE_PRIMITIVE:
                col = createPrimitiveCollection(jsonArray, colType, elemType);
                break;
            case JSONMapper.FIELD_TYPE_STRING:
                col = createStringCollection(jsonArray, colType);
                break;
            case JSONMapper.FIELD_TYPE_JSON_CLASS:
                col = createObjectCollection(jsonArray, colType, elemType, buildConfig);
                break;
            default:
                break;
        }

        return col;
    }

    /**
     * Return an instance of a class implements {@link Collection}.
     * 
     * @param T
     *            primitive types or {@link String} or the type of a class implements {@link IJSONString}.
     * @param jsonArrayString
     *            a string representation of JSONArray.
     * @param colType
     *            the type of a class implements {@link Collection}.
     * @param elemType
     *            the type of data contained in JSONArray.
     * @param buildConfig
     * 
     * @return
     * @throws JSONException
     * 
     * @since 2014. 6. 4. - Only forward parameters to {@link #createCollection(String, Class, Class, BuildConfig)}
     * @since 2014. 4. 15.
     * 
     * @see #createCollection(JSONArray, Class, Class, BuildConfig)
     */
    public static final <C extends Collection<T>, T> Collection<T> createCollection(String jsonArrayString, Class<C> colType, Class<T> elemType) throws JSONException {
        return createCollection(jsonArrayString, colType, elemType, BuildConfig.config());
    }

    /**
     * Return an instance of a class implements {@link Collection}.
     * 
     * @param T
     *            primitive types or {@link String} or the type of a class implements {@link IJSONString}.
     * @param jsonArrayString
     *            a string representation of JSONArray.
     * @param colType
     *            the type of a class implements {@link Collection}.
     * @param elemType
     *            the type of data contained in JSONArray.
     * @param buildConfig
     * 
     * @return
     * @throws JSONException
     * 
     * @since 2014. 6. 4.
     * 
     */
    public static final <C extends Collection<T>, T> Collection<T> createCollection(String jsonArrayString, Class<C> colType, Class<T> elemType, BuildConfig buildConfig)
            throws JSONException {
        return createCollection(new JSONArray(jsonArrayString), colType, elemType, buildConfig);
    }

    /**
     * Return an array of a object.
     * 
     * @param jsonArray
     *            an instance of a class supports JSONArray
     * @param elemType
     *            the type of data contained in JSONArray
     * @param T
     *            the type of a class implements {@link IJSONString}.
     * 
     * @return
     * @throws JSONException
     * 
     * @since 2014. 6. 4 - Only forward parameters to {@link #createObjectArray(JSONArray, Class, BuildConfig)}
     * @since 2014. 4. 15.
     */
    public static final <T> T[] createObjectArray(JSONArray jsonArray, Class<T> elemType) throws JSONException {
        return createObjectArray(jsonArray, elemType, BuildConfig.config());
    }

    /**
     * Return an array of a object.
     * 
     * @param jsonArray
     *            an instance of a class supports JSONArray
     * @param elemType
     *            the type of data contained in JSONArray
     * @param buildConfig
     * @param T
     *            the type of a class implements {@link IJSONString}.
     * 
     * @return
     * @throws JSONException
     * 
     * @since 2014. 6. 4
     */
    public static final <T> T[] createObjectArray(JSONArray jsonArray, Class<T> elemType, BuildConfig buildConfig) throws JSONException {

        int length = jsonArray.length();

        T[] array = (T[]) Array.newInstance(elemType, length);

        T elem = null;
        for (int i = 0; i < length; i++) {
            try {
                elem = elemType.newInstance();
                ((IJSONString) elem).mature(jsonArray.getJSONObject(i), buildConfig);
                array[i] = elem;

            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }

        return array;
    }

    /**
     * Return an array of a object.
     * 
     * @param T
     *            the type of a class implements {@link IJSONString}.
     * @param jsonArrayStr
     *            a string representation of JSONArray
     * @param elemType
     *            the type of data contained in JSONArray
     * @return
     * @throws JSONException
     * 
     * @since 2014. 6. 4 - Only forward parameters to {@link #createObjectArray(String, Class, BuildConfig)}
     * @since 2014. 4. 15.
     * 
     * @see #createObjectArray(String, Class, BuildConfig)
     */
    public static final <T> T[] createObjectArray(String jsonArrayStr, Class<T> elemType) throws JSONException {
        return createObjectArray(jsonArrayStr, elemType, BuildConfig.config());
    }

    /**
     * REturn an array of a object
     * 
     * @param T
     *            the type of a class implements {@link IJSONString}.
     * @param jsonArrayStr
     *            a string representation of JSONArray
     * @param elemType
     *            the type of data contained in JSONArray
     * @param buildConfig
     * @return
     * @throws JSONException
     * 
     * @since 2014. 6. 4.
     */
    public static final <T> T[] createObjectArray(String jsonArrayStr, Class<T> elemType, BuildConfig buildConfig) throws JSONException {
        return createObjectArray(new JSONArray(jsonArrayStr), elemType, buildConfig);
    }

    /**
     * Return a instance of a class implements {@link Collection}.
     * 
     * @param T
     *            the type of a class implements {@link IJSONString}.
     * @param jsonArray
     *            an instance of a class implements JSONarray.
     * @param colType
     *            the type of a class implements {@link Collection}.
     * @param elemType
     *            the type of a class contained in JSONArray.
     * 
     * @return
     * @throws JSONException
     * 
     * @since 2014. 6. 4 - Only forward parameters to
     *        {@link #createObjectCollection(JSONArray, Class, Class, BuildConfig)}
     * @since 2014. 4. 15.
     * 
     * @see #createObjectCollection(JSONArray, Class, Class, BuildConfig)
     */
    public static final <C extends Collection<T>, T> Collection<T> createObjectCollection(JSONArray jsonArray, Class<C> colType, Class<T> elemType) throws JSONException {
        return createObjectCollection(jsonArray, colType, elemType, BuildConfig.config());
    }

    /**
     * Return a instance of a class implements {@link Collection}.
     * 
     * @param T
     *            the type of a class implements {@link IJSONString}.
     * @param jsonArray
     *            an instance of a class implements JSONarray.
     * @param colType
     *            the type of a class implements {@link Collection}.
     * @param elemType
     *            the type of a class contained in JSONArray.
     * @param buildConfig
     * 
     * @return
     * @throws JSONException
     * 
     * @since 2014. 6. 4
     * 
     * @see JSONMapper#getImplClass(Class)
     */
    public static final <C extends Collection<T>, T> Collection<T> createObjectCollection(JSONArray jsonArray, Class<C> colType, Class<T> elemType, BuildConfig buildConfig)
            throws JSONException {

        Class<?> colClass = JSONMapper.getImplClass(colType);
        Collection<T> col = null;

        try {
            col = (Collection<T>) colClass.newInstance();

            int length = jsonArray.length();
            T elem = null;
            for (int i = 0; i < length; i++) {
                try {
                    elem = elemType.newInstance();
                    ((IJSONString) elem).mature(jsonArray.getJSONObject(i), buildConfig);
                    col.add(elem);

                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return col;
    }

    /**
     * Return a instance of a class implements {@link Collection}.
     * 
     * @param T
     *            the type of a class implements {@link IJSONString}.
     * @param colType
     *            the type of a class implements {@link Collection}.
     * @param elemType
     *            the type of a class contained in JSONArray.
     * @param jsonArray
     *            a string representation on JSONArray.
     * 
     * @return
     * @throws JSONException
     * 
     * @since 2014. 6. 4 - Only forward parameters to {@link #createObjectCollection(String, Class, Class, BuildConfig)}
     * @since 2014. 4. 15.
     * 
     * @see #createObjectCollection(String, Class, Class, BuildConfig)
     */
    public static final <C extends Collection<T>, T> Collection<T> createObjectCollection(String jsonArrayStr, Class<C> colType, Class<T> elemType) throws JSONException {
        return createObjectCollection(jsonArrayStr, colType, elemType, BuildConfig.config());
    }

    /**
     * Return a instance of a class implements {@link Collection}.
     * 
     * @param T
     *            the type of a class implements {@link IJSONString}.
     * @param jsonArray
     *            a string representation on JSONArray.
     * @param colType
     *            the type of a class implements {@link Collection}.
     * @param elemType
     *            the type of a class contained in JSONArray.
     * @param buildConfig
     * 
     * 
     * @return
     * @throws JSONException
     * 
     * @since 2014. 6. 4
     * 
     * @see #createObjectCollection(JSONArray, Class, Class, BuildConfig)
     */
    public static final <C extends Collection<T>, T> Collection<T> createObjectCollection(String jsonArrayStr, Class<C> colType, Class<T> elemType, BuildConfig buildConfig)
            throws JSONException {
        return createObjectCollection(new JSONArray(jsonArrayStr), colType, elemType, buildConfig);
    }

    /**
     * Return an array of one of primitive types.
     * 
     * @param T
     *            primitive types.
     * 
     * @param jsonArrayStr
     *            a string representation of JSONArray.
     * @param elemType
     *            the type of data contained in JSONArray.
     * @return
     * @throws JSONException
     * 
     * @since 2014. 4. 15.
     * @see #toPrimitiveArray(JSONArray, Class)
     */
    public static final <T> Object createPrimitiveArray(String jsonArrayStr, Class<T> elemType) throws JSONException {
        return toPrimitiveArray(new JSONArray(jsonArrayStr), elemType);
    }

    /**
     * Return an instance of a class implements {@link Collection}.
     * 
     * @param T
     *            primitive types.
     * 
     * @param jsonArray
     *            an instance of a class supports JSONArray.
     * @param colType
     *            the type of a class implements {@link Collection}.
     * @param elemType
     *            the type of data contained in JSONArray.
     * @return
     * @throws JSONException
     * 
     * @since 2014. 4. 15.
     */
    public static final <C extends Collection<T>, T> Collection<T> createPrimitiveCollection(JSONArray jsonArray, Class<C> colType, Class<T> elemType) throws JSONException {
        Class<?> colClass = JSONMapper.getImplClass(colType);
        Collection<T> col = null;

        try {
            col = (Collection<T>) colClass.newInstance();

            int length = jsonArray.length();
            for (int i = 0; i < length; i++) {
                col.add((T) jsonArray.get(i));
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return col;
    }

    /**
     * Return an instance of a class implements {@link Collection}.
     * 
     * @param T
     *            primitive types.
     * 
     * @param jsonArrayStr
     *            a string representation of JSONArray.
     * @param colType
     *            the type of a class implements {@link Collection}.
     * @param elemType
     *            the type of data contained in JSONArray.
     * @return
     * @throws JSONException
     * 
     * @since 2014. 4. 15.
     * @see #createPrimitiveCollection(JSONArray, Class, Class)
     */
    public static final <C extends Collection<T>, T> Collection<T> createPrimitiveCollection(String jsonArrayStr, Class<C> colType, Class<T> elemType) throws JSONException {
        return createPrimitiveCollection(new JSONArray(jsonArrayStr), colType, elemType);
    }

    /**
     * Return a string array.
     * 
     * @param jsonArray
     *            the instance of a class supports JSONArray.
     * @return
     * @throws JSONException
     * 
     * @since 2014. 4. 15.
     */
    public static final String[] createStringArray(JSONArray jsonArray) throws JSONException {
        int length = jsonArray.length();

        String[] array = (String[]) Array.newInstance(String.class, length);

        for (int i = 0; i < length; i++) {
            array[i] = jsonArray.getString(i);
        }

        return array;
    }

    /**
     * Return a string array.
     * 
     * @param jsonArrayStr
     *            a string representation of JSONArray.
     * @return
     * @throws JSONException
     * 
     * @since 2014. 4. 15.
     * @see #createStringArray(JSONArray)
     */
    public static final String[] createStringArray(String jsonArrayStr) throws JSONException {
        return createStringArray(new JSONArray(jsonArrayStr));
    }

    /**
     * Return an instance of a class implements {@link Collection}.
     * 
     * @param T
     *            {@link String}.
     * @param jsonArray
     *            an instance of a class supports JSONArray.
     * @param colType
     *            the type of a class implements {@link Collection}.
     * @return
     * @throws JSONException
     * 
     * @since 2014. 4. 15.
     */
    public static final <C extends Collection<T>, T> Collection<T> createStringCollection(JSONArray jsonArray, Class<C> colType) throws JSONException {
        Class<?> colClass = JSONMapper.getImplClass(colType);
        Collection<T> col = null;

        try {
            col = (Collection<T>) colClass.newInstance();

            int length = jsonArray.length();
            for (int i = 0; i < length; i++) {
                col.add((T) jsonArray.getString(i));
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return col;
    }

    /**
     * Return an instance of a class implements {@link Collection}.
     * 
     * @param T
     *            {@link String}.
     * @param jsonArrayStr
     *            a string representation of JSONArray.
     * @param colType
     *            the type of a class implements {@link Collection}.
     * @return
     * @throws JSONException
     * 
     * @since 2014. 4. 15.
     * @see #createStringCollection(JSONArray, Class)
     */
    public static final <C extends Collection<T>, T> Collection<T> createStringCollection(String jsonArrayStr, Class<C> colType) throws JSONException {
        return createStringCollection(new JSONArray(jsonArrayStr), colType);
    }

    /**
     * Return a <b><code>double</code></b> array.
     * 
     * @param jsonArray
     *            a instance of a class supports JSONArray
     * @return
     * @throws JSONException
     * 
     * @since 2014. 4. 15.
     */
    public static final double[] doubleArray(JSONArray jsonArray) throws JSONException {

        int length = jsonArray.length();

        double[] array = new double[length];

        for (int i = 0; i < length; i++) {
            array[i] = jsonArray.getDouble(i);
        }

        return array;
    }

    /**
     * Return a <b><code>double</code></b> array.
     * 
     * @param jsonArrayStr
     *            a instance of a class supports JSONArray
     * @return
     * @throws JSONException
     * 
     * @since 2014. 4. 15.
     * @see #doubleArray(JSONArray)
     */
    public static final double[] doubleArray(String jsonArrayStr) throws JSONException {
        return doubleArray(new JSONArray(jsonArrayStr));
    }

    /**
     * Return a <b><code>float</code></b> array.
     * 
     * @param jsonArray
     *            a instance of a class supports JSONArray
     * @return
     * @throws JSONException
     * 
     * @since 2014. 4. 15.
     */
    public static final float[] floatArray(JSONArray jsonArray) throws JSONException {

        int length = jsonArray.length();

        float[] array = new float[length];

        for (int i = 0; i < length; i++) {
            array[i] = (float) jsonArray.getDouble(i);
        }

        return array;
    }

    /**
     * Return a <b><code>float</code></b> array.
     * 
     * @param jsonArrayStr
     *            a string representation of JSONArray.
     * @return
     * @throws JSONException
     * 
     * @since 2014. 4. 15.
     * @see #floatArray(JSONArray)
     */
    public static final float[] floatArray(String jsonArrayStr) throws JSONException {
        return floatArray(new JSONArray(jsonArrayStr));
    }

    /**
     * Return an <b><code>int</code></b> array.
     * 
     * @param jsonArray
     *            a instance of a class supports JSONArray
     * @return
     * @throws JSONException
     * 
     * @since 2014. 4. 15.
     */
    public static final int[] intArray(JSONArray jsonArray) throws JSONException {

        int length = jsonArray.length();

        int[] array = new int[length];

        for (int i = 0; i < length; i++) {
            array[i] = jsonArray.getInt(i);
        }

        return array;
    }

    /**
     * Return an <b><code>int</code></b> array.
     * 
     * @param jsonArray
     *            a instance of a class supports JSONArray
     * @return
     * @throws JSONException
     * 
     * @since 2014. 4. 15.
     * @see #intArray(JSONArray)
     */
    public static final int[] intArray(String jsonArrayStr) throws JSONException {
        return intArray(new JSONArray(jsonArrayStr));
    }

    /**
     * Return a <b><code>long</code></b> array.
     * 
     * @param jsonArray
     *            a instance of a class supports JSONArray
     * @return
     * @throws JSONException
     * 
     * @since 2014. 4. 15.
     */
    public static final long[] longArray(JSONArray jsonArray) throws JSONException {

        int length = jsonArray.length();

        long[] array = new long[length];

        for (int i = 0; i < length; i++) {
            array[i] = jsonArray.getLong(i);
        }

        return array;
    }

    /**
     * Return a <b><code>long</code></b> array.
     * 
     * @param jsonArray
     *            a instance of a class supports JSONArray
     * @return
     * @throws JSONException
     * 
     * @since 2014. 4. 15.
     * @see #longArray(JSONArray)
     */
    public static final long[] longArray(String jsonArrayStr) throws JSONException {
        return longArray(new JSONArray(jsonArrayStr));
    }

    /**
     * Return a instance of a class supports JSONArray.
     * 
     * @param values
     * @return
     * @throws JSONException
     * 
     * @since 2014. 5. 8.
     */
    public static final JSONArray toJSONArray(boolean[] values) throws JSONException {
        JSONArray array = new JSONArray();

        for (boolean value : values) {
            array.put(value);
        }

        return array;
    }

    /**
     * Return a instance of a class supports JSONArray.
     * 
     * @param values
     * @return
     * @throws JSONException
     * 
     * @since 2014. 5. 8.
     */
    public static final JSONArray toJSONArray(byte[] values) throws JSONException {
        JSONArray array = new JSONArray();

        for (byte value : values) {
            array.put(value);
        }

        return array;
    }

    /**
     * Return a instance of a class supports JSONArray.
     * 
     * @param values
     * @return
     * @throws JSONException
     * 
     * @since 2014. 5. 8.
     */
    public static final JSONArray toJSONArray(char[] values) throws JSONException {
        JSONArray array = new JSONArray();

        for (char value : values) {
            array.put(value);
        }
        return array;
    }

    /**
     * 
     * @param values
     * @param elemType
     * @return
     * @throws JSONException
     * 
     * @since 2014. 7. 7. - <b><code>values.toArray()</code></b> -->> <b>
     *        <code>values.toArray(new IJSONString[]{})</code></b>로 변경.
     * @since 2014. 5. 8.
     */
    public static final <T extends Serializable & IJSONString> JSONArray toJSONArray(Collection<T> values, Class<T> elemType) throws JSONException {
        return toJSONArray(values.toArray(new IJSONString[] {}), elemType);
    }

    /**
     * 
     * @param values
     * @param elemType
     * @param ordered
     * @return
     * @throws JSONException
     *
     * @since 2016. 5. 26.
     * @since 1.6.0
     */
    public static final <T extends Serializable & IJSONString> JSONArray toJSONArray(Collection<T> values, Class<T> elemType, boolean ordered) throws JSONException {
        return toJSONArray(values.toArray(new IJSONString[] {}), elemType, ordered);
    }

    /**
     * 
     * @param values
     * @param elemType
     * @param ordered
     * @param paramTypes
     * @return
     * @throws JSONException
     *
     * @since 2016. 5. 26.
     * @since 1.6.0
     */
    public static final <T extends Serializable & IJSONString> JSONArray toJSONArray(Collection<T> values, Class<T> elemType, boolean ordered, GenericTypeVariable... paramTypes)
            throws JSONException {
        return toJSONArray(values.toArray(new IJSONString[] {}), elemType, ordered, paramTypes);
    }

    /**
     * 
     * @param values
     * @param elemType
     * @return
     * @throws JSONException
     * 
     * @since 2014. 7. 7. - <b><code>values.toArray()</code></b> -->> <b>
     *        <code>values.toArray(new IJSONString[]{})</code></b>로 변경.
     * @since 2014. 5. 8.
     */
    public static final <T extends Serializable & IJSONString> JSONArray toJSONArray(Collection<T> values, Class<T> elemType, Class<?> paramType) throws JSONException {
        return toJSONArray(values.toArray(new IJSONString[] {}), elemType, paramType);
    }

    /**
     * 
     * @param values
     * @param elemType
     * @param paramType
     * @param ordered
     * @return
     * @throws JSONException
     *
     * @since 2016. 5. 26.
     * @since 1.6.0
     */
    public static final <T extends Serializable & IJSONString> JSONArray toJSONArray(Collection<T> values, Class<T> elemType, Class<?> paramType, boolean ordered)
            throws JSONException {
        return toJSONArray(values.toArray(new IJSONString[] {}), elemType, ordered, GenericTypeVariable.getParamType(paramType));
    }

    /**
     * 
     * @param values
     * @param elemType
     * @return
     * @throws JSONException
     * 
     * @since 2014. 7. 7. - <b><code>values.toArray()</code></b> -->> <b>
     *        <code>values.toArray(new IJSONString[]{})</code></b>로 변경.
     * @since 2014. 5. 8.
     */
    public static final <T extends Serializable & IJSONString> JSONArray toJSONArray(Collection<T> values, Class<T> elemType, GenericTypeVariable... paramTypes)
            throws JSONException {
        return toJSONArray(values.toArray(new IJSONString[] {}), elemType, paramTypes);
    }

    /**
     * 
     * @param values
     * @param elemType
     * @return
     * @throws JSONException
     * 
     * @since 2014. 7. 7. - <b><code>values.toArray()</code></b> -->> <b>
     *        <code>values.toArray(new IJSONString[]{})</code></b>로 변경.
     * @since 2014. 5. 8.
     */
    public static final <T extends Serializable & IJSONString> JSONArray toJSONArray(Collection<T> values, Class<T> elemType, List<Class<?>> paramTypes) throws JSONException {
        return toJSONArray(values.toArray(new IJSONString[] {}), elemType, paramTypes);
    }

    /**
     * 
     * @param values
     * @param elemType
     * @param paramTypes
     * @param ordered
     * @return
     * @throws JSONException
     *
     * @since 2016. 5. 26.
     * @since 1.6.0
     */
    public static final <T extends Serializable & IJSONString> JSONArray toJSONArray(Collection<T> values, Class<T> elemType, List<Class<?>> paramTypes, boolean ordered)
            throws JSONException {
        return toJSONArray(values.toArray(new IJSONString[] {}), elemType, paramTypes, ordered);
    }

    /**
     * Return a instance of a class supports JSONArray.
     * 
     * @param values
     * @return
     * @throws JSONException
     * 
     * @since 2014. 5. 8.
     */
    public static final JSONArray toJSONArray(double[] values) throws JSONException {
        JSONArray array = new JSONArray();

        for (double value : values) {
            array.put(value);
        }
        return array;
    }

    /**
     * Return a instance of a class supports JSONArray.
     * 
     * @param values
     * @return
     * @throws JSONException
     * 
     * @since 2014. 5. 8.
     */
    public static final JSONArray toJSONArray(float[] values) throws JSONException {
        JSONArray array = new JSONArray();

        for (float value : values) {
            array.put(value);
        }
        return array;
    }

    /**
     * Return a instance of a class supports JSONArray.
     * 
     * @param values
     * @return
     * @throws JSONException
     * 
     * @since 2014. 5. 8.
     */
    public static final JSONArray toJSONArray(int[] values) throws JSONException {
        JSONArray array = new JSONArray();

        for (int value : values) {
            array.put(value);
        }
        return array;
    }

    /**
     * Return a instance of a class supports JSONArray.
     * 
     * @param values
     * @return
     * @throws JSONException
     * 
     * @since 2014. 5. 8.
     */
    public static final JSONArray toJSONArray(long[] values) throws JSONException {
        JSONArray array = new JSONArray();

        for (long value : values) {
            array.put(value);
        }
        return array;
    }

    /**
     * Return a instance of a class supports JSONArray.
     * 
     * @param values
     * @return
     * @throws JSONException
     * 
     * @since 2014. 5. 8.
     */
    public static final <T> JSONArray toJSONArray(Object values, Class<T> elemType) throws JSONException {
        return toJSONArray(values, elemType, false);
    }

    /**
     * Return a instance of a class supports JSONArray.
     * 
     * @param values
     * @param ordered
     *            순서 적용
     * @return
     * @throws JSONException
     * 
     * @since 2016. 05. 26
     * @since 1.6.0
     */
    public static final <T> JSONArray toJSONArray(Object values, Class<T> elemType, boolean ordered) throws JSONException {

        JSONArray array = null;

        switch (JSONMapper.whatType(elemType)) {
            case JSONMapper.FIELD_TYPE_PRIMITIVE:
                array = new JSONArray();
                array = toJSONPrimitiveArray(values, elemType);
                break;
            case JSONMapper.FIELD_TYPE_STRING:

                array = toJSONArray0(values);

                break;
            case JSONMapper.FIELD_TYPE_JSON_CLASS:
                array = new JSONArray();

                IJSONString[] jsonStrArray = (IJSONString[]) values;

                for (IJSONString jsonStr : jsonStrArray) {
                    array.put(jsonStr.toJSONObject(ordered));
                }

                break;
            default:
                break;
        }

        return array;
    }

    /**
     * Return a instance of a class supports JSONArray.
     * 
     * @param values
     * @param ordered
     *            순서 적용 여부
     * @return
     * @throws JSONException
     * 
     * @since 2014. 5. 8.
     */
    public static final <T> JSONArray toJSONArray(Object values, Class<T> elemType, boolean ordered, GenericTypeVariable... paramTypes) throws JSONException {

        JSONArray array = null;

        switch (JSONMapper.whatType(elemType)) {
            case JSONMapper.FIELD_TYPE_PRIMITIVE:
                array = new JSONArray();
                array = toJSONPrimitiveArray(values, elemType);
                break;
            case JSONMapper.FIELD_TYPE_STRING:

                array = toJSONArray0(values);

                break;
            case JSONMapper.FIELD_TYPE_JSON_CLASS:
                array = new JSONArray();

                IJSONString[] jsonStrArray = (IJSONString[]) values;

                for (IJSONString jsonStr : jsonStrArray) {
                    array.put(jsonStr.toJSONObject(ordered, paramTypes));
                }
                break;
            default:
                break;
        }

        return array;
    }

    /**
     * Return a instance of a class supports JSONArray.
     * 
     * @param values
     * @return
     * @throws JSONException
     * 
     * @since 2014. 5. 8.
     */
    public static final <T> JSONArray toJSONArray(Object values, Class<T> elemType, Class<?> paramType) throws JSONException {
        return toJSONArray(values, elemType, false, GenericTypeVariable.getParamType(paramType));
    }

    /**
     * Return a instance of a class supports JSONArray.
     * 
     * @param values
     * @param ordered
     *            순서 적용
     * @return
     * @throws JSONException
     * 
     * @since 2014. 5. 8.
     */
    public static final <T> JSONArray toJSONArray(Object values, Class<T> elemType, Class<?> paramType, boolean ordered) throws JSONException {
        return toJSONArray(values, elemType, ordered, GenericTypeVariable.getParamType(paramType));
    }

    /**
     * Return a instance of a class supports JSONArray.
     * 
     * @param values
     * @return
     * @throws JSONException
     * 
     * @since 2014. 5. 8.
     */
    public static final <T> JSONArray toJSONArray(Object values, Class<T> elemType, GenericTypeVariable... paramTypes) throws JSONException {
        return toJSONArray(values, elemType, false, paramTypes);
    }

    /**
     * Return a instance of a class supports JSONArray.
     * 
     * @param values
     * @return
     * @throws JSONException
     * 
     * @since 2014. 5. 8.
     */
    public static final <T> JSONArray toJSONArray(Object values, Class<T> elemType, List<Class<?>> paramTypes) throws JSONException {
        return toJSONArray(values, elemType, paramTypes, false);
    }

    /**
     * Return a instance of a class supports JSONArray.
     * 
     * @param values
     * @param ordered
     *            TODO
     * @return
     * @throws JSONException
     * 
     * @since 2014. 5. 8.
     */
    public static final <T> JSONArray toJSONArray(Object values, Class<T> elemType, List<Class<?>> paramTypes, boolean ordered) throws JSONException {

        GenericTypeVariable[] paramTypeArray = new GenericTypeVariable[paramTypes.size()];

        int i = 0;
        for (Class<?> paramType : paramTypes) {
            paramTypeArray[i] = GenericTypeVariable.getParamType(paramType);
            i++;
        }

        return toJSONArray(values, elemType, ordered, paramTypeArray);
    }

    private static JSONArray toJSONArray0(Object values) {
        JSONArray array = new JSONArray();

        String[] strArray = new String[0];

        if (values instanceof Collection) {
            strArray = ((Collection<String>) values).toArray(new String[] {});
        } else if (values instanceof Array) {
            strArray = (String[]) values;
        }

        for (String str : strArray) {
            array.put(str);
        }

        return array;
    }

    public static JSONObject[] toJSONObjectArray(JSONArray jsonarray) throws JSONException {
        JSONObject[] jsonObjects = new JSONObject[jsonarray.length()];
        for (int i = 0; i < jsonarray.length(); i++) {
            jsonObjects[i] = jsonarray.getJSONObject(i);
        }

        return jsonObjects;
    }

    /**
     * 
     * @param values
     * @param elemType
     * @return
     * @throws JSONException
     * 
     * @since 2014. 5. 8.
     */
    public static final <T extends Serializable & IJSONString> Collection<JSONObject> toJSONObjectCollection(Collection<T> values, Class<T> elemType) throws JSONException {
        return toJSONObjectCollection(toJSONArray(values.toArray(new IJSONString[] {}), elemType, false));
    }

    /**
     * 
     * @param values
     * @param elemType
     * @param ordered
     * @return
     * @throws JSONException
     *
     * @since 2016. 5. 26.
     * @since 1.6.0
     */
    public static final <T extends Serializable & IJSONString> Collection<JSONObject> toJSONObjectCollection(Collection<T> values, Class<T> elemType, boolean ordered)
            throws JSONException {
        return toJSONObjectCollection(toJSONArray(values.toArray(new IJSONString[] {}), elemType, ordered));
    }

    /**
     * 
     * @param values
     * @param elemType
     * @param ordered
     * @param paramTypes
     * @return
     * @throws JSONException
     *
     * @since 2016. 5. 26.
     * @sincd 1.6.0
     */
    public static final <T extends Serializable & IJSONString> Collection<JSONObject> toJSONObjectCollection(Collection<T> values, Class<T> elemType, boolean ordered,
            GenericTypeVariable... paramTypes) throws JSONException {
        return toJSONObjectCollection(toJSONArray(values.toArray(new IJSONString[] {}), elemType, ordered, paramTypes));
    }

    /**
     * 
     * @param values
     * @param elemType
     * @return
     * @throws JSONException
     * 
     * @since 2014. 5. 8.
     */
    public static final <T extends Serializable & IJSONString> Collection<JSONObject> toJSONObjectCollection(Collection<T> values, Class<T> elemType, Class<?> paramType)
            throws JSONException {
        return toJSONObjectCollection(toJSONArray(values.toArray(new IJSONString[] {}), elemType, false, GenericTypeVariable.getParamType(paramType)));
    }

    /**
     * 
     * @param values
     * @param elemType
     * @param paramType
     * @param ordered
     * @return
     * @throws JSONException
     *
     * @since 2016. 5. 26.
     * @since 1.6.0
     */
    public static final <T extends Serializable & IJSONString> Collection<JSONObject> toJSONObjectCollection(Collection<T> values, Class<T> elemType, Class<?> paramType,
            boolean ordered) throws JSONException {
        return toJSONObjectCollection(toJSONArray(values.toArray(new IJSONString[] {}), elemType, ordered, GenericTypeVariable.getParamType(paramType)));
    }

    /**
     * 
     * @param values
     * @param elemType
     * @return
     * @throws JSONException
     * 
     * @since 2014. 5. 8.
     */
    public static final <T extends Serializable & IJSONString> Collection<JSONObject> toJSONObjectCollection(Collection<T> values, Class<T> elemType,
            GenericTypeVariable... paramTypes) throws JSONException {
        return toJSONObjectCollection(toJSONArray(values.toArray(new IJSONString[] {}), elemType, false, paramTypes));
    }

    /**
     * 
     * @param values
     * @param elemType
     * @return
     * @throws JSONException
     * 
     * @since 2014. 5. 8.
     */
    public static final <T extends Serializable & IJSONString> Collection<JSONObject> toJSONObjectCollection(Collection<T> values, Class<T> elemType, List<Class<?>> paramTypes)
            throws JSONException {
        return toJSONObjectCollection(toJSONArray(values.toArray(new IJSONString[] {}), elemType, paramTypes, false));
    }

    /**
     * 
     * @param values
     * @param elemType
     * @param paramTypes
     * @param ordered
     * @return
     * @throws JSONException
     *
     * @since 2016. 5. 26.
     * @since 1.6.0
     */
    public static final <T extends Serializable & IJSONString> Collection<JSONObject> toJSONObjectCollection(Collection<T> values, Class<T> elemType, List<Class<?>> paramTypes,
            boolean ordered) throws JSONException {
        return toJSONObjectCollection(toJSONArray(values.toArray(new IJSONString[] {}), elemType, paramTypes, ordered));
    }

    public static Collection<JSONObject> toJSONObjectCollection(JSONArray jsonarray) throws JSONException {
        List<JSONObject> jsonObjects = new ArrayList<>();
        for (int i = 0; i < jsonarray.length(); i++) {
            jsonObjects.add(jsonarray.getJSONObject(i));
        }

        return jsonObjects;
    }

    /**
     * Return a instance of a class supports JSONArray.
     * 
     * @param values
     * @return
     * @throws JSONException
     * 
     * @since 2014. 5. 8.
     */
    public static final <T> Collection<JSONObject> toJSONObjectCollection(Object values, Class<T> elemType) throws JSONException {
        return toJSONObjectCollection(toJSONArray(values, elemType, false));
    }

    /**
     * 
     * @param values
     * @param elemType
     * @param ordered
     * @return
     * @throws JSONException
     *
     * @since 2016. 5. 26.
     * @since 1.6.0
     */
    public static final <T> Collection<JSONObject> toJSONObjectCollection(Object values, Class<T> elemType, boolean ordered) throws JSONException {
        return toJSONObjectCollection(toJSONArray(values, elemType, ordered));
    }

    /**
     * 
     * @param values
     * @param elemType
     * @param ordered
     * @param paramTypes
     * @return
     * @throws JSONException
     *
     * @since 2016. 5. 26.
     * @since 1.6.0
     */
    public static final <T> Collection<JSONObject> toJSONObjectCollection(Object values, Class<T> elemType, boolean ordered, GenericTypeVariable... paramTypes)
            throws JSONException {
        return toJSONObjectCollection(toJSONArray(values, elemType, ordered, paramTypes));
    }

    /**
     * Return a instance of a class supports JSONArray.
     * 
     * @param values
     * @return
     * @throws JSONException
     * 
     * @since 2014. 5. 8.
     */
    public static final <T> Collection<JSONObject> toJSONObjectCollection(Object values, Class<T> elemType, Class<?> paramType) throws JSONException {
        return toJSONObjectCollection(toJSONArray(values, elemType, false, GenericTypeVariable.getParamType(paramType)));
    }

    /**
     * 
     * @param values
     * @param elemType
     * @param paramType
     * @param ordered
     * @return
     * @throws JSONException
     *
     * @since 2016. 5. 26.
     * @since 1.6.0
     */
    public static final <T> Collection<JSONObject> toJSONObjectCollection(Object values, Class<T> elemType, Class<?> paramType, boolean ordered) throws JSONException {
        return toJSONObjectCollection(toJSONArray(values, elemType, ordered, GenericTypeVariable.getParamType(paramType)));
    }

    /**
     * Return a instance of a class supports JSONArray.
     * 
     * @param values
     * @return
     * @throws JSONException
     * 
     * @since 2014. 5. 8.
     */
    public static final <T> Collection<JSONObject> toJSONObjectCollection(Object values, Class<T> elemType, GenericTypeVariable... paramTypes) throws JSONException {
        return toJSONObjectCollection(toJSONArray(values, elemType, false, paramTypes));
    }

    /**
     * Return a instance of a class supports JSONArray.
     * 
     * @param values
     * @return
     * @throws JSONException
     * 
     * @since 2014. 5. 8.
     */
    public static final <T> Collection<JSONObject> toJSONObjectCollection(Object values, Class<T> elemType, List<Class<?>> paramTypes) throws JSONException {
        return toJSONObjectCollection(toJSONArray(values, elemType, paramTypes, false));
    }

    /**
     * 
     * @param values
     * @param elemType
     * @param paramTypes
     * @param ordered
     * @return
     * @throws JSONException
     *
     * @since 2016. 5. 26.
     * @since 1.6.0
     */
    public static final <T> Collection<JSONObject> toJSONObjectCollection(Object values, Class<T> elemType, List<Class<?>> paramTypes, boolean ordered) throws JSONException {
        return toJSONObjectCollection(toJSONArray(values, elemType, paramTypes, ordered));
    }

    static final <T> JSONArray toJSONPrimitiveArray(Object values, Class<T> elemType) throws JSONException {

        if (boolean.class.equals(elemType)) {
            return toJSONArray((boolean[]) values);
        } else if (char.class.equals(elemType)) {
            return toJSONArray((char[]) values);
        } else if (byte.class.equals(elemType)) {
            return toJSONArray((byte[]) values);
        } else if (int.class.equals(elemType)) {
            return toJSONArray((int[]) values);
        } else if (long.class.equals(elemType)) {
            return toJSONArray((long[]) values);
        } else if (float.class.equals(elemType)) {
            return toJSONArray((float[]) values);
        } else if (double.class.equals(elemType)) {
            return toJSONArray((double[]) values);
        } else {

            JSONArray array = new JSONArray();

            T[] valuesArr = (T[]) values;

            for (T value : valuesArr) {
                array.put(value);
            }

            return array;
        }
    }

    /**
     * Return an array of one of primitive types.
     * 
     * @param T
     *            primitive types.
     * 
     * @param jsonArray
     *            an instance of a class supports JSONArray
     * @param elemType
     *            the type of data contained in JSONArray
     * @return
     * @throws JSONException
     * 
     * @since 2014. 4. 15.
     */
    static final <T> Object toPrimitiveArray(JSONArray jsonArray, Class<T> elemType) throws JSONException {

        if (boolean.class.equals(elemType)) {
            return booleanArray(jsonArray);
        } else if (char.class.equals(elemType)) {
            return charArray(jsonArray);
        } else if (byte.class.equals(elemType)) {
            return byteArray(jsonArray);
        } else if (int.class.equals(elemType)) {
            return intArray(jsonArray);
        } else if (long.class.equals(elemType)) {
            return longArray(jsonArray);
        } else if (float.class.equals(elemType)) {
            return floatArray(jsonArray);
        } else if (double.class.equals(elemType)) {
            return doubleArray(jsonArray);
        } else {
            int length = jsonArray.length();
            T[] array = (T[]) Array.newInstance(elemType, length);

            for (int i = 0; i < length; i++) {
                array[i] = (T) jsonArray.get(i);
            }

            return array;
        }
    }
}
