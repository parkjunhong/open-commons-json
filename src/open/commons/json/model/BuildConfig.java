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
 * Date  : 2014. 3. 17. 오후 3:50:32
 *
 * Author: Park_Jun_Hong_(fafanmama_at_naver_com)
 * 
 */

package open.commons.json.model;

import java.lang.reflect.Field;
import java.lang.reflect.TypeVariable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import open.commons.concurrent.Mutex;
import open.commons.json.IJSONString;
import open.commons.json.annotation.JSONField;
import open.commons.reflect.GenericTypeVariable;
import open.commons.utils.AssertUtils;
import open.commons.utils.CollectionUtils;
import open.commons.utils.MapUtils;
import open.commons.utils.ReflectionUtils;

/**
 * @author Park_Jun_Hong_(fafanmama_at_naver_com)
 * 
 */
public class BuildConfig implements Cloneable{

    /**
     * {@link IJSONString}을 구현한 클래스가 Generic Parameters를 가지는 경우 해당 정보를 관리<br>
     * 키: 변수명, 값: 변수에 사용된 Generic 클래스 정보
     */
    private Map<String, GenericTypeVariable> typeVarMap = new HashMap<String, GenericTypeVariable>();
    private Mutex mutexGenericTypeVars = new Mutex("typeVarMap");

    /**
     * 클래스 선언에 적용된 Generic 클래스.
     */
    private ArrayList<GenericTypeVariable> typeVarList = new ArrayList<GenericTypeVariable>();
    private Mutex mutexTypeVars = new Mutex("typeVarList");

    /**
     * 클래스 필드가 Generic을 적용한 타입인 경우 해당 정보를 관리.<br>
     * 키: 필드명, 값: {@link BuildConfig}
     */
    private Map<String, BuildConfig> fieldBuildConfigs = new HashMap<String, BuildConfig>();
    private Mutex mutexFieldBuildConfigs = new Mutex("fieldBuildConfigs");

    /** <b>객체 (not Class<?>, but instance)</b> 필드 중에 {@link JSONField}가 설정된 필드들. */
    private Map<Field, JSONField> annotated;
    
    
    /**
     * @see java.lang.Object#clone()
     */
    @Override
    protected Object clone() throws CloneNotSupportedException {
        BuildConfig clone = new BuildConfig();
        
        for(Entry<String, GenericTypeVariable> entry : this.typeVarMap.entrySet()){
            clone.typeVarMap.put(entry.getKey(), (GenericTypeVariable) entry.getValue().clone());
        }
        
        for(GenericTypeVariable c : this.typeVarList) {
            clone.typeVarList.add((GenericTypeVariable) c.clone());
        }
        
        for(Entry<String, BuildConfig> entry : this.fieldBuildConfigs.entrySet()){
            clone.fieldBuildConfigs.put(entry.getKey(), (BuildConfig) entry.getValue().clone());
        }
        
        if( annotated != null) {
            for(Entry<Field, JSONField> entry : this.annotated.entrySet()){
                clone.annotated.put(entry.getKey(), entry.getValue());
            }
        }
        
        return clone;
    }

    /**
     * prevent to create a new instance.
     */
    private BuildConfig() {
    }

    /**
     * 
     * @param configs
     *            클래스 필드와 필드에 적용할 {@link BuildConfig}.
     * 
     * @since 2014. 3. 20.
     */
    public BuildConfig addFieldBuildConfig(Map<String, BuildConfig> configs) {
        AssertUtils.assertMapNull("The configs MUST NOT be null. configs: " + configs, configs);

        synchronized (mutexFieldBuildConfigs) {
            fieldBuildConfigs.putAll(configs);
        }

        return this;
    }

    /**
     * 
     * @param fieldName
     *            클래스 필드 이름.
     * @param config
     *            해당 클래스 필드에 적용할 설정
     * @since 2014. 3. 20.
     */
    public BuildConfig addFieldBuildConfig(String fieldName, BuildConfig config) {
        AssertUtils.assertNull("Neither fieldName and config MUST be null. fieldName: " + fieldName + ", config: " + config);

        synchronized (mutexFieldBuildConfigs) {
            fieldBuildConfigs.put(fieldName, config);
        }

        return this;
    }

    /**
     * 
     * @param fieldName
     * @param typeVar
     * @return
     * 
     * @since 2014. 6. 17.
     */
    public BuildConfig addFieldGenericTypeVar(GenericTypeVariable typeVar) {
        return addFieldGenericTypeVar(typeVar.getTypeVarName(), typeVar);
    }

    /**
     * 
     * @param fieldName
     * @param typeVar
     * @return
     * 
     * @since 2014. 6. 17.
     */
    public BuildConfig addFieldGenericTypeVar(String fieldName, GenericTypeVariable typeVar) {
        AssertUtils.assertNull("Neither fieldName and config MUST be null. fieldName: " + fieldName + ", typeVar: " + typeVar);

        synchronized (mutexFieldBuildConfigs) {
            // start - 신규 추가하는 데이터에 대한 검증 모듈이 보완되어야 할 듯.... : 2014. 6. 18., Park_Jun_Hong_(fafanmama_at_naver_com)
            BuildConfig config = fieldBuildConfigs.get(fieldName);
            if (config == null) {
                config = new BuildConfig();
                fieldBuildConfigs.put(fieldName, config);
                config.addTypeVariable(typeVar);
            }
            // end - 신규 추가하는 데이터에 대한 검증 모듈이 보완되어야 할 듯.... : 2014. 6. 18.
            
        }

        return this;
    }

    /**
     * 
     * @param paramType
     *            클래스 선언에 사용된 {@link GenericTypeVariable}
     * 
     * @since 2014. 3. 20.
     */
    public BuildConfig addTypeVariable(GenericTypeVariable paramType) {
        AssertUtils.assertNull("The paramType MUST NOT be null. paramType: null", paramType);

        synchronized (mutexTypeVars) {
            typeVarList.add(paramType);
        }

        return this;
    }

    /**
     * 
     * @param paramTypes
     * 
     * @since 2014. 3. 20.
     * 
     * @see #addTypeVariable(GenericTypeVariable)
     * 
     */
    public BuildConfig addTypeVariable(GenericTypeVariable... paramTypes) {
        AssertUtils.assertNull("The paramTypes MUST NOT be null. paramTypes: null", paramTypes);

        synchronized (mutexTypeVars) {
            this.typeVarList.addAll(Arrays.asList(paramTypes));
        }

        return this;
    }

    /**
     * 
     * @param paramTypes
     * @since 2014. 3. 20.
     * 
     * @see #addTypeVariable(GenericTypeVariable)
     */
    public BuildConfig addTypeVariable(List<GenericTypeVariable> paramTypes) {
        AssertUtils.assertNull("The paramTypes MUST NOT be null. paramTypes: null", paramTypes);

        synchronized (mutexTypeVars) {
            this.typeVarList.addAll(paramTypes);
        }

        return this;
    }

    <T extends IJSONString> void build(T object) {
        synchronized (mutexTypeVars) {
            build0(object, typeVarList.toArray(new GenericTypeVariable[] {}));
        }
    }

    <T extends IJSONString> void build(T object, GenericTypeVariable... paramTypes) {
        build0(object, paramTypes);
    }

    private <T extends IJSONString> void build0(T object, GenericTypeVariable... paramTypes) {
        AssertUtils.assertNulls("Neither object and paramTypes MUST be null. object: " + object + ", paramTypes: " + paramTypes, object, paramTypes);

        synchronized (mutexGenericTypeVars) {
            TypeVariable<?>[] typeVars = object.getClass().getTypeParameters();

            // start - In case, declares generic paramerterized type but no set. : 2014. 3. 18., Park_Jun_Hong_(fafanmama_at_naver_com)
            if (typeVars.length > 0 && paramTypes.length == 0) {
                paramTypes = new GenericTypeVariable[typeVars.length];
                GenericTypeVariable objectType = new GenericTypeVariable(Object.class);
                for (int i = 0; i < typeVars.length; i++) {
                    paramTypes[i] = objectType;
                }
            }
            // end - In case, declares generic paramerterized type but no set. : 2014. 3. 18.

            AssertUtils.assertTrue("The count of Generic Info in class declaration is not equal to the count of generic parameter types. typeVars: " + typeVars.length
                    + ", paramTypes: " + paramTypes.length, typeVars.length != paramTypes.length);

            String genericName = null;
            GenericTypeVariable paramType = null;
            for (int i = 0; i < typeVars.length; i++) {
                genericName = typeVars[i].getName();

                paramType = paramTypes[i];
                paramType.setTypeVarName(genericName);

                typeVarMap.put(genericName, paramType);
            }
        }

        annotated = ReflectionUtils.getAnnotatedFields(object, JSONField.class);
    }

    public void clear() {
        boolean oldAccessible = false;

        for (Field field : annotated.keySet()) {
            try {
                oldAccessible = field.isAccessible();
                ReflectionUtils.resetField(this, field);
            } catch (IllegalArgumentException ignored) {
            } catch (IllegalAccessException ignored) {
            } finally {
                field.setAccessible(oldAccessible);
            }
        }
    }
    
    public void destroy(){
        // EXPERIMENTAL
        CollectionUtils.clear(this.typeVarList);
        MapUtils.clear(this.annotated, this.fieldBuildConfigs, this.typeVarMap);
    }

    public BuildConfig getFieldBuildConfig(String fieldName) {
        AssertUtils.assertNull("The fieldName MUT NOT be null. fieldName: null", fieldName);

        synchronized (mutexFieldBuildConfigs) {
            return fieldBuildConfigs.get(fieldName);
        }
    }

    /**
     * 
     * @return
     * 
     * @since 2014. 6. 17.
     */
    public Map<String, BuildConfig>  getFieldBuildConfigs() {
        synchronized (mutexFieldBuildConfigs) {
            return Collections.unmodifiableMap(fieldBuildConfigs);
        }
    }
    
    public GenericTypeVariable getGenericTypeVariable(String fieldName) {
        AssertUtils.assertNulls("The fieldName MUST be null. fieldName: " + fieldName, fieldName);

        synchronized (mutexGenericTypeVars) {
            return typeVarMap.get(fieldName);
        }
    }

    /**
     * 
     * @return
     * 
     * @since 2014. 6. 4.
     */
    public Collection<GenericTypeVariable> getGenericTypeVariables() {
        synchronized (mutexGenericTypeVars) {
            return Collections.unmodifiableCollection(typeVarMap.values());
        }
    }

    public void register(String fieldName, GenericTypeVariable paramTypes) {
        AssertUtils.assertNulls("Neither field and paramTypes MUST be null. fieldName: " + fieldName + ", paramTypes: " + paramTypes, fieldName, paramTypes);

        synchronized (mutexGenericTypeVars) {
            typeVarMap.put(fieldName, paramTypes);
        }
    }

    public void reset() {
        typeVarMap.clear();
        annotated = null;
    }

    public static BuildConfig config() {
        return new BuildConfig();
    }

    public static BuildConfig config(GenericTypeVariable... paramTypes) {
        AssertUtils.assertNull(paramTypes);

        BuildConfig config = new BuildConfig();

        config.addTypeVariable(paramTypes);

        return config;
    }

    public static BuildConfig config(List<GenericTypeVariable> paramTypes) {
        AssertUtils.assertNull(paramTypes);

        BuildConfig config = new BuildConfig();

        config.addTypeVariable(paramTypes);

        return config;
    }
}
