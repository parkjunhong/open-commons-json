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
 * COPYLEFT by 'Open Commons' &  Park Jun-Hong All Rights Reserved when use for commercial purpose.
 *
 * This file is generated under this project, "open-commons-json".
 *
 * Date  : 2014. 6. 17. 오후 3:51:42
 *
 * Author: Park_Jun_Hong_(fafanmama_at_naver_com)
 * 
 */

package open.commons.jsonx;

import open.commons.io.IMarshaller;
import open.commons.json.model.BuildConfig;

/**
 * @param <T>
 *            데이터 모델
 * 
 * @since 2014. 6. 17.
 * @author Park_Jun_Hong_(fafanmama_at_naver_com)
 */
public interface IValueCreator<T> extends IMarshaller<T> {

    public IValueCreator<T> setTypeClass(Class<T> typeClass);

    public IValueCreator<T> setBuildConfig(BuildConfig config);

    public IValueCreator<T> setMarshaller(IMarshaller<T> marshaller);

}
