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

import open.commons.json.IJSONString;
import open.commons.json.annotation.JSONField;

public class JSONWrapperResult<T extends IJSONString> extends AbstractJSONWrapperResult {

    private static final long serialVersionUID = 7212670559677695165L;

    @JSONField
    private int resultCode = IMessageCodeProvider.NO_CODE;

    @JSONField
    private String resultMsg;

    @JSONField
    private T resultValue;

    public JSONWrapperResult() {
        super();
    }

    public JSONWrapperResult(IMessageCodeProvider msgProvider) {
        super(msgProvider);
    }

    // public JSONW

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
        JSONWrapperResult<?> other = (JSONWrapperResult<?>) obj;
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
     * @param resultCode
     *            the resultCode to set
     * 
     * @see open.commons.json.model.supports.AbstractJSONWrapperResult#setResultCode(int)
     */
    @Override
    public String setResultCode(int resultCode) throws IllegalArgumentException {

        if (msgCodeProvider != null) {
            String msg = msgCodeProvider.message(resultCode);
            if (!IMessageCodeProvider.NO_MESSAGE.equals(msg)) {
                this.resultCode = resultCode;
                this.resultMsg = msg;
            } else {
                throw new IllegalArgumentException("There is no message that matched to a code. code =" + resultCode);
            }
        } else {
            this.resultCode = resultCode;
        }

        return this.resultMsg;
    }

    /**
     * @param resultMsg
     *            the resultMsg to set
     * 
     * @see open.commons.json.model.supports.AbstractJSONWrapperResult#setResultMsg(java.lang.String)
     */
    @Override
    public int setResultMsg(String resultMsg) throws IllegalArgumentException {
        if (msgCodeProvider != null) {
            int code = msgCodeProvider.code(resultMsg);
            if (code != IMessageCodeProvider.NO_CODE) {
                this.resultCode = code;
                this.resultMsg = resultMsg;
            } else {
                throw new IllegalArgumentException("There is no code that matched to a message. message=" + resultMsg);
            }
        } else {
            this.resultMsg = resultMsg;
        }

        return this.resultCode;
    }

    /**
     * @param resultValue
     *            the resultValue to set
     * 
     * @see open.commons.json.model.supports.AbstractJSONWrapperResult#setResultValue(java.lang.Object)
     */
    @SuppressWarnings("unchecked")
    @Override
    public void setResultValue(Object resultValue) throws ClassCastException {
        this.resultValue = (T) resultValue;
    }
}
