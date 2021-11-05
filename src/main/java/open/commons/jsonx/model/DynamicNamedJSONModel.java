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
 * Date  : 2014. 6. 4. 오후 3:58:07
 *
 * Author: Park_Jun_Hong_(parkjunhong77@gmail.com)
 * 
 */

package open.commons.jsonx.model;

import java.util.Collection;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

import open.commons.concurrent.Mutex;
import open.commons.json.model.BuildConfig;
import open.commons.json.model.DefaultJSONModel;
import open.commons.json.model.JSONMapper;
import open.commons.jsonx.IDynamicNamedJSONString;
import open.commons.jsonx.IValueCreator;
import open.commons.jsonx.supports.ValueCreatorFactory;
import open.commons.reflect.GenericTypeVariable;
import open.commons.reflect.IllegalGenericNameException;
import open.commons.utils.ReflectionUtils;

/**
 * 
 * <p>
 * <b>Example Code</b><br>
 * 
 * <pre>
 * import open.commons.json.annotation.JSONField;
 * import open.commons.json.model.DefaultJSONModel;
 * import open.commons.jsonx.model.DynamicNamedJSONModel;
 * import open.commons.reflect.GenericTypeVariable;
 * import open.commons.utils.ReflectionUtils;
 * import code.org.codehaus.jettison.json.JSONException;
 * 
 * public class DynamicNamedTest {
 * 
 *     public static void main(String[] args) throws JSONException {
 * 
 *         String jsonString = &quot;{&quot; //
 *                 + &quot; \&quot;home1\&quot;: {&quot; //
 *                 + &quot;     \&quot;owner\&quot;: \&quot;person 1\&quot;,&quot; //
 *                 + &quot;     \&quot;주소\&quot;: \&quot;주소1\&quot;&quot; //
 *                 + &quot; },&quot; //
 *                 + &quot; \&quot;home2\&quot;: {&quot; //
 *                 + &quot;     \&quot;owner\&quot;: \&quot;person 2\&quot;,&quot; //
 *                 + &quot;     \&quot;주소\&quot;: \&quot;주소2\&quot;&quot; //
 *                 + &quot; },&quot; //
 *                 + &quot; \&quot;집\&quot;: {&quot; //
 *                 + &quot;     \&quot;owner\&quot;: \&quot;person 3\&quot;,&quot; //
 *                 + &quot;     \&quot;주소\&quot;: \&quot;주소3\&quot;&quot; //
 *                 + &quot; },&quot; //
 *                 + &quot; \&quot;home4\&quot;: {&quot; //
 *                 + &quot;     \&quot;owner\&quot;: \&quot;person 4\&quot;,&quot; //
 *                 + &quot;     \&quot;주소\&quot;: \&quot;주소4\&quot;&quot; //
 *                 + &quot; }&quot; //
 *                 + &quot;}&quot; //
 *         ;
 * 
 *         DynamicNamedJSONModel&lt;Home&gt; homes = new DynamicNamedJSONModel&lt;Home&gt;();
 *         GenericTypeVariable[] typeVarsArray = ReflectionUtils.createGenericTypeVariables(DynamicNamedJSONModel.class, true, Home.class).toArray(new GenericTypeVariable[] {});
 * 
 *         homes.mature(jsonString, typeVarsArray);
 * 
 *         String toString = homes.toJSONString(2, typeVarsArray);
 * 
 *         System.out.println(toString);
 * 
 *     }
 * 
 *     public static class Home extends DefaultJSONModel {
 *         &#064;JSONField
 *         private String owner;
 * 
 *         &#064;JSONField(name = &quot;주소&quot;)
 *         private String address;
 * 
 *     }
 * 
 * }
 * </pre>
 * 
 * <b>Result</b><br>
 * 
 * <pre>
 * {
 *   "home4": {
 *     "owner": "person 4",
 *     "주소": "주소4"
 *   },
 *   "home1": {
 *     "owner": "person 1",
 *     "주소": "주소1"
 *   },
 *   "집": {
 *     "owner": "person 3",
 *     "주소": "주소3"
 *   },
 *   "home2": {
 *     "owner": "person 2",
 *     "주소": "주소2"
 *   }
 * }
 * 
 * </pre>
 * 
 * @since 2014. 6. 4.
 * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
 */
public class DynamicNamedJSONModel<T> extends DefaultJSONModel implements IDynamicNamedJSONString<T> {

    private static final long serialVersionUID = 1L;

    protected ConcurrentHashMap<String, T> data = new ConcurrentHashMap<String, T>();
    protected Mutex mutexData = new Mutex("data");

    /**
     * @see open.commons.jsonx.IDynamicNamedJSONString#entry()
     */
    @Override
    public Set<Entry<String, T>> entry() {
        synchronized (mutexData) {
            return data.entrySet();
        }
    }

    protected IValueCreator<T> getValueCreator() {
        BuildConfig config = JSONMapper.getBuildConfig(this);

        String typeVarName = ReflectionUtils.getTypeVariableNames(getClass()).get(0);

        GenericTypeVariable typeVar = config.getGenericTypeVariable(typeVarName);

        if (!typeVarName.equals(typeVar.getTypeVarName())) {
            throw new IllegalGenericNameException("NOT MATCH generic name. expected: " + typeVarName + ", value: " + typeVar.getTypeVarName());
        }

        Class<?> typeClass = typeVar.getTypeClass();

        int typeValue = JSONMapper.whatType(typeClass);

        IValueCreator<T> valueCreator = ValueCreatorFactory.create(typeValue, typeClass);

        BuildConfig childConfig = BuildConfig.config();
        childConfig.addFieldBuildConfig(config.getFieldBuildConfigs());

        return valueCreator != null ? valueCreator.setBuildConfig(childConfig) : null;
    }

    /**
     * @see open.commons.json.model.DefaultJSONModel#mature0(code.org.codehaus.jettison.json.JSONObject)
     */
    @SuppressWarnings("unchecked")
    @Override
    protected void mature0(JSONObject json) throws JSONException {

        IValueCreator<T> valueCreator = getValueCreator();

        if (valueCreator == null) {
            return;
        }

        Iterator<String> names = json.keys();
        String name = null;
        while (names.hasNext()) {
            name = names.next();
            data.put(name, valueCreator.unmarshall(json.get(name)));
        }
    }

    /**
     * @see open.commons.jsonx.IDynamicNamedJSONString#names()
     */
    @Override
    public Set<String> names() {
        synchronized (mutexData) {
            return data.keySet();
        }
    }

    /**
     * @see open.commons.json.model.DefaultJSONModel#toJSONObject0()
     */
    @Override
    protected JSONObject toJSONObject0() throws JSONException {
        JSONObject json = new JSONObject();

        IValueCreator<T> valueCreator = (IValueCreator<T>) getValueCreator();

        if (valueCreator == null) {
            return json;
        }

        for (Entry<String, T> entry : data.entrySet()) {
            json.put(entry.getKey(), valueCreator.marshall(entry.getValue()));
        }

        return json;
    }

    /**
     * @see open.commons.json.model.AbstractJSONModel#toString()
     */
    @Override
    public String toString() {

        StringBuffer sb = new StringBuffer();

        sb.append(getClass().getSimpleName());
        sb.append('[');

        T value = null;
        for (Entry<String, T> entry : data.entrySet()) {

            value = entry.getValue();

            if (value == null) {
                continue;
            }

            sb.append(entry.getKey());
            sb.append('=');
            sb.append(value.toString());
        }
        sb.append(']');

        return sb.toString();
    }

    /**
     * @see open.commons.jsonx.IDynamicNamedJSONString#value(java.lang.String)
     */
    @Override
    public T value(String name) {
        synchronized (mutexData) {
            return data.get(name);
        }
    }

    /**
     * @see open.commons.jsonx.IDynamicNamedJSONString#values()
     */
    @Override
    public Collection<T> values() {
        synchronized (mutexData) {
            return data.values();
        }
    }

    /**
     * Return a {@link GenericTypeVariable} instance extracted from {@link DynamicNamedJSONModel}.
     * 
     * @param type
     * @return
     * 
     * @since 2014. 6. 17.
     */
    public static GenericTypeVariable getGenericTypeVariable(Class<?> type) {
        GenericTypeVariable typeVar = new GenericTypeVariable(type);
        typeVar.setTypeVarName(ReflectionUtils.getTypeVariableNames(DynamicNamedJSONModel.class).get(0));

        return typeVar;
    }
}
