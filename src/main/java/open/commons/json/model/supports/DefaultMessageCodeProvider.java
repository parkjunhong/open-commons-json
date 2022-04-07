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
 * Date  : 2014. 4. 3. 오전 11:27:04
 *
 * Author: Park_Jun_Hong_(parkjunhong77@gmail.com)
 * 
 */

package open.commons.json.model.supports;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import open.commons.core.concurrent.Mutex;
import open.commons.core.utils.AssertUtils;

/**
 * 
 * @since 2014. 4. 3.
 * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
 */
public class DefaultMessageCodeProvider implements IMessageCodeProvider {
    
    /** 키: 코드, 값: 메시지 */
    protected final Map<Integer, String> messageCodes = new HashMap<Integer, String>();
    protected Mutex mutexMessageCodes = new Mutex("messageCodes");

    /**
     * @see open.commons.json.model.supports.IMessageCodeProvider#code(java.lang.String)
     */
    @Override
    public int code(String message) {

        int code = messageNotDefined(message);

        synchronized (mutexMessageCodes) {
            for (Entry<Integer, String> entry : messageCodes.entrySet()) {
                if (entry.getValue().equals(message)) {
                    code = entry.getKey();
                    break;
                }
            }
        }

        return code;
    }
    /**
     * for message
     * 
     * @see #message(int)
     * @see open.commons.json.model.supports.IMessageCodeProvider#codeNotDefined(int)
     */
    @Override
    public String codeNotDefined(int code) {
        return NO_MESSAGE;
    }

    /**
     * @see open.commons.json.model.supports.IMessageCodeProvider#codes()
     */
    @Override
    public Set<Integer> codes() {
        synchronized (mutexMessageCodes) {
            return Collections.unmodifiableSet(messageCodes.keySet());
        }
    }

    /**
     * @see open.commons.json.model.supports.IMessageCodeProvider#entry()
     */
    @Override
    public Set<Entry<Integer, String>> entry() {
        synchronized (mutexMessageCodes) {
            return Collections.unmodifiableSet(messageCodes.entrySet());
        }
    }

    /**
     * @see open.commons.json.model.supports.IMessageCodeProvider#message(int)
     */
    @Override
    public String message(int code) {
        String message = codeNotDefined(code);

        synchronized (mutexMessageCodes) {
            if (messageCodes.containsKey(code)) {
                message = messageCodes.get(code);
            }
        }

        return message;
    }

    /**
     * for code
     * 
     * @see #code(String)
     * @see open.commons.json.model.supports.IMessageCodeProvider#messageNotDefined(String)
     */
    @Override
    public int messageNotDefined(String message) {
        return NO_CODE;
    }

    /**
     * @see open.commons.json.model.supports.IMessageCodeProvider#messages()
     */
    @Override
    public Collection<String> messages() {
        synchronized (mutexMessageCodes) {
            return Collections.unmodifiableCollection(messageCodes.values());
        }
    }

    /**
     * @see open.commons.json.model.supports.IMessageCodeProvider#registerCode(int, java.lang.String)
     */
    @Override
    public void registerCode(int code, String message) throws IllegalArgumentException {
        AssertUtils.assertNull(message);

        synchronized (mutexMessageCodes) {
            messageCodes.put(code, message);
        }

    }
}
