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
 * Date  : 2012. 11. 19. 오후 2:11:35
 *
 * Author: Park_Jun_Hong_(fafanmama_at_naver_com)
 *
 */
package open.commons.json.model;

import java.util.Map;

import open.commons.reflect.GenericTypeVariable;

import code.org.codehaus.jettison.json.JSONException;
import code.org.codehaus.jettison.json.JSONObject;

public final class JSONNull extends AbstractJSONModel {

    private static final long serialVersionUID = 1L;

    /**
     * @see open.commons.json.model.AbstractJSONModel#loadDynamicNamedFieldGenericTypeVar()
     */
    @Override
    protected Map<String, GenericTypeVariable> loadDynamicNamedFieldGenericTypeVar() {
        return null;
    }

    /**
     * @see open.commons.json.model.AbstractJSONModel#mature0(code.org.codehaus.jettison.json.JSONObject)
     */
    @Override
    protected void mature0(JSONObject json) throws JSONException {
    }

    /**
     * @see open.commons.json.model.AbstractJSONModel#toJSONObject0()
     */
    @Override
    protected JSONObject toJSONObject0() throws JSONException {
        return null;
    }

    @Override
    public String toJSONString() throws JSONException {
        return "null";
    }
}
