/*
 * 
 *
 * This file is generated under this project, "open.commons.json".
 *
 * Date  : 2014. 3. 13. 오후 6:44:54
 *
 * Author: Park_Jun_Hong_(parkjunhong77@gmail.com)
 * 
 */

package open.commons.json.test;

import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

import open.commons.json.DefaultFieldMarshaller;
import open.commons.json.model.JSONArrayFactory;
import open.commons.json.model.JSONMapper;
import open.commons.json.model.supports.JSONWrapperArrayResult;
import open.commons.json.model.supports.JSONWrapperResult;
import open.commons.json.util.JSONUtil;

/**
 * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
 * 
 */
@SuppressWarnings("rawtypes")
public class TEST_JSONWrapperResult {

    static JSONObject __genJSONObject(Object obj, String... names) throws JSONException {
        JSONObject json = new JSONObject();
        for (String name : names) {
            json.put(name, obj);
        }

        return json;
    }

    @SuppressWarnings({ "unused", "deprecation" })
    static void _test_ContainerField() throws JSONException {

        JSONObject json = new JSONObject();
      json.put("enumm", "ONE"); // enum
      json.put("age", 37); // int
      json.put("name", "박준홍"); // String
      json.put("ints", JSONUtil.toJSONArray(1, 2, 3, 4, 5, 6)); // array of int
//      json.put("typeVarArray", JSONUtil.toJSONArray("하나", "둘", "셋", "넷")); // type array of String
//      json.put("typeVarArray", JSONUtil.toJSONArray(1, 2, 3, 4, 5, 6)); // array of int
      json.put("typeVarArray2", JSONUtil.toJSONArray(1, 2, 3, 4, 5, 6)); // array of TypeVariable
      json.put("strings", JSONUtil.toJSONArray("나는", "자랑스런", "대한민국", "국민이다.")); // array of String
      json.put("enumArray", JSONUtil.toJSONArray("ONE", "TWO", "THREE")); // array of Enum
      json.put("enumList", JSONUtil.toJSONArray("ONE", "TWO", "THREE")); // list of Enum
      json.put("enumMap", __genJSONObject("ONE", "하나", "둘", "셋")); // Map of Enum
      json.put("list", JSONUtil.toJSONArray("나는", "자랑스런", "대한민국", "국민이다.")); // List of String
      json.put("mapInt", __genJSONObject(0, "나", "는", "행", "복", "하", "다")); // Map of int
      json.put("mapStr", __genJSONObject("OK", "나", "는", "행", "복", "하", "다")); // Map of String

      JSONObject jsonRef2 = new JSONObject();
      jsonRef2.put("mapStr", __genJSONObject("OK", "나", "는", "행")); // Map of String
      
      JSONObject jsonRef = new JSONObject();
      jsonRef.put("enumm", "ONE");
      jsonRef.put("age", 37);
      jsonRef.put("name", "박준홍");
      jsonRef.put("mapTypeVar", __genJSONObject(jsonRef2, "행", "복", "해")); // Map of Type Variable
      jsonRef.put("mapStr", __genJSONObject("OK", "나", "는", "행", "복", "하", "다")); // Map of String
      jsonRef.put("mapObj", __genJSONObject(jsonRef2, "나", "는", "행", "복", "하", "다")); // Map of Object
      jsonRef.put("ints", JSONUtil.toJSONArray(1, 2, 3, 4, 5, 6)); // array of int
//      jsonRef.put("typeVarArray", JSONUtil.toJSONArray("하나", "둘", "셋", "넷")); // type array of String
//      jsonRef.put("typeVarArray", JSONUtil.toJSONArray(1, 2, 3, 4, 5, 6)); // array of int
      jsonRef.put("typeVarArray2", JSONUtil.toJSONArray(1, 2, 3, 4, 5, 6)); // array of int
//
//      json.put("ref", jsonRef); // Object
//      json.put("refArray", JSONUtil.toJSONArray(jsonRef, jsonRef));
//      json.put("listObj", JSONUtil.toJSONArray(jsonRef, jsonRef, jsonRef)); // List of Object
//      json.put("mapObj", __genJSONObject(jsonRef, "나", "는", "행", "복", "하", "다")); // Map of Object
//      json.put("mapTypeVar", __genJSONObject(jsonRef, "행", "복")); // Map of Type Variable
//      
      JSONArray jsonArr1 = JSONUtil.toJSONArray(jsonRef, jsonRef); // list
      JSONArray jsonArr2 = JSONUtil.toJSONArray(jsonArr1,jsonArr1,jsonArr1,jsonArr1); // array 
//      json.put("listArrList", JSONUtil.toJSONArray(jsonArr2, jsonArr2, jsonArr2)); // list
//
      JSONArray jsonArr11 = JSONUtil.toJSONArray("나는", "자랑스런", "대한민국", "국민이다.");
      JSONArray jsonArr12 = JSONUtil.toJSONArray("언제나", "꽃은", "핀다");
      jsonRef = new JSONObject();
      jsonRef.put("kor", jsonArr11);
      jsonRef.put("flower", jsonArr12);
//      
      JSONObject jsonRef11 = new JSONObject();
      jsonRef11.put("kor11", jsonArr11);
      jsonRef11.put("flower11", jsonArr12);
      json.put("mapArrayList", JSONUtil.toJSONArray(JSONUtil.toJSONArray(jsonRef, jsonRef11), JSONUtil.toJSONArray(jsonRef, jsonRef11)));
      
//      json.put("typeVarArray2", JSONUtil.toJSONArray(1, 2, 3, 4, 5, 6)); // array of TypeVariable
//      json.put("typeList", JSONUtil.toJSONArray("바람이", "불고","꽃이", "시들어도", "언제나", "그러했듯", "봄은", "온다.")); // string list of typeVariable
//      json.put("typeList", JSONUtil.toJSONArray(jsonRef2, jsonRef2, jsonRef2)); // JSONModel list of typeVariable
//      json.put("annonyList", JSONUtil.toJSONArray(1, 2, 3, 4, 5, 6)); // list of no generic

        System.out.println(json.toString());
        System.out.println("---------------------------");

        JSONMapper.register(JSONEnum.class, JSONEnum.getMarshaller());
        JSONMapper.register(JSONModel.ageUpper, new DefaultFieldMarshaller() {
            /**
             * @see open.commons.json.IFieldMarshaller.DefaultFieldMarshaller#marshall(java.lang.Object)
             */
            @Override
            public Object marshall(Object fieldValue) {
                return ((Integer) fieldValue) < 0 ? null : null;
            }
        });

        JSONModel<String, String> model = null;
//        model = new JSONModel<String, String>();
//        model.mature(json);
//        System.out.println(model.toJSONString(2));
        
//        GenericParamType strType = GenericParamType.getParamType(String.class);
//        GenericParamType intType = GenericParamType.getParamType(int.class);
//        GenericParamType doubleType = GenericParamType.getParamType(Double.class);
//        GenericParamType modelType = GenericParamType.getParamType(JSONModel.class);
        
//        BuildConfig config = BuildConfig.config(modelType, strType);
//        config.addFieldBuildConfig("mapTypeVar", config);
//        
//        model = new JSONModel<String, String>();
//        model.mature(json, config);
//        model.toJSONObject();
//        
//        System.out.println(model.toJSONString(2));
        
        
//        JSONEnum enumm = (JSONEnum) model.enumMap.get("하나");

    }

    static void _test_JSONUtil() throws JSONException {
        JSONObject json = new JSONObject();
        json.put("1", "11111:,11\"hihih\"");
        json.put("23333", 2222222);
        json.put("3", 3333333);
        json.put("4", 4444444);
        json.put("5", new JSONObject(json.toString()));
        json.put("6", "[{[{\\{[{[{\"이런...]}\\");

        System.out.println(JSONUtil.toString(json.toString(), 2));
        System.out.println();

        json = new JSONObject(JSONUtil.toString(json.toString(), 2));
        System.out.println(json.toString(2));

    }

    static void _test_JSONWrapperArrayResult() throws JSONException {
        JSONObject jsonResult = new JSONObject();
        jsonResult.put("resultCode", 0);
        jsonResult.put("resultMsg", "성공");

        JSONArray jsonResultValue = new JSONArray();

        JSONObject json = new JSONObject();
        json.put("enumm", "ONE"); // enum
        json.put("age", 37); // int
        json.put("name", "박준홍"); // String
        json.put("strings", JSONUtil.toJSONArray("나는", "자랑스런", "대한민국", "국민이다.")); // array of String
        json.put("ints", JSONUtil.toJSONArray(1, 2, 3, 4, 5, 6)); // array of int
        json.put("enumArray", JSONUtil.toJSONArray("ONE", "TWO", "THREE")); // array of Enum
        json.put("enumList", JSONUtil.toJSONArray("ONE", "TWO", "THREE")); // list of Enum
        json.put("enumMap", __genJSONObject("ONE", "하나", "둘", "셋")); // Map of Enum
        json.put("list", JSONUtil.toJSONArray("나는", "자랑스런", "대한민국", "국민이다.")); // List of String
        json.put("mapInt", __genJSONObject(0, "나", "는", "행", "복", "하", "다")); // Map of int
        json.put("mapStr", __genJSONObject("OK", "나", "는", "행", "복", "하", "다")); // Map of String

        for (int i = 0; i < 2; i++) {
            jsonResultValue.put(json);
        }

        jsonResult.put("resultValue", jsonResultValue);

        JSONWrapperArrayResult<JSONModel> result = new JSONWrapperArrayResult<JSONModel>();
        result.mature(jsonResult);

        System.out.println(result);

    }
    
    static void _test_JSONArray() throws JSONException {
        JSONArray jsonArr = new JSONArray();
        
        JSONObject json = new JSONObject();
        json.put("enumm", "ONE"); // enum
        json.put("age", 37); // int
        json.put("name", "박준홍"); // String
        json.put("strings", JSONUtil.toJSONArray("나는", "자랑스런", "대한민국", "국민이다.")); // array of String
        json.put("ints", JSONUtil.toJSONArray(1, 2, 3, 4, 5, 6)); // array of int
        json.put("enumArray", JSONUtil.toJSONArray("ONE", "TWO", "THREE")); // array of Enum
        json.put("enumList", JSONUtil.toJSONArray("ONE", "TWO", "THREE")); // list of Enum
        json.put("enumMap", __genJSONObject("ONE", "하나", "둘", "셋")); // Map of Enum
        json.put("list", JSONUtil.toJSONArray("나는", "자랑스런", "대한민국", "국민이다.")); // List of String
        json.put("mapInt", __genJSONObject(0, "나", "는", "행", "복", "하", "다")); // Map of int
        json.put("mapStr", __genJSONObject("OK", "나", "는", "행", "복", "하", "다")); // Map of String
        json.put("subClass", "나는 하위 클래스입니다");
        
        jsonArr.put(json);
        jsonArr.put(json);
        jsonArr.put(json);
        jsonArr.put(json);
        
        JSONMapper.register(JSONEnum.class, JSONEnum.getMarshaller());
        JSONModel[] jmArr = (JSONModel[]) JSONArrayFactory.createArray(jsonArr, JSONModel.class);
        
        for(JSONModel jm : jmArr ){
            System.out.println(jm.toJSONString(4));
        }
    }
    
    static void _test_Inherit() throws JSONException {
        JSONArray jsonArr = new JSONArray();
        
        JSONObject json = new JSONObject();
        json.put("enumm", "ONE"); // enum
        json.put("age", 37); // int
        json.put("name", "박준홍"); // String
        json.put("strings", JSONUtil.toJSONArray("나는", "자랑스런", "대한민국", "국민이다.")); // array of String
        json.put("ints", JSONUtil.toJSONArray(1, 2, 3, 4, 5, 6)); // array of int
        json.put("enumArray", JSONUtil.toJSONArray("ONE", "TWO", "THREE")); // array of Enum
        json.put("enumList", JSONUtil.toJSONArray("ONE", "TWO", "THREE")); // list of Enum
        json.put("enumMap", __genJSONObject("ONE", "하나", "둘", "셋")); // Map of Enum
        json.put("list", JSONUtil.toJSONArray("나는", "자랑스런", "대한민국", "국민이다.")); // List of String
        json.put("mapInt", __genJSONObject(0, "나", "는", "행", "복", "하", "다")); // Map of int
        json.put("mapStr", __genJSONObject("OK", "나", "는", "행", "복", "하", "다")); // Map of String
        json.put("subClass", "나는 하위 클래스입니다");
        json.put("subClass3", "나는 하위 클래스의 하위 클래스입니다");
        
        jsonArr.put(json);
//        jsonArr.put(json);
//        jsonArr.put(json);
//        jsonArr.put(json);
        
        JSONMapper.register(JSONEnum.class, JSONEnum.getMarshaller());
        JSONModel[] jmArr = (JSONModel[]) JSONArrayFactory.createArray(jsonArr, JSONModel3.class);
        
        for(JSONModel jm : jmArr ){
            System.out.println(jm.toJSONString(4));
        }
    }

    static void _test_JSONWrapperResult() throws JSONException {

        JSONObject jsonResult = new JSONObject();
        jsonResult.put("resultCode", 0);
        jsonResult.put("resultMsg", "성공");

        JSONObject jsonResultValue = new JSONObject();
        jsonResultValue.put("age", 37);
        jsonResultValue.put("name", "박준홍");

        JSONArray jsonArray = new JSONArray();
        for (int i = 0; i < 5; i++) {
            jsonArray.put(i);
        }
        jsonResultValue.put("ints", jsonArray);

        jsonArray = new JSONArray();
        jsonArray.put("안");
        jsonArray.put("녕");
        jsonArray.put("하");
        jsonArray.put("세");
        jsonArray.put("요");
        jsonResultValue.put("strings", jsonArray);

        JSONObject jsonRef = new JSONObject();
        jsonRef.put("age", 37);
        jsonRef.put("name", "\"박준홍\"");

        jsonArray = new JSONArray();
        for (int i = 0; i < 5; i++) {
            jsonArray.put(i);
        }
        jsonRef.put("ints", jsonArray);

        jsonArray = new JSONArray();
        jsonArray.put("안");
        jsonArray.put("녕");
        jsonArray.put("하");
        jsonArray.put("세");
        jsonArray.put("요");
        jsonRef.put("strings", jsonArray);

        jsonResultValue.put("ref", jsonRef);

        jsonResult.put("resultValue", jsonResultValue);

        System.out.println(jsonResult.toString(2));

        JSONWrapperResult<JSONModel> result = new JSONWrapperResult<JSONModel>();
        result.mature(jsonResult);

        System.out.println(result.toJSONString(2));
        System.out.println(result.toJSONString(1));
        System.out.println(result.toJSONString());

    }

    public static void main(String[] args) throws JSONException {
        // _test_JSONWrapperResult();
        // _test_JSONUtil();
//        _test_ContainerField();
        // _test_JSONWrapperArrayResult();
//        _test_JSONArray();
        _test_Inherit();

    }

}
