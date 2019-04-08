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
 * Date  : 2012. 11. 19. 오전 12:10:35
 *
 * Author: Park_Jun_Hong_(fafanmama_at_naver_com)
 *
 */

package open.commons.json.util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

import open.commons.collection.FIFOMap;
import open.commons.doc.IndentationFactory;
import open.commons.doc.IndentationFactory.Indentation;

public class JSONUtil {

    /**
     * 
     * @param absorber
     * @param absorbedInto
     * @throws JSONException
     *
     * @since 2015. 1. 28.
     */
    @SuppressWarnings("unchecked")
    public static void absorb(JSONObject absorber, JSONObject absorbedInto) throws JSONException {

        Iterator<String> itr = absorbedInto.keys();

        String name = null;
        while (itr.hasNext()) {
            name = itr.next();
            absorber.put(name, absorbedInto.get(name));
        }
    }

    /**
     * 
     * @param absorber
     * @param absorbedIntoStr
     *            JSONObject 문자열
     * @throws JSONException
     *
     * @since 2015. 1. 28.
     */
    @SuppressWarnings("unchecked")
    public static void absorb(JSONObject absorber, String absorbedIntoStr) throws JSONException {

        JSONObject absorbedInto = new JSONObject(absorbedIntoStr);

        Iterator<String> itr = absorbedInto.keys();

        String name = null;
        while (itr.hasNext()) {
            name = itr.next();
            absorber.put(name, absorbedInto.get(name));
        }
    }

    /**
     * 
     * @param absorberStr
     *            JSONObject 문자열
     * @param absorbedInto
     * @throws JSONException
     *
     * @since 2015. 1. 28.
     */
    @SuppressWarnings("unchecked")
    public static void absorb(String absorberStr, JSONObject absorbedInto) throws JSONException {

        JSONObject absorber = new JSONObject(absorberStr);

        Iterator<String> itr = absorbedInto.keys();

        String name = null;
        while (itr.hasNext()) {
            name = itr.next();
            absorber.put(name, absorbedInto.get(name));
        }
    }

    /**
     * 
     * @param absorberStr
     *            JSONObject 문자열
     * @param absorbedIntoStr
     *            JSONObject 문자열
     * @throws JSONException
     *
     * @since 2015. 1. 28.
     */
    @SuppressWarnings("unchecked")
    public static void absorb(String absorberStr, String absorbedIntoStr) throws JSONException {

        JSONObject absorber = new JSONObject(absorberStr);
        JSONObject absorbedInto = new JSONObject(absorbedIntoStr);

        Iterator<String> itr = absorbedInto.keys();

        String name = null;
        while (itr.hasNext()) {
            name = itr.next();
            absorber.put(name, absorbedInto.get(name));
        }
    }

    /**
     * 
     * @param json
     * @param key
     * @return T or null if {@link JSONObject} has no the 'key' or the value to the 'key' is {@link JSONObject#NULL}.
     * @throws JSONException
     */
    @SuppressWarnings("unchecked")
    public static <T> T get(JSONObject json, String key) throws JSONException {
        return hasValue(json, key) ? (T) json.get(key) : null;
    }

    /**
     * 
     * @param json
     * @param key
     * @param defaultValue
     * @return T or null if {@link JSONObject} has no the 'key' or the value to the 'key' is {@link JSONObject#NULL}.
     * 
     * @throws JSONException
     * 
     * @since 2014. 7. 25.
     */
    public static <T> T get(JSONObject json, String key, T defaultValue) throws JSONException {
        T value = get(json, key);

        return value != null ? value : defaultValue;
    }

    /**
     * 
     * @param json
     * @param key
     * @return {@link JSONArray} object or null if {@link JSONObject} has no the 'key' or the value to the 'key' is
     *         {@link JSONObject#NULL}.
     * @throws JSONException
     */
    public static JSONArray getJSONArray(JSONObject json, String key) throws JSONException {
        return hasValue(json, key) ? (JSONArray) json.getJSONArray(key) : null;
    }

    /**
     * 
     * @param json
     * @param key
     * @return {@link JSONObject} or null if {@link JSONObject} has no the 'key' or the value to the 'key' is
     *         {@link JSONObject#NULL}.
     * @throws JSONException
     */
    public static <T> JSONObject getJSONObject(JSONObject json, String key) throws JSONException {
        return hasValue(json, key) ? json.getJSONObject(key) : null;
    }

    /**
     * 
     * @param json
     * @param key
     * @return {@link List} or null if {@link JSONObject} has no the 'key' or the value to the 'key' is
     *         {@link JSONObject#NULL}.
     * @throws JSONException
     */
    @SuppressWarnings("unchecked")
    public static <T> List<T> getList(JSONObject json, String key) throws JSONException {
        List<T> list = null;

        JSONArray array = getJSONArray(json, key);

        if (array != null) {
            list = new ArrayList<T>();

            for (int i = 0; i < array.length(); i++) {
                list.add((T) array.get(i));
            }
        }

        return list;
    }

    /**
     * {@link JSONObject}에서 주어진 키에 해당하는 대상이 {@link JSONArray}인지 여부를 반환한다.
     * 
     * @param json
     * @param key
     * @return
     * @throws JSONException
     */
    public static JsonType getType(JSONObject json, String key) throws JSONException {
        JsonType type = JsonType.JSON_NULL;

        if (hasValue(json, key)) {
            type = getType(json.get(key));
        }

        return type;
    }

    public static JsonType getType(Object object) {
        JsonType type = JsonType.JSON_NULL;

        if (object instanceof JSONArray) {
            type = JsonType.JSON_ARRAY;
        } else if (object instanceof JSONObject) {
            type = JsonType.JSON_OBJECT;
        } else {
            type = JsonType.JSON_PRIMITIVE;
        }

        return type;
    }

    /**
     * {@link JSONObject}에 주어진 키에 해당하는 값이 존재하고, 그 값이 {@link JSONObject#NULL}이 아닌지를 반환한다.
     * 
     * @param json
     * @param key
     * @return
     * @throws JSONException
     */
    public static boolean hasValue(JSONObject json, String key) throws JSONException {
        return json.has(key) && !(json.get(key) instanceof JSONObject.Null);
    }

    public static void newLine(StringBuffer sb, boolean newLine) {
        if (newLine) {
            sb.append('\n');
        }
    }

    public static JSONArray toJSONArray(boolean[] values) throws JSONException {
        JSONArray array = new JSONArray();

        for (boolean value : values) {
            array.put(value);
        }

        return array;
    }

    public static JSONArray toJSONArray(byte[] values) throws JSONException {
        JSONArray array = new JSONArray();

        for (byte value : values) {
            array.put(value);
        }

        return array;
    }

    public static JSONArray toJSONArray(char[] values) throws JSONException {
        JSONArray array = new JSONArray();

        for (char value : values) {
            array.put(value);
        }

        return array;
    }

    public static JSONArray toJSONArray(Collection<?> objects) {
        return toJSONArray(objects.toArray());
    }

    public static JSONArray toJSONArray(double[] values) throws JSONException {
        JSONArray array = new JSONArray();

        for (double value : values) {
            array.put(value);
        }

        return array;
    }

    public static JSONArray toJSONArray(float[] values) throws JSONException {
        JSONArray array = new JSONArray();

        for (float value : values) {
            array.put(value);
        }

        return array;
    }

    public static JSONArray toJSONArray(int[] values) throws JSONException {
        JSONArray array = new JSONArray();

        for (int value : values) {
            array.put(value);
        }

        return array;
    }

    public static JSONArray toJSONArray(long[] values) throws JSONException {
        JSONArray array = new JSONArray();

        for (long value : values) {
            array.put(value);
        }

        return array;
    }

    /**
     * 주어진 내용을 가지고 {@link JSONArray} 객체를 생성하여 반환한다.
     * 
     * @param objects
     * @return
     */
    public static JSONArray toJSONArray(Object... objects) {
        JSONArray array = new JSONArray();

        for (Object obj : objects) {
            array.put(obj);
        }

        return array;
    }

    public static List<Object> toList(JSONArray jsonArray) throws JSONException {
        List<Object> list = new ArrayList<Object>();

        Object value = null;
        int len = jsonArray.length();
        for (int i = 0; i < len; i++) {
            value = jsonArray.get(i);
            switch (getType(value)) {
                case JSON_PRIMITIVE:
                    break;
                case JSON_ARRAY:
                    value = toList((JSONArray) value);
                    break;
                case JSON_OBJECT:
                    value = toMap((JSONObject) value);
                    break;
                default:
                    value = null;
                    break;
            }
            list.add(value);
        }

        return list;
    }

    public static List<Object> toList(String jsonString) throws JSONException {
        return toList(new JSONArray(jsonString));
    }

    @SuppressWarnings("unchecked")
    public static Map<String, Object> toMap(JSONObject json) throws JSONException {
        Map<String, Object> map = new FIFOMap<String, Object>();

        Iterator<String> keyItr = json.keys();

        String key = null;
        Object value = null;
        while (keyItr.hasNext()) {
            key = keyItr.next();

            switch (getType(json, key)) {
                case JSON_PRIMITIVE:
                    value = get(json, key);
                    break;
                case JSON_ARRAY:
                    value = toList(getJSONArray(json, key));
                    break;
                case JSON_OBJECT:
                    value = toMap(getJSONObject(json, key));
                    break;
                default:
                    value = null;
                    break;
            }

            map.put(key, value);
        }

        return map;
    }

    public static Map<String, Object> toMap(String jsonString) throws JSONException {
        return toMap(new JSONObject(jsonString));
    }

    @SuppressWarnings("unchecked")
    public static Map<String, Object> toMapStartWith(JSONObject json, String startWith) throws JSONException {
        Map<String, Object> map = new FIFOMap<String, Object>();

        Iterator<String> keyItr = json.keys();

        String key = null;
        Object value = null;
        while (keyItr.hasNext()) {
            key = keyItr.next();

            if (!key.startsWith(startWith)) {
                continue;
            }

            switch (getType(json, key)) {
                case JSON_PRIMITIVE:
                    value = get(json, key);
                    break;
                case JSON_ARRAY:
                    value = toList(getJSONArray(json, key));
                    break;
                case JSON_OBJECT:
                    value = toMap(getJSONObject(json, key));
                    break;
                default:
                    value = null;
                    break;
            }

            map.put(key, value);
        }

        return map;
    }

    @SuppressWarnings("unchecked")
    public static Map<String, Object> toMapStartWithout(JSONObject json, String startWithout) throws JSONException {
        Map<String, Object> map = new FIFOMap<String, Object>();

        Iterator<String> keyItr = json.keys();

        String key = null;
        Object value = null;
        while (keyItr.hasNext()) {
            key = keyItr.next();

            if (key.startsWith(startWithout)) {
                continue;
            }

            switch (getType(json, key)) {
                case JSON_PRIMITIVE:
                    value = get(json, key);
                    break;
                case JSON_ARRAY:
                    value = toList(getJSONArray(json, key));
                    break;
                case JSON_OBJECT:
                    value = toMap(getJSONObject(json, key));
                    break;
                default:
                    value = null;
                    break;
            }

            map.put(key, value);
        }

        return map;
    }

    /**
     * JSON 문자열을 읽기 편한 형태로 출력한다.
     * 
     * @param jsonString
     *            JSON 문자열
     * @return
     * 
     * @see #toString(String, int)
     */
    public static String toString(String jsonString) {
        return toString(jsonString, 0);
    }

    /**
     * JSON 문자열을 읽기 편한 형태로 출력한다.
     * 
     * @param jsonString
     *            JSON 문자열
     * @param indentationFactor
     *            들여쓰기 크기 (단위: 빈칸)
     * @return
     */
    public static String toString(String jsonString, int indentationFactor) {

        if (jsonString == null) {
            return null;
        }

        boolean indentFactor = true;

        if (indentationFactor < 1) {
            indentationFactor = 0;
            indentFactor = false;
        }

        char[] indentChars = new char[indentationFactor];
        for (int i = 0; i < indentationFactor; i++) {
            indentChars[i] = ' ';
        }

        char[] charArr = jsonString.toCharArray();
        StringBuffer sb = new StringBuffer();

        boolean esc = false;
        boolean newLined = false;
        boolean literal = false;

        Indentation indent = IndentationFactory.newInstance(new String(indentChars));

        for (char c : charArr) {
            // #1. check Escape
            if (!esc && c == '\\') {
                sb.append(c);

                esc = true;
            } else {
                if (c == '"') {
                    if (!esc) {
                        literal ^= true;
                    }
                }

                // skip
                if (!literal && Character.isWhitespace(c)) {
                    continue;
                }

                // newline before a char
                if (indentFactor && !literal && (c == '}' || c == ']')) {
                    sb.append('\n');

                    indent.dec();

                    sb.append(indent.toString());

                    newLined = true;
                }

                sb.append(c);

                // newline after a char
                if (indentFactor && !literal && !newLined && (c == '{' || c == '[' || c == ',')) {
                    sb.append('\n');

                    if (c != ',') {
                        indent.inc();
                    }

                    sb.append(indent.toString());
                }

                if (indentFactor && !literal && c == ':') {
                    sb.append(' ');
                }

                esc = false;
            }

            newLined = false;
        }

        return sb.toString();
    }
}
