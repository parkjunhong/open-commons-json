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
 * Date  : 2014. 4. 3. 오전 10:27:33
 *
 * Author: Park_Jun_Hong_(fafanmama_at_naver_com)
 * 
 */

package open.commons.json.model.supports;

import open.commons.json.model.DefaultJSONModel;

/**
 * 
 * @since 2014. 4. 3.
 * @author Park_Jun_Hong_(fafanmama_at_naver_com)
 */
public abstract class AbstractJSONWrapperResult extends DefaultJSONModel implements IJSONResult {

    private static final long serialVersionUID = 1L;

    protected IMessageCodeProvider msgCodeProvider;

    public AbstractJSONWrapperResult() {
        this(null);
    }

    public AbstractJSONWrapperResult(IMessageCodeProvider mcp) {
        this.msgCodeProvider = mcp;
    }

    /**
     * {@link IMessageCodeProvider}가 변경되는 경우, 기존의 결과코드와 그에 대한 메시지를 결과코드 기반으로 새로운 {@link IMessageCodeProvider}에 맞게 변경한다.
     * 
     * 
     * @since 2014. 4. 3.
     */
    public final void rebuild() {
        if (msgCodeProvider != null) {
            int code = resultCode();
            setResultMsg(msgCodeProvider.message(code));
        }
    }

    /**
     * 결과 코드를 설정하고, {@link IMessageCodeProvider}가 설정되어 있는 경우 결과 코드에 대한 메시지를 반환한다.
     * 
     * @param code
     * @return
     * @throws IllegalArgumentException
     *             {@link IMessageCodeProvider}가 설정되어 있는 경우, 코드에 해당하는 메시지가 없는 경우
     * 
     * @since 2014. 4. 3.
     */
    public abstract String setResultCode(int code) throws IllegalArgumentException;

    /**
     * 결과 메시지를 설정하고, {@link IMessageCodeProvider}가 설정되어 있는 경우 메시지에 대한 결과 코드를 반환한다.
     * 
     * @param message
     * @return
     * @throws IllegalArgumentException
     *             {@link IMessageCodeProvider}가 설정되어 있는 경우, 메시지에 해당하는 코드가 없는 경우
     * 
     * @since 2014. 4. 3.
     */
    public abstract int setResultMsg(String message) throws IllegalArgumentException;

    /**
     * 결과값을 설정한다.
     * 
     * @param resultValue
     * @throws ClassCastException
     *             결과값으로 설정된 타입과 전달받은 타입이 일치하지 않을 때.
     * @since 2014. 4. 3.
     */
    public abstract void setResultValue(Object resultValue) throws ClassCastException;
}
