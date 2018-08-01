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
 * Date  : 2012. 11. 19. 오전 12:40:38
 *
 * Author: Park_Jun_Hong_(fafanmama_at_naver_com)
 *
 */
package open.commons.json.model.supports;

import open.commons.json.annotation.JSONField;
import open.commons.json.model.DefaultJSONModel;

import code.org.codehaus.jettison.json.JSONException;

public class JSONSingleResult<T> extends DefaultJSONModel implements IJSONResult {

    private static final long serialVersionUID = 7212670559677695165L;

    @JSONField
    private int resultCode = IMessageCodeProvider.NO_CODE;

    @JSONField
    private String resultMsg;

    @JSONField
    private T resultValue;

    @JSONField
    private String name;

    public JSONSingleResult(String name) {
        this.name = name;
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        JSONSingleResult<?> other = (JSONSingleResult<?>) obj;
        if (name == null) {
            if (other.name != null)
                return false;
        } else if (!name.equals(other.name))
            return false;
        if (resultCode != other.resultCode)
            return false;
        if (resultMsg == null) {
            if (other.resultMsg != null)
                return false;
        } else if (!resultMsg.equals(other.resultMsg))
            return false;
        if (resultValue == null) {
            if (other.resultValue != null)
                return false;
        } else if (!resultValue.equals(other.resultValue))
            return false;
        return true;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @return the resultCode
     */
    public int resultCode() {
        return resultCode;
    }

    /**
     * @return the resultMsg
     */
    public String resultMsg() {
        return resultMsg;
    }

    /**
     * @return the resultValue
     */
    public T resultValue() {
        return resultValue;
    }

    /**
     * @param name
     *            the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @param resultCode
     *            the resultCode to set
     */
    public void setResultCode(int resultCode) {
        this.resultCode = resultCode;
    }

    /**
     * @param resultMsg
     *            the resultMsg to set
     */
    public void setResultMsg(String resultMsg) {
        this.resultMsg = resultMsg;
    }

    /**
     * @param resultValue
     *            the resultValue to set
     */
    public void setResultValue(T resultValue) {
        this.resultValue = resultValue;
    }

    // @Override
    // public JSONObject toJSONObject() throws JSONException {
    // JSONObject json = new JSONObject();
    // json.put("resultCode", resultCode).put("resultMsg", resultMsg);
    // if (resultValue != null) {
    // JSONObject j = new JSONObject();
    // j.put(name, resultValue);
    // json.put("resultValue", j);
    // }
    //
    // return json;
    // }

    @Override
    public String toJSONString() throws JSONException {
        return toJSONObject().toString();
    }
}
