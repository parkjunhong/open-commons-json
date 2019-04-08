/*
 * 
 *
 * This file is generated under this project, "open.commons.json".
 *
 * Date  : 2014. 3. 14. 오전 11:16:05
 *
 * Author: Park_Jun_Hong_(fafanmama_at_naver_com)
 * 
 */

package open.commons.json.test;

import open.commons.json.IEnumMarshaller;
import open.commons.utils.ConvertUtils;

/**
 * @author Park_Jun_Hong_(fafanmama_at_naver_com)
 * 
 */
public enum JSONEnum {

    ONE, TWO, THREE, FOUR_R;

    public static final IEnumMarshaller<JSONEnum> getMarshaller() {
        return new IEnumMarshaller<JSONEnum>() {

            @Override
            public Object marshall(JSONEnum fieldValue) {

                ConvertUtils.assertValue(fieldValue, JSONEnum.class);

                switch ((JSONEnum) fieldValue) {
                    case ONE:
                        return "ONE";
                    case TWO:
                        return "TWO";
                    case THREE:
                        return "THREE";
                }

                return null;
            }

            @Override
            public JSONEnum unmarshall(Object value) {

                ConvertUtils.assertValue(value, String.class);

                if ("one".equalsIgnoreCase(String.valueOf(value))) {
                    return ONE;
                }
                if ("two".equalsIgnoreCase(String.valueOf(value))) {
                    return TWO;
                }
                if ("three".equalsIgnoreCase(String.valueOf(value))) {
                    return THREE;
                }

                return null;

                // 위 내용과 동일한 결과를 반환한다. 단, 아래 코드는
                // 일치하는 결과가 없으면 Exception을 발생한다.
                // return Enum.valueOf(JSONEnum.class, String.valueOf(value).toUpperCase())
            }
        };
    }

    static void print(Object obj) {
        System.out.println(obj);
    }

    public static void main(String[] args) {
        print(Enum.valueOf(JSONEnum.class, "ONE"));
        print(Enum.valueOf(JSONEnum.class, "FOUR_R"));
        print(JSONEnum.getMarshaller().unmarshall("FOUR"));
        print(Enum.valueOf(JSONEnum.class, "FOUR"));
    }
}
