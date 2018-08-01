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

import java.util.ArrayList;
import java.util.List;

import open.commons.json.IJSONString;
import open.commons.json.annotation.JSONField;
import open.commons.utils.AssertUtils;

/**
 * 
 * @param <T>
 * @since 2014. 4. 3.
 * @author Park_Jun_Hong_(fafanmama_at_naver_com)
 */
@SuppressWarnings("unchecked")
public class JSONWrapperArrayResult<T extends IJSONString> extends AbstractJSONWrapperResult {

    private static final long serialVersionUID = 7212670559677695165L;

    @JSONField
    private int resultCode = IMessageCodeProvider.NO_CODE;

    @JSONField
    private String resultMsg;

    @JSONField
    private List<T> resultValue = new ArrayList<T>();

    /**
     * 결과에 포함되는 데이터를 추가한다.
     * 
     * @param resultValueElement
     * @throws ClassCastException
     * 
     * @since 2014. 4. 3.
     */
    public void addResultValueElement(Object resultValueElement) throws ClassCastException {
        AssertUtils.assertNull(resultValueElement);

        if (resultValue == null) {
            resultValue = new ArrayList<T>();
        }

        this.resultValue.add((T) resultValueElement);
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
        JSONWrapperArrayResult<?> other = (JSONWrapperArrayResult<?>) obj;
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
    public List<T> resultValue() {
        return resultValue;
    }

    /**
     * @param resultCode
     *            the resultCode to set
     * @throws IllegalArgumentException
     *             TODO
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
     * @throws IllegalArgumentException
     *             TODO
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
     * 
     * @see open.commons.json.model.supports.AbstractJSONWrapperResult#setResultValue(java.lang.Object)
     */
    @Override
    public void setResultValue(Object resultValue) throws ClassCastException {
        this.resultValue = (List<T>) resultValue;
    }
}
