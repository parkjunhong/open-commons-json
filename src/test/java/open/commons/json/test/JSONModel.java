package open.commons.json.test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import open.commons.json.annotation.JSONField;
import open.commons.json.model.DefaultJSONModel;

public class JSONModel<T, V> extends DefaultJSONModel {
    private static final long serialVersionUID = 1L;

    public static final String enummName = "open.commons.test.JSONModel.JSONEnumm enumm";
    public static final String ageUpper = "open.commons.test.JSONModel.int age";

    @JSONField(marshaller = enummName)
    public JSONEnum enumm;
    @JSONField(marshaller = ageUpper)
    public int age;
    @JSONField
    public String name;
    @JSONField
    public JSONModel ref;
    @JSONField
    public int[] ints;
    @JSONField
    public String[] strings;
    @JSONField
    public JSONEnum[] enumArray;
    @JSONField
    public T[] typeVarArray;
    @JSONField
    public V[] typeVarArray2;
    @JSONField
    public JSONModel[] refArray;
    @JSONField
    public List<T> typeList;
    @JSONField
    public List<JSONEnum> enumList;
    @JSONField
    public Map<String, JSONEnum> enumMap;
    @JSONField
    public List<String> list;
    @JSONField
    public List<String> listObj;
    @JSONField
    public ArrayList<JSONModel> arraylist;
    @JSONField
    public Map<String, Integer> mapInt;
    @JSONField(isfinal = false)
    public Map<String, String> mapStr;
    @JSONField
    public Map<String, JSONModel> mapObj;
    @JSONField
    public Map<String, T> mapTypeVar;

    @JSONField
    public HashMap<String, Integer> hashMap;
    @JSONField
    public List<HashMap<String, JSONModel>> hashMapList;
    @JSONField
    public List<List<JSONModel>[]> listArrList;
    @JSONField
    public List<Map<String, List<String>>[]> mapArrayList;
    @JSONField
    public List annonyList;

}