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
 * Date  : 2014. 7. 29. 오전 11:32:49
 *
 * Author: Park_Jun_Hong_(fafanmama_at_naver_com)
 * 
 */

package open.commons.jsonx.util;

import java.util.Collection;

import code.org.codehaus.jettison.json.JSONArray;
import code.org.codehaus.jettison.json.JSONException;
import code.org.codehaus.jettison.json.JSONObject;

/**
 * 
 * @since 2014. 7. 29.
 * @author Park_Jun_Hong_(fafanmama_at_naver_com)
 */
public class JSONNameMapper {

    /** a name in Source JSON Object */
    public final String src;
    /** a data type in Source JSON Object */
    public final JSONType srcType;
    /** a name in Destination JSON Object */
    public final String dst;
    /** a data type in Destination JSON Object */
    public final JSONType dstType;

    /**
     * 
     * @param name
     * @param type
     * @since 2014. 7. 22.
     */
    public JSONNameMapper(String name, JSONType type) {
        this(name, type, name, type);
    }

    /**
     * 
     * @param name
     * @param srcType
     *            a data type in Source JSON Object
     * @param dstType
     *            a data type in Destination JSON Object
     * @since 2014. 7. 22.
     */
    public JSONNameMapper(String name, JSONType srcType, JSONType dstType) {
        this(name, srcType, name, dstType);
    }

    /**
     * 
     * @param src
     *            a name in Source JSON Object
     * @param srcType
     *            a data type in Source JSON Object
     * @param dst
     *            a name in Destination JSON Object
     * @param dstType
     *            a data type in Destination JSON Object
     * 
     * @since 2014. 7. 22.
     */
    public JSONNameMapper(String src, JSONType srcType, String dst, JSONType dstType) {
        this.src = src;
        this.dst = dst;
        this.srcType = srcType;
        this.dstType = dstType;
    }

    /**
     * 
     * @param src
     *            a name in Source JSON Object
     * @param dst
     *            a name in Destination JSON Object
     * @param type
     * 
     * @since 2014. 7. 22.
     */
    public JSONNameMapper(String src, String dst, JSONType type) {
        this(src, type, dst, type);
    }

    public static JSONObject mapValues(JSONObject srcJson, JSONObject dstJson, Collection<JSONNameMapper> mappers) {
        for (JSONNameMapper mapper : mappers) {
            mapValues(srcJson, dstJson, mapper);
        }

        return dstJson;
    }

    public static JSONObject mapValues(JSONObject srcJson, JSONObject dstJson, JSONNameMapper... mappers) {
        for (JSONNameMapper mapper : mappers) {
            mapValues(srcJson, dstJson, mapper);
        }

        return dstJson;
    }

    public static JSONObject mapValues(JSONObject srcJson, JSONObject dstJson, JSONNameMapper mapper) {

        if (srcJson.has(mapper.src)) {
            try {
                switch (mapper.srcType) {
                    case Array:
                        JSONNameMapper.putArrayAs(srcJson.getJSONArray(mapper.src), dstJson, mapper.dst, mapper.dstType);
                        break;
                    case Boolean:
                        JSONNameMapper.putBooleanAs(srcJson.getBoolean(mapper.src), dstJson, mapper.dst, mapper.dstType);
                        break;
                    case Double:
                        JSONNameMapper.putDoubleAs(srcJson.getDouble(mapper.src), dstJson, mapper.dst, mapper.dstType);
                        break;
                    case Int:
                        JSONNameMapper.putIntAs(srcJson.getInt(mapper.src), dstJson, mapper.dst, mapper.dstType);
                        break;
                    case Long:
                        JSONNameMapper.putLongAs(srcJson.getLong(mapper.src), dstJson, mapper.dst, mapper.dstType);
                        break;
                    case Object:
                        JSONNameMapper.putObjectAs(srcJson.getJSONObject(mapper.src), dstJson, mapper.dst, mapper.dstType);
                        break;
                    case String:
                        JSONNameMapper.putStringAs(srcJson.getString(mapper.src), dstJson, mapper.dst, mapper.dstType);
                        break;
                }
            } catch (JSONException e) {
            }
        }

        return dstJson;
    }

    /**
     * 
     * @param srcValue
     * @param srcType
     *            a type of a value in source JSON Object.
     * @param dstJson
     *            a JSON Object to receive a value.
     * @param dstName
     *            a key in destination JSON Object.
     * @param dstType
     *            a type of a value in destination JSON Object.
     * @since 2014. 7. 22.
     */
    public static void put(Object srcValue, JSONType srcType, JSONObject dstJson, String dstName, JSONType dstType) throws JSONException {

        switch (srcType) {
            case Array:
                putArrayAs(srcValue, dstJson, dstName, dstType);
                break;
            case Boolean:
                putBooleanAs(srcValue, dstJson, dstName, dstType);
                break;
            case Double:
                putDoubleAs(srcValue, dstJson, dstName, dstType);
                break;
            case Int:
                putIntAs(srcValue, dstJson, dstName, dstType);
                break;
            case Long:
                putLongAs(srcValue, dstJson, dstName, dstType);
                break;
            case Object:
                putObjectAs(srcValue, dstJson, dstName, dstType);
                break;
            case String:
                putStringAs(srcValue, dstJson, dstName, dstType);
                break;
        }
    }

    public static void putArrayAs(Object value, JSONObject json, String name, JSONType type) throws JSONException {
        switch (type) {
            case Array:
                json.put(name, value);
                break;
            case String:
                StringBuffer sb = new StringBuffer();

                JSONArray arr = (JSONArray) value;
                final int len = arr.length();

                if (len < 1) {
                    return;
                }

                sb.append(arr.get(0));

                for (int i = 1; i < len; i++) {
                    sb.append(", ");
                    sb.append(arr.get(i));
                }

                json.put(name, sb.toString());
                break;
            default:
                throw new JSONException("CANNOT Convert " + value.getClass().getName() + " to " + type);
        }
    }

    public static void putBooleanAs(Object value, JSONObject json, String name, JSONType type) throws JSONException {
        switch (type) {
            case Array:
                JSONArray arr = new JSONArray();
                arr.put(value);

                json.put(name, arr);
                break;
            case Boolean:
                json.put(name, ((Boolean) value).booleanValue());
                break;
            case String:
                json.put(name, value.toString());
                break;
            default:
                throw new JSONException("CANNOT Convert " + value.getClass().getName() + " to " + type);
        }
    }

    public static void putDoubleAs(Object value, JSONObject json, String name, JSONType type) throws JSONException {
        switch (type) {
            case Array:
                JSONArray arr = new JSONArray();
                arr.put(value);

                json.put(name, arr);
                break;
            case Double:
                json.put(name, ((Double) value).doubleValue());
                break;
            case String:
                json.put(name, String.valueOf(value));
                break;
            default:
                throw new JSONException("CANNOT Convert " + value.getClass().getName() + " to " + type);
        }
    }

    public static void putIntAs(Object value, JSONObject json, String name, JSONType type) throws JSONException {
        switch (type) {
            case Array:
                JSONArray arr = new JSONArray();
                arr.put(value);

                json.put(name, arr);
                break;
            case Double:
                json.put(name, ((Integer) value).doubleValue());
                break;
            case Int:
                json.put(name, ((Integer) value).intValue());
                break;
            case Long:
                json.put(name, ((Integer) value).longValue());
                break;
            case String:
                json.put(name, String.valueOf(value));
                break;
            default:
                throw new JSONException("CANNOT Convert " + value.getClass().getName() + " to " + type);
        }
    }

    public static void putLongAs(Object value, JSONObject json, String name, JSONType type) throws JSONException {
        switch (type) {
            case Array:
                JSONArray arr = new JSONArray();
                arr.put(value);

                json.put(name, arr);
                break;
            case Double:
                json.put(name, ((Long) value).doubleValue());
                break;
            case Int:
                json.put(name, ((Long) value).intValue());
                break;
            case Long:
                json.put(name, ((Long) value).longValue());
                break;
            case String:
                json.put(name, String.valueOf(value));
                break;
            default:
                throw new JSONException("CANNOT Convert " + value.getClass().getName() + " to " + type);
        }
    }

    public static void putObjectAs(Object value, JSONObject json, String name, JSONType type) throws JSONException {
        switch (type) {
            case Array:
                JSONArray arr = new JSONArray();
                arr.put(value);

                json.put(name, arr);
                break;
            case Object:
                json.put(name, value);
                break;
            case String:
                json.put(name, value.toString());
                break;
            default:
                throw new JSONException("CANNOT Convert " + value.getClass().getName() + " to " + type);
        }
    }

    public static void putStringAs(Object value, JSONObject json, String name, JSONType type) throws JSONException {
        switch (type) {
            case Array:
                json.put(name, new JSONArray((String) value));
                break;
            case Object:
                json.put(name, new JSONObject((String) value));
                break;
            case String:
                json.put(name, value);
                break;
            case Boolean:
                json.put(name, Boolean.parseBoolean((String) value));
                break;
            case Double:
                json.put(name, Double.parseDouble((String) value));
                break;
            case Int:
                json.put(name, Integer.parseInt((String) value));
                break;
            case Long:
                json.put(name, Long.parseLong((String) value));
                break;
            default:
                throw new JSONException("CANNOT Convert " + value.getClass().getName() + " to " + type);
        }
    }
}
