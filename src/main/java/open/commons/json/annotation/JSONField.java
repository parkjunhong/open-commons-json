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
 * Date  : 2014. 3. 12. 오후 2:45:33
 *
 * Author: Park_Jun_Hong_(parkjunhong77@gmail.com)
 * 
 */

package open.commons.json.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.lang.model.type.NullType;

import open.commons.json.model.exception.RequiredNameNotFoundException;

/**
 * 사용자 정의 클래스 필드에서 JSON 변환 대상을 설정하는 어노테이션
 * 
 * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
 * 
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface JSONField {

    /**
     * 해당 필드의 상속 불가능 여부.
     * 
     * @return <code>true</code> means uninheritable.
     * 
     * @deprecated Use {@link #inheritable()} instead.
     */
    boolean isfinal() default true;

    /**
     * 하위 클래스에 해당 필드를 전달할지 여부.
     * 
     * @return <code>true</code> means inheritable.
     *
     * @since 2016. 2. 5.
     * 
     * @since 1.4.6
     */
    boolean inheritable() default true;

    /**
     * JSON 데이터 변환기
     * 
     * @return
     */
    String marshaller() default "";

    /**
     * JSON 프로퍼티 이름.
     * 
     * @return
     */
    String name() default "";

    /**
     * 해당 변수가 Generic 을 사용하는 경우 적용된 클래스 타입.
     * 
     * @return
     */
    Class<?>[] genericParamClasses() default NullType.class;

    /**
     * JSON 데이터에 반드시 포함되여야 하는지 여부.
     * 
     * @return
     * 
     * @see RequiredNameNotFoundException
     */
    boolean required() default false;

    /**
     * 필드값을 JSON으로 변경하는 경우 허용하는지 여부.
     * 
     * @return
     * 
     * @since 2014. 4. 7.
     */
    boolean export() default true;

    /**
     * 프로퍼티 나열 순서.
     * 
     * @return
     *
     * @since 2016. 5. 26.
     */
    int order() default 0;

}
