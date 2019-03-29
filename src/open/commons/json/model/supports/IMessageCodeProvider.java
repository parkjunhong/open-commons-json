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
 * Date  : 2014. 4. 3. 오전 10:23:22
 *
 * Author: Park_Jun_Hong_(fafanmama_at_naver_com)
 * 
 */

package open.commons.json.model.supports;

import java.util.Collection;
import java.util.Map.Entry;
import java.util.Set;

/**
 * 처리상태 코드와 메시지를 제공해 주는 인터페이스.
 * 
 * @since 2014. 4. 3.
 * @author Park_Jun_Hong_(fafanmama_at_naver_com)
 */
public interface IMessageCodeProvider {

    /** 코드에 맞는 메시지가 없는 경우 */
    public static final String NO_MESSAGE = IMessageCodeProvider.class.getName() + "-#-NO_MESSAGE";
    /** 메시지에 맞는 코드가 없는 경우 */
    public static final int NO_CODE = NO_MESSAGE.hashCode();

    /**
     * 메시지에 해당하는 코드를 반환한다.
     * 
     * @param message
     * @return 메시지에 해당하는 코드가 없는 경우, {@link #NO_CODE}를 반환한다.
     * 
     * @since 2014. 4. 3.
     */
    public int code(String message);

    /**
     * 등록되지 않은 코드인 경우 반환하는 메시지.
     * 
     * @param code
     *            코드
     * 
     * @return
     * 
     * @since 2014. 4. 3.
     * @see #NO_MESSAGE
     * 
     */
    public String codeNotDefined(int code);

    /**
     * 전체 코드를 반환한다.
     * 
     * @return
     * 
     * @since 2014. 4. 3.
     */
    public Set<Integer> codes();

    /**
     * 코드와 메시지들을 반환한다.
     * 
     * @return
     * 
     * @since 2014. 4. 3.
     */
    public Set<Entry<Integer, String>> entry();

    /**
     * 코드에 해당하는 메시지를 제공한다.
     * 
     * @param code
     * @return 코드에 해당하는 메시지가 없는 경우 {@link #NO_MESSAGE}를 반환한다.
     * 
     * @since 2014. 4. 3.
     */
    public String message(int code);

    /**
     * 등록되지 않은 메시지인 경우 반환하는 코드.
     * 
     * @param message
     *            메시지
     * 
     * @return
     * @since 2014. 4. 3.
     * @see #NO_CODE
     */
    public int messageNotDefined(String message);

    /**
     * 전체 메시지를 반환한다.
     * 
     * @return
     * 
     * @since 2014. 4. 3.
     */
    public Collection<String> messages();

    /**
     * 메시지 코드를 등록한다.
     * 
     * @param code
     *            메시지 코드
     * @param message
     *            메시지
     * @throws IllegalArgumentException
     *             message 가 <code>null</code>인 경우.
     * 
     * @since 2014. 4. 3.
     */
    public void registerCode(int code, String message) throws IllegalArgumentException;

}
