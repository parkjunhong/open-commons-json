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
 * Date  : 2014. 6. 4. 오전 11:40:20
 *
 * Author: Park_Jun_Hong_(fafanmama_at_naver_com)
 * 
 */

package open.commons.jsonx;

import java.util.Collection;
import java.util.Map.Entry;
import java.util.Set;

import org.codehaus.jettison.json.JSONObject;

import open.commons.json.IJSONString;
import open.commons.jsonx.model.DynamicNamedJSONModel;

/**
 * {@link JSONObject}이지만 name필드에 오는 문자열이 미리 정해지지 않고 가변적으로 오는 대상을 처리하기 위한 클래스.<br>
 * 
 * 
 * @param <T> 데이터 모델.
 * 
 * @see DynamicNamedJSONModel
 * 
 * @since 2014. 6. 4.
 * @author Park_Jun_Hong_(fafanmama_at_naver_com)
 * 
 */
public interface IDynamicNamedJSONString<T> extends IJSONString {

    /**
     * 
     * @return
     * 
     * @since 2014. 6. 19.
     */
    public Set<Entry<String, T>> entry();

    /**
     * <b><code>name</code></b> 영역에 오는 문자열들을 반환한다.
     * 
     * @return
     * 
     * @since 2014. 6. 19.
     */
    public Set<String> names();

    /**
     * 주어진 이름(<b><code>name</code></b>)에 해당하는 값을 반환한다.
     * @param name
     * @return
     *
     * @since 2014. 6. 19.
     */
    public T value(String name);

    /**
     * 
     * @return
     *
     * @since 2014. 6. 19.
     */
    public Collection<T> values();
}